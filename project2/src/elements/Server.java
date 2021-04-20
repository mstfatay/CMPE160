
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;

import java.io.*;
import java.util.*;	

/**
 * 
 * @author mustafa atay
 *
 */

public class Server{
	/**
	 * The max amount of sum of the characters of the bodies of the messages this server can contain.
	 * When this boundary is exceed, the messages are deleted.
	 */
	private long capacity;
	
	/**
	 * The amount of sum of the characters of the bodies of the messages this server contains.
	 */
	private long currentSize = 0;
	
	/**
	 * Explains what was the last showed warning. 0 = noWoarning, 1 = %50Warning, 2 = %80Warning.
	 */
	private int warningState = 0;
	
	/**
	 * The messages are sent to this server are kept here.
	 */
	private Queue<Message> msgs = new LinkedList<Message>();
	
	/**
	 * Creates a server with the given amount of capacity
	 * @param capacity The max number of letters of message bodies this server can have
	 */
	public Server (long capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Examines the rate of the server's fullness.
	 * When the server gets 50% or 80% full for the first time prints a warning message.
	 * when the server gets 100% full prints a warning message and flushes the server.
	 * @param printer a PrintStream object which is used to print the warnings
	 */
	public void checkServerLoad(PrintStream printer) {
		double occupancyRate = (double) currentSize / capacity;
		if(occupancyRate >= 1) {
			printer.println("Server is full. Deleting all messages...");
			flush();
		}
		else if(occupancyRate >= 0.8) {
			// if last showed warning message isn't 2 (%80 full), show the second warning message.
			if(warningState != 2) {
				printer.println("Warning! Server is 80% full.");
				warningState = 2;
			}
		}
		else if(occupancyRate >= 0.5) {
			// if last showed warning message isn't 1 (%50 full), show the first warning message.
			if( warningState != 1) {
				printer.println("Warning! Server is 50% full.");
				warningState = 1;
			}
		}
		else if(occupancyRate < 0.5){
			// No warning messages are needed, so update the warning state as default.
			warningState = 0;
		}
	}
	
	/**
	 * Adds the given message to the server's memory
	 * @param m a message object
	 */
	public void addMessage(Message m) {
		currentSize += m.getBody().length();
		msgs.add(m);
	}
	
	/**
	 * returns all the messages of the given user if the user is friend with 
	 * the message sender.
	 * @param receiver an user whose messages will be searched
	 * @return a Queue of messages
	 */
	public Queue<Message> giveMessages(User receiver){
		Queue<Message> messages = new LinkedList<Message>();	
		int size = msgs.size();
		
		// Gets and removes the needed messages from queue and keeps the order of the remaining messages.
		for(int i = 0; i<size ; i++) {
			Message m = msgs.poll();
			
			// Only give messages which are sent to given receiver and if sender and receiver are friends.
			if(m.getReceiver() == receiver && receiver.isFriendsWith(m.getSender())){
				messages.add(m);
				currentSize -= m.getBody().length();
			}
			else {
				msgs.add(m);
			}
		}
		return messages;
	}

	/**
	 * Returns the current size of the server.
	 * The current size of the server is the sum of the length of the messages the
	 * server keeps.
	 * @return the current size of the server as a long
	 */
	public long getCurrentSize() {
		return currentSize;
	}
	
	/**
	 * Deletes all the messages the server keeps
	 */
	public void flush() {
		msgs.clear();
		currentSize = 0;
		warningState = 0; // Warning state is default. (currentsize < capacity)
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

