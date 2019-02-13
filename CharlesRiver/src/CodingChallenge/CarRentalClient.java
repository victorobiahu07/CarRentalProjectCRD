package CodingChallenge;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import CodingChallenge.Vehicle;
import CodingChallenge.Vehicle.VehicleType;
import CodingChallenge.Customer;
import CodingChallenge.CarReservationController;

public class CarRentalClient {
	
	public static void main(String[] args) throws Exception {
		
		CarReservationController reservationSystem = new CarReservationController();
		CarReservation firstRes = new CarReservation(VehicleType.PREMIUM, "Jon Snow", "9098070212", "2019-01-23", "2019-01-31");
		reservationSystem.addReservation(firstRes);
		
		CarReservation secondRes = new CarReservation(VehicleType.ECONOMY, "Jamie Lannister", "312999111", "2019-01-11", "2019-01-20");
		reservationSystem.addReservation(secondRes);
		
		CarReservation thirdRes = new CarReservation(VehicleType.PREMIUM, "Jack White", "312999100", "2019-01-15", "2019-01-19");
		reservationSystem.addReservation(thirdRes);
		
		CarReservation fourthRes = new CarReservation(VehicleType.ECONOMY, "Luke Skywalker", "302919102", "2019-01-10", "2019-01-11");
		reservationSystem.addReservation(fourthRes);
		
		//testing delete here
		reservationSystem.deleteReservation(secondRes);
		
		System.out.println("Number of reservations in reservation map: " + reservationSystem.reservationsMap.size()); //Should return 3 because I added 4 and deleted 1
	
		
		//For extensive test cases, go to CarReservationTest JUnit class 	
	}

}