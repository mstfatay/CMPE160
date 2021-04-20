
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;

import interfaces.*;
import locations.Location;

/**
 * 
 * @author Mustafa Atay
 *
 */
public class StandardPassenger extends Passenger implements ownCar, usePublicTransport{
	/**
	 * Creates a standard passenger with an ID at location l. The information whether the passenger 
	 * has a drivers license is also initialized.
	 * @param ID the unique ID of that passenger
	 * @param hasDriversLicense whether the passenger has a drivers license
	 * @param l location where the passenger created
	 */
	public StandardPassenger(int ID, boolean hasDriversLicense, Location l) {
		super(ID, hasDriversLicense, l);
	}
	
	/**
	 * Creates a standard passenger with an ID, drivers license and car at location l. His/her car's
	 * fuel consumption is also initialized.
	 * @param ID the unique ID of that passenger
	 * @param l location where the passenger created
	 * @param fuelConsumption the fuel consumption amount of the passenger's car
	 */
	public StandardPassenger(int ID, Location l, double fuelConsumption) {
		super(ID, l, fuelConsumption);
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

