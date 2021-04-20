
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;
import java.io.PrintStream;

import locations.Location;
import vehicles.*;

/**
 * 
 * @author Mustafa Atay
 *
 */
public class Passenger{
	/**
	 * The number of passengers
	 */
	public static int count = 0;
	
	/**
	 * The unique ID of this passenger
	 */
	private int ID;
	
	/**
	 * Whether this passenger has a drivers license
	 */
	private boolean hasDriversLicense;
	
	/**
	 * The value of card balance of this passenger
	 */
	private double cardBalance = 0;
	
	/**
	 * If this passenger has a car, this is that car object. Else, this is null
	 */
	private Car car;
	
	/**
	 * The location where passenger is
	 */
	private Location currentLocation;
	
	/**
	 * Creates a passenger with an ID at location l. The information whether the passenger 
	 * has a drivers license is also initialized.
	 * @param ID the unique ID of that passenger
	 * @param hasDriversLicense whether the passenger has a drivers license
	 * @param l location where the passenger created
	 */
	public Passenger(int ID, boolean hasDriversLicense, Location l) {
		this.ID = ID;
		this.hasDriversLicense = hasDriversLicense;
		this.currentLocation = l;
		l.incomingPassenger(this);
		//keep continue!!!
	}
	
	/**
	 * Creates a passenger with an ID, drivers license and car at location l. His/her car's
	 * fuel consumption is also initialized.
	 * @param ID the unique ID of that passenger
	 * @param l location where the passenger created
	 * @param fuelConsumption the fuel consumption amount of the passenger's car
	 */
	public Passenger(int ID, Location l, double fuelConsumption) {
		this.ID = ID;
		this.hasDriversLicense = true;
		this.currentLocation = l;
		car = new Car(ID, fuelConsumption);
		l.incomingPassenger(this);
		//keep continue!!!
	}
	
	/**
	 * The passenger goes to location l using given public transportation
	 * vehicle p, which is a bus or train. If public transportation can't go to
	 * location l or the passenger's card balance isn't enough to afford the
	 * ride, the passenger doesn't go anywhere.
	 * @param p The public transportation vehicle which the passenger uses. It is a bus or train.
	 * @param l the location where passenger goes
	 */
	public void ride(PublicTransport p, Location l) {
		// if p can't go between the location where passenger is and the location l, don't ride (return)
		if(!p.canRide(getCurrentLocation(), l)) {
			return;
		}
		double price = getTotalPrice(p,l); // the price of the ride
		
		if(price == -1) {
			return;
		}
		
		// check if the passenger has enough money to ride
		else if(cardBalance>=price) {
			passengerGo(l);
			cardBalance -= price;
		}
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
			return b.getPrice();
		}
		else if(p instanceof Train) {
			Train t = (Train) p;
			return t.getPrice(l.getDistance(getCurrentLocation()));
		}
		else {
			return -1;
		}
	}
	
	/**
	 * The passenger drives to location l. If the passenger's car doesn't
	 *  have enough fuel to reach the location l or the passenger doesn't have
	 * a drivers license or a car, he/she doesn't drive.
	 * @param l The location where passenger drives
	 */
	public void drive(Location l) {
		// the passenger must have a drivers license and a car to drive
		if(!this.hasDriversLicense || !(this.car instanceof Car)) {
			return;
		}
		double distance = currentLocation.getDistance(l);
		double fuelAmount = car.getFuelAmount();
		double fuelConsumption = car.getFuelConsumption();
		double fuel = fuelConsumption * distance; // the amount of fuel needed to finish that drive
		
		// if the fuel enough, then the passenger drives
		if(fuelAmount >= fuel ) {
			passengerGo(l);
			car.spendFuel(fuel);
		}
	}
	
	/**
	 * Writes the passenger's ID and if he/she has a car, this car's fuel amount. Else, his/her card balance.
	 */
	public void write(PrintStream output) {
		if(car != null) {
			double a = (double)((int)(car.getFuelAmount() * 100 + 0.0000000001)) / 100;
			output.printf("Passenger %d: %.2f\n", ID, a); // writes: "passenger <ID>: <fuel amount>"
		}
		else {
			double a = (double)((int)(cardBalance * 100 + 0.0000000001)) / 100;
			output.printf("Passenger %d: %.2f\n", ID, a); // writes: "passenger <ID>: <card balance>"
		}
	}
	
	/**
	 * Sends passenger to the given location l. Updates currentLocation.
	 * @param l the location passenger goes
	 */
	public void passengerGo(Location l) {
		this.currentLocation.outgoingPassenger(this);
		this.currentLocation = l;
		l.incomingPassenger(this);
	}
	
	/**
	 * Adds given amount of money to card balance.
	 * @param amount the amount of money to be added to the card balance
	 */
	public void refillCard(double amount) {
		this.cardBalance += amount;
	}
	
	/**
	 * Adds given amount of fuel to the passenger's car.
	 * @param amount the amount of fuel to be added to the car
	 */
	public void refuel(double amount) {
		// passenger must have a car to refuel
		if(car!=null) {
			car.refuel(amount);
		}
	}
	
	/**
	 * Passenger buys a car which has a given amount of fuel consumption. New car object with
	 * the given fuel consumption is assigned to the passenger's car. Also the passenger gets
	 * a drivers license. 
	 * @param fuelConsumption the amount of fuel consumption of the bought car
	 */
	public void purchaseCar(double fuelConsumption) {
		car = new Car(this.ID, fuelConsumption);
		this.hasDriversLicense = true;
	}

	/**
	 * Returns ID of the passenger.
	 * @return the unique ID of passenger as an integer
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Returns current location of the passenger.
	 * @return the Location object where passenger is
	 */
	public Location getCurrentLocation() {
		return currentLocation;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

