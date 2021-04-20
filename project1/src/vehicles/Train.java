
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;

/**
 * 
 * @author Mustafa Atay
 *
 */
public class Train extends PublicTransport{
	/**
	 * Creates a train with ID in the operation range (x1, y1, x2, y2)
	 * @param ID the unique ID of the public transportation vehicle
	 * @param x1 the x component of the first end point of the operation range
	 * @param y1 the y component of the first end point of the operation range
	 * @param x2 the x component of the second end point of the operation range
	 * @param y2 the y component of the second end point of the operation range
	 */
	public Train(int ID, double x1, double y1, double x2, double y2) {
		super(ID, x1, y1, x2, y2);
	}
	
	/**
	 * Returns the price of a train ride which is given distance long.
	 * @param distance
	 * @return the price of the train ride
	 */
	public double getPrice(double distance) {
		int price = ((int)(distance / 15))*5;
		
		// rounds the price
		if (distance % 15 >= 7.5) {
			price += 5;
		}
		return price;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

