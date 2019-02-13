package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

import org.junit.Test;

import CodingChallenge.CarReservation;
import CodingChallenge.CarReservationController;
import CodingChallenge.Vehicle.VehicleType;

public class CarReservationTest {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 

	@Test
	public void testThatCustomerCanMakeReservation() throws Exception { 
		CarReservationController reservationSystem = new CarReservationController();
		reservationSystem.setMaxCarsAvailable(VehicleType.PREMIUM, 2);
		CarReservation firstRes = new CarReservation(VehicleType.PREMIUM, "Jon Snow", "1234567890", "2019-01-23", "2019-01-31");
		reservationSystem.addReservation(firstRes);
		//assertTrue(reservationSystem.reservationsMap.containsKey("1234567890"));
		assertTrue(reservationSystem.reservationsMap.size() > 0);
		assertEquals(firstRes, (reservationSystem.reservationsMap.get("1234567890")).get(0));

	}

	@Test
	public void testThatOneCustomerCannotMakeOverlappingReservation() throws Exception { //satisfies rule where booking has to be one at a time
		CarReservationController reservationSystem = new CarReservationController();
		CarReservation firstRes = new CarReservation(VehicleType.PREMIUM, "Jon Snow", "9098070212", "2019-01-23", "2019-01-31");
		reservationSystem.addReservation(firstRes);
		assertTrue(reservationSystem.reservationsMap.containsKey("9098070212"));
		assertEquals(firstRes, (reservationSystem.reservationsMap.get("9098070212")).get(0));

		// Attempt to add a second reservation using the same phone number.
		CarReservation secondRes = new CarReservation(VehicleType.PREMIUM, "Jon Snow", "9098070212", "2019-01-24", "2019-01-25");
		reservationSystem.addReservation(secondRes);
		assertTrue(reservationSystem.reservationsMap.containsKey("9098070212"));
		//  assertFalse(reservationSystem.canReserveVehicle(VehicleType.PREMIUM, "9098070212", sdf.parse(secondRes.getStartDate()), sdf.parse(secondRes.getEndDate())));
		// The second reservation should not be allowed added into the reservations list.
		assertTrue(reservationSystem.reservationsMap.size() < 2); //checking that only 1 reservation happened.
		assertEquals(firstRes, (reservationSystem.reservationsMap.get("9098070212")).get(0));
	}

	@Test
	public void testThatReservationPriceWorks() throws Exception {

		CarReservationController reservationSystem = new CarReservationController();
		reservationSystem.calculateReservationPrice("PREMIUM","2019-01-22", "2019-01-29");
		assertTrue(reservationSystem.reservationPrice == 157.50); //there are 7 days between 22nd Jan and 29th. The daily cost of a premium vehicle is $22.50
	}
	
	@Test
	public void deleteReservationAndCheckCountOfAvailableCars() throws Exception {

		CarReservationController reservationSystem = new CarReservationController();
		CarReservation firstRes = new CarReservation(VehicleType.PREMIUM, "Jon Snow", "9098070212", "2019-01-23", "2019-01-31");
		reservationSystem.addReservation(firstRes);
		
		CarReservation secondRes = new CarReservation(VehicleType.ECONOMY, "Jamie Lannister", "312999111", "2019-01-11", "2019-01-20");
		reservationSystem.addReservation(secondRes);
		
		CarReservation thirdRes = new CarReservation(VehicleType.PREMIUM, "Jack White", "312999100", "2019-01-15", "2019-01-19");
		reservationSystem.addReservation(thirdRes);
		
		CarReservation fourthRes = new CarReservation(VehicleType.ECONOMY, "Luke Skywalker", "302919102", "2019-01-10", "2019-01-11");
		reservationSystem.addReservation(fourthRes);
		
		reservationSystem.deleteReservation(secondRes);
		assertTrue(reservationSystem.reservationsMap.size() == 3);
		
	}


}