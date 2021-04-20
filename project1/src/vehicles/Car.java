
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;

/**
 * 
 * @author Mustafa Atay
 *
 */
public class Car{
	/**
	 * The ID of the passenger who owns that car
	 */
	private int ownerID;
	
	/**
	 * The amount of fuel of the car
	 */
	private double fuelAmount = 0;
	
	/**
	 * The amount of fuel the car consumes per unit distance
	 */
	private double fuelConsumption;
	
	/**
	 * Creates a car with a given owner ID and the given fuel consumption information.
	 * @param ID the car owner's ID
	 * @param fuelConsumption The amount of fuel the car consumes per unit distance
	 */
	public Car(int ID, double fuelConsumption) {
		this.ownerID = ID;
		this.fuelConsumption = fuelConsumption;
	}
	
	/**
	 * Adds the given amount of fuel to car.
	 * @param amount the amount of fuel added
	 */
	public void refuel(double amount) {
		this.fuelAmount += amount;
	}
	
	/**
	 * returns the amount of fuel the car has.
	 * @return the amount of fuel the car has as a double
	 */
	public double getFuelAmount() {
		return fuelAmount;
	}
	
	/**
	 * returns the amount of fuel the car consumes per unit distance.
	 * @return the amount of fuel the car consumes per unit distance as double
	 */
	public double getFuelConsumption() {
		return fuelConsumption;
	}
	
	/**
	 * Car spends the given amount of fuel. If car doesn't have enough fuel to spend, 
	 * the process doesn't take place.
	 * @param fuel the amount of fuel that is wanted to spend by car
	 */
	public void spendFuel(double fuel) {
		// the fuel car has must be enough to finish the drive
		if(this.fuelAmount - fuel < 0) {
			return;
		}
		this.fuelAmount -= fuel;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

