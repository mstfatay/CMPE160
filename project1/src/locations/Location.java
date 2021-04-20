
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package locations;
import passengers.Passenger;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * 
 * @author Mustafa Atay
 *
 */
public class Location{
	/**
	 * The number of locations
	 */
	public static int count = 0;

	/**
	 * The unique ID of this location
	 */
	private int ID;
	
	/**
	 * X component of the location
	 */
	private double locationX;
	
	/**
	 * Y component of the location
	 */
	private double locationY;
	
	/**
	 * A list of passengers who has gone this location but aren't at this location now
	 */
	private ArrayList<Passenger> history = new ArrayList<Passenger>();
	
	/**
	 * A list of passengers who are at this location now
	 */
	private ArrayList<Passenger> current = new ArrayList<Passenger>();
	
	/**
	 * Creates a location object with an ID and coordinates x and y.
	 * @param ID a unique number for all Location objects.
	 * @param locationX x component of coordinate of this location
	 * @param locationY y component of coordinate of this location
	 */
	public Location(int ID, double locationX, double locationY) {
		this.ID = ID;
		this.locationX = locationX;
		this.locationY = locationY;
	}

	/**
	 * Calculates distance between 2 locations.
	 * @param other second location object
	 * @return distance between 2 locations which is a double
	 */
	public double getDistance(Location other) {
		double x2 = other.getLocationX();
		double y2 = other.getLocationY();
		return Math.sqrt(Math.pow(this.locationX-x2, 2) + Math.pow(this.locationY-y2, 2)); // Pythagorean theorem is used to calculate the distance
	}
	
	/**
	 * Adds the Passenger p to List current.
	 * @param p the passenger
	 */
	public void incomingPassenger(Passenger p) {
		current.add(p); // adds passenger to the current passenger list
	}
	
	/**
	 * Removes the Passenger p from List current and adds it to List history.
	 * @param p the passenger
	 */
	public void outgoingPassenger(Passenger p) {
		for(int i=0; i<current.size(); i++) {
			//find the needed passenger in current list
			if(current.get(i) == p) {
				current.remove(i);
				history.add(p);
				return;
			}
		}
	}
	
	/**
	 * writes this location's position and calls write function for all passengers in List current
	 * to write passenger's claimed property in this location.
	 */
	public void write(PrintStream output) {
		double a = (double)((int)(locationX * 100 + 0.0000000001)) / 100;
		double b = (double)((int)(locationY * 100 + 0.0000000001)) / 100;
		output.printf("Location %d: (%.2f, %.2f)\n", ID, a, b);
		ArrayList<Passenger> list = new ArrayList<Passenger>(current);
		
		// writes passengers in a ascending order
		while(list.size()>0) {
			int tmp = list.get(0).getID();
			int index = 0;
			for(int i = 1; i<list.size(); i++) {
				if(list.get(i).getID() < tmp) {
					tmp = list.get(i).getID();
					index = i;
				}
			}
			list.get(index).write(output);
			list.remove(index);
		}
	}
	
	/**
	 * returns x component of coordinates of location.
	 * @return x component of coordinates of location as double
	 */
	public double getLocationX() {
		return locationX;
	}
	
	/**
	 * returns y component of coordinates of location.
	 * @return y component of coordinates of location as double
	 */
	public double getLocationY() {
		return locationY;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

