
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;

import interfaces.*;
import locations.Location;
import vehicles.Bus;
import vehicles.PublicTransport;
import vehicles.Train;

/**
 * 
 * @author Mustafa Atay
 *
 */
public class DiscountedPassenger extends Passenger implements ownCar, usePublicTransport{
	/**
	 * Creates a passenger with an ID at location l. The information whether the passenger 
	 * has a drivers license is also initialized.
	 * @param ID the unique ID of that passenger
	 * @param hasDriversLicense whether the passenger has a drivers license
	 * @param l location where the passenger created
	 */
	public DiscountedPassenger(int ID, boolean hasDriversLicense, Location l) {
		super(ID, hasDriversLicense, l);
	}
	
	/**
	 * Creates a passenger with an ID, drivers license and car at location l. His/her car's
	 * fuel consumption is also initialized.
	 * @param ID the unique ID of that passenger
	 * @param l location where the passenger created
	 * @param fuelConsumption the fuel consumption amount of the passenger's car
	 */
	public DiscountedPassenger(int ID, Location l, double fuelConsumption) {
		super(ID, l, fuelConsumption);
	}
	
	/**
	 * Calculates the price needed to go to the location l using public 
	 * transportation vehicle p. If p isn't a bus or train returns -1.
	 * @param p the public transportation vehicle which is a bus or train
	 * @param l the location where the passenger goes.
	 * @return the price of the ride if p is a bus or train. Else, returns -1.
	 */
	public double getTotalPrice(PublicTransport p, Location l) {
		if(p instanceof Bus) {
			Bus b = (Bus) p;
			return b.getPrice()/2;
		}
		else if(p instanceof Train) {
			Train t = (Train) p;
			return t.getPrice(l.getDistance(getCurrentLocation()))*4/5;
		}
		else {
			System.out.println("wrong veichle instance in Passenger.ride");
			return -1;
		}
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

