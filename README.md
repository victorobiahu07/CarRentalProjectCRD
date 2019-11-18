# CarRentalProjectCRD
Designing a prototype a car rental system using OOP with a focus on the following 
1. Having the system let a customer reserve a car of a given type at a desired date and time for a given number of days.

2. The number of cars of each type is limited, but customers should be able to reserve a single rental car for multiple, non-overlapping time frames.

3.  Provide JUnit tests that illustrate the core reservation workflow and demonstrates its correctness, including a negative test for when a car is not available.
------------------------------------------------------------------------------------------------------------------------------------------
This project contains 4 classes 

Vehicle which contains an Enum VehicleType for the 3 possible type of cars a customer can rent..The 3 types of cars all have different prices. 
CarReservation class is a POJO with the attributes necessary to make a Reservation happen

CarReservationController has the core functionality where I have addReservation, deleteReservation, calculateReservationPrice, canReserveVehicle, setMaxCarsAvailable as well as validatePhoneNumber methods

There is a map called createAvailableMaps which populates the count of the 3 vehicles types which ties into the availableCars map, reservationsMap and reservedVehicleReturnDates

I included the carRentalClient class which contains displays some of the core methods

Lastly I included the CarReservationTest class which I did using JUnit. This further tested validity of core methods

This project took 3hrs in it's entirety due to test cases and testing classes within Java Main



