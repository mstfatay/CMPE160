
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import elements.*;
import java.util.*;

/**
 * 
 * @author mustafa atay
 *
 */
public class Outbox extends Box {
	/**
	 * The messages have been sent by the owner of this outbox
	 */
	private Queue<Message> sent = new LinkedList<Message>();
	
	/**
	 * Creates a outbox whose owner is the given user
	 * @param owner the owner of the outbox
	 */
	public Outbox(User owner) {
		super(owner);
	}
	
	/**
	 * Adds the given message to this outbox
	 * @param m the message to be added
	 */
	public void addMessage(Message m) {
		sent.add(m);
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

