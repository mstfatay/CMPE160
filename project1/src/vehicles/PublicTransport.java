
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;
import locations.Location;

/**
 * 
 * @author Mustafa Atay
 *
 */
public abstract class PublicTransport {
	/**
	 * The number of public transportation vehicles
	 */
	public static int count = 0;
	
	/**
	 * The unique ID of the public transportation vehicle
	 */
	private int ID;
	
	/**
	 * The x component of the first end point of the operation range
	 */
	private double x1;
	
	/**
	 * The y component of the first end point of the operation range
	 */
	private double y1;
	
	/**
	 * The x component of the second end point of the operation range
	 */
	private double x2;
	
	/**
	 * The y component of the second end point of the operation range
	 */
	private double y2;
	
	/**
	 * Creates a public transportation vehicle with ID in the operation range (x1, y1, x2, y2)
	 * @param ID the unique ID of the public transportation vehicle
	 * @param x1 the x component of the first end point of the operation range
	 * @param y1 the y component of the first end point of the operation range
	 * @param x2 the x component of the second end point of the operation range
	 * @param y2 the y component of the second end point of the operation range
	 */
	public PublicTransport(int ID, double x1, double y1, double x2, double y2) {
		this.ID = ID;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	/**
	 * Returns true if location departure and arrival are in the vehicle's operation range.
	 * @param departure the location where the vehicle leaves
	 * @param arrival the location where the vehicle goes
	 * @return true if location departure and arrival are in the vehicle's operation range
	 */
	public boolean canRide(Location departure, Location arrival) {
		double xc1 = departure.getLocationX();
		double yc1 = departure.getLocationY();
		double xc2 = arrival.getLocationX();
		double yc2 = arrival.getLocationY();
		
		// checks whether departure and arrival locations are in vehicle's operation range and returns it
		return between(x1,x2,xc1) && between(x1,x2,xc2) && between(y1,y2,yc1) && between(y1,y2,yc2); 
	}
	
	/**
	 * Returns true if the mid value is between the a and b values. If mid value equals to a or b, it again returns true.
	 * @param a the first value
	 * @param b the second value
	 * @param mid the value we want to examine if it is middle value
	 * @return true if the mid value is between the a and b values. If mid value equals to a or b, it again returns true.
	 */
	private static boolean between(double a, double b, double mid) {
		// to make sure a is equal to or greater than b
		if(b>a) {
			double tmp = a;
			a = b;
			b = tmp;
		}
		return a >= mid && mid >= b;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE





