
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;

/**
 * 
 * @author Mustafa Atay
 *
 */
public class Bus extends PublicTransport{
	/**
	 * Creates a bus with ID in the operation range (x1, y1, x2, y2)
	 * @param ID the unique ID of the public transportation vehicle
	 * @param x1 the x component of the first end point of the operation range
	 * @param y1 the y component of the first end point of the operation range
	 * @param x2 the x component of the second end point of the operation range
	 * @param y2 the y component of the second end point of the operation range
	 */
	public Bus(int ID, double x1, double y1, double x2, double y2) {
		super(ID, x1, y1, x2, y2);
	}
	
	/**
	 * Returns the price of a bus ride which is always 2.
	 * @return the price of a bus ride which is always 2 as an integer
	 */
	public double getPrice() {
		return 2;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

