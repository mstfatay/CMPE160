
package interfaces;

import locations.*;

/**
 * 
 * @author Mustafa Atay
 *
 */
public interface ownCar {
	/**
	 * Adds given amount of fuel to the passenger's car.
	 * @param amount the amount of fuel to be added to the car
	 */
	public void refuel(double amount);
	
	/**
	 * The passenger drives to location l. 
	 * @param l The location where passenger drives
	 */
	public void drive(Location l);
	
	/**
	 * Passenger buys a car which has a given amount of fuel consumption. 
	 * @param fuelConsumption the amount of fuel consumption of the bought car
	 */
	public void purchaseCar(double fuelConsumption);
}

