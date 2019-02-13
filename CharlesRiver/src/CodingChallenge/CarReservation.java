package CodingChallenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import CodingChallenge.Vehicle.VehicleType;

public class CarReservation {
	String customerName;
	long numDays = 0; 
	Vehicle vehicle;
	VehicleType vType;
	double reservationPrice = 0.0;
	String startDate;
	String endDate;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    String phoneNumber;
	public CarReservation(VehicleType v, String cName, String phoneNum, String sDate, String eDate)
	{
		//String 
		this.vType = v;
		customerName = cName;
		phoneNumber = phoneNum; 
		startDate = sDate;
		endDate = eDate;
	}
 
	/*public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}*/
	
	public Date getStartDate() throws ParseException {
		return sdf.parse(startDate);
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() throws ParseException {
		return sdf.parse(endDate);
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getNumDays() {
		return numDays;
	}

	public void setNumDays(long numDays) {
		this.numDays = numDays;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public VehicleType getvType() {
		return vType;
	}

	public void setvType(VehicleType vType) {
		this.vType = vType;
	}

	public double getReservationPrice() {
		return reservationPrice;
	}

	public void setReservationPrice(double reservationPrice) {
		this.reservationPrice = reservationPrice;
	}

}