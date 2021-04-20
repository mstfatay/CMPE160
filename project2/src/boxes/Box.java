
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import elements.*;

/**
 * 
 * @author mustafa atay
 *
 */
abstract class Box{
	/**
	 * The owner of this box. The box is a child object of that user.
	 */
	protected User owner;
	
	
	/**
	 * Creates the box object with given owner.
	 * @param owner
	 */
	protected Box(User owner) {
		this.owner = owner;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

