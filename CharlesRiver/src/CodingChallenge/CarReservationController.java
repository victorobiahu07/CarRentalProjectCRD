package CodingChallenge;



import CodingChallenge.Vehicle;
import CodingChallenge.Vehicle.VehicleType;

import java.lang.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//main method here where we handle reservations and all that
public class CarReservationController {

	String phoneNumber; 
	long numDays = 0; 
	Vehicle vehicle;
	VehicleType vType;
	public double reservationPrice = 0.0;

	String startDate;
	String endDate;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	public static final int MAX_ECONOMY = 10; //used this to track the amount of rented cars and what was available to rent. This was applied in addReservation method
	public static final int MAX_PREMIUM = 10; 
	public static final int MAX_SUV = 5;

	public CarReservationController()
	{
		availableCars = createAvailableCarsMap(); //trying to populate the createAvailableCars hashmap with data so that I can add reservations per vehicle type(Economy, Premium and SUV if there are any available.
	}

	Map<VehicleType, PriorityQueue<Date>> reservedVehicleReturnDates = new HashMap<>(); // Map from vehicle type to reserved car end dates. This will hold all the current reservations end dates for each vehicle type

	Map<VehicleType, Integer> availableCars; 

	public HashMap<String, List<CarReservation>> reservationsMap = new HashMap<>();

	public Map<VehicleType, Integer> createAvailableCarsMap() { 
		Map<VehicleType, Integer> map = new EnumMap<VehicleType, Integer>(VehicleType.class);
		map.put(VehicleType.ECONOMY, MAX_ECONOMY);
		map.put(VehicleType.PREMIUM, MAX_PREMIUM);
		map.put(VehicleType.SUV, MAX_SUV);
		return map;
	}

	public void setMaxCarsAvailable(VehicleType v, int maxAvailable) {
			availableCars.put(v, maxAvailable);
	}

	private static boolean validatePhoneNumber(String phoneNo) {
		//validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}")) 
			return true; //phone number with -, . or spaces
		else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) 
			return true; //phone number with extension length from 3 to 5
		else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
			return true; //phone number with area code in braces
		else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) 
			return true;
		//return false if nothing matches the input
		else return false;
	}

        //the core method to create a reservation 
 	public void addReservation(CarReservation res) throws Exception 
	{	
		
		Date sDate = res.getStartDate(); //HERE
		Date eDate = res.getEndDate(); //HERE
		vType = res.getvType();
		String phoneNumber = res.getPhoneNumber();
			if(canReserveVehicle(vType, phoneNumber, sDate, eDate)) {
				if (reservationsMap.containsKey(phoneNumber)) {
					List<CarReservation> currCustomerRes = reservationsMap.get(phoneNumber);
					currCustomerRes.add(res);
					reservationsMap.put(phoneNumber, currCustomerRes);
				} else {
					List<CarReservation> currCustomerRes = new ArrayList<CarReservation>(Arrays.asList(res));
					reservationsMap.put(phoneNumber, currCustomerRes);
				}
				int countForVehicleType = availableCars.get(vType); //was Integer before
				availableCars.put(vType, countForVehicleType - 1);
				if (reservedVehicleReturnDates.containsKey(vType)) {
					reservedVehicleReturnDates.get(vType).add(eDate);
				} else {
					PriorityQueue<Date> q = new PriorityQueue<Date>();
					reservedVehicleReturnDates.put(vType, q);
				}
			}	
	}
	
	//this checks validity of a reservation
	public boolean canReserveVehicle(VehicleType v, String phoneNumber, Date startDate, Date endDate) throws ParseException 
	{
		//if phone Number isn't valid for reservation then it is false
		//if(!validatePhoneNumber(phoneNumber))
			//return false;

		PriorityQueue<Date> reservedVehicleQueue = reservedVehicleReturnDates.get(v);
		if(reservedVehicleQueue == null) {
			reservedVehicleQueue = new PriorityQueue<Date>();
		}
		if(endDate.before(startDate))
			return false; // check that the start date of the reservation is before the end date 

		if (availableCars.get(v) == 0) {
			Date nextCarReturnDate = reservedVehicleQueue.peek();
			if(nextCarReturnDate.after(startDate))
				return false; // return false if a reserved car is not going to be available before the new customer is requesting one.
		}
		else {
			// If a car that will become available before the customer requests it, remove it from the queue and replace with the 
			//requesting customer's return date (as they now lay claim to the car)
			if(!reservedVehicleQueue.isEmpty()) {
				reservedVehicleQueue.poll();
			}
			reservedVehicleQueue.add(endDate);
		}

		//these are comparing strings.
		if (reservationsMap.containsKey(phoneNumber)) {
			List<CarReservation> resByCustomer = reservationsMap.get(phoneNumber);
			CarReservation lastResByCustomer = resByCustomer.get(resByCustomer.size() - 1); 
			Date lastResEndDate = sdf.parse(lastResByCustomer.endDate);
			if (startDate.before(lastResEndDate)) {  //1 customer can only have one rental at a time within the system.
				return false;
			} 
		}
		return true;	
	}

	
        //Removes a reservation using phoneNumber as a key
	public void deleteReservation(CarReservation res)
	{
		String phoneNo = res.getPhoneNumber();
		vType = res.getvType();
		reservationsMap.remove(phoneNo);
		int countForVehicleType = availableCars.get(vType);
		availableCars.put(vType, countForVehicleType + 1);
		
	}
	
	//Made this method a function of the start and end date of the rental reservation, calculated num days * price of that type of rental
	public double calculateReservationPrice(String v, String startDate, String endDate) throws ParseException
	{
		Date formatStartdate = sdf.parse(startDate);
		Date formatEndDate = sdf.parse(endDate);
		VehicleType vehicleType = VehicleType.valueOf(v);

		numDays = Math.round((formatEndDate.getTime() - formatStartdate.getTime()) / (double) 86400000);
		double reservationPrice = vehicleType.getVehicleDailyCost() * numDays;
		return reservationPrice;
	}


}
