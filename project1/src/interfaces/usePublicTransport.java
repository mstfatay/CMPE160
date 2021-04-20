
package interfaces;

import locations.*;
import vehicles.*;

public interface usePublicTransport {
	/**
	 * The passenger goes to location l using given public transportation vehicle p.
	 * @param p The public transportation vehicle which the passenger uses.
	 * @param l the location where passenger goes
	 */
	public void ride(PublicTransport p, Location l);
	
	/**
	 * Adds given amount of money to card balance.
	 * @param amount the amount of money to be added to the card balance
	 */
	public void refillCard(double amount);
}

