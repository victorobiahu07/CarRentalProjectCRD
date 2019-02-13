# CarRentalProjectCRD
This project contains 4 classes 
Vehicle which contains an Enum VehicleType for the 3 possible type of cars a customer can rent..The 3 types of cars all have different prices. I
CarReservation class is a POJO with the attributes necessary to make a Reservation happen
CarReservationController has the core functionality where I have addReservation, deleteReservation, calculateReservationPrice, canReserveVehicle, setMaxCarsAvailable as well as validatePhoneNumber methods. 
There is a map called createAvailableMaps which populates the count of the 3 vehicles types which ties into the availableCars map, reservationsMap and reservedVehicleReturnDates.
I included the carRentalClient class which contains displays some of the core methods
Lastly I included the CarReservationTest class which I did using JUnit. This further tested validity of core methods.
This project took 3hrs in it's entirety due to test cases and testing classes within Java Main



