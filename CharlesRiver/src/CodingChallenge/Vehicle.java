package CodingChallenge;
import java.util.*;

import java.lang.*;

import java.util.EnumMap;

//My main class..
public abstract class Vehicle {
	
	public enum VehicleType
	{
		ECONOMY(18.00), PREMIUM(22.50), SUV(25.50);
		
		private double vehicleDailyCost;
		
		private VehicleType(double vehicleDailyCost)
		{
			this.vehicleDailyCost = vehicleDailyCost;
		}
		
		public double getVehicleDailyCost()
		{
			return vehicleDailyCost;
		}
	}
	
	public String licensePlate;
	public String vin;
	public String make;
	public String model; 
	public int milesPerGallon;
	public double dailyCost;
	public VehicleType vType;
	
	public Vehicle()
	{
		
	}
	
	public Vehicle(String v, String lPlate, String vn, String mke, String mdl, int mpg)
	{
		vType = VehicleType.valueOf(v);
		licensePlate = lPlate;
		vin =  vn;
		make = mke;
		model = mdl;
		milesPerGallon = mpg;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}


	public String getVin() {
		return vin;
	}


	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}
	
	public String toString()
	{
		return getMake() + " " + getModel() + " with license plate" + getLicensePlate() + " is free for booking";
	}
	
}
