
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import elements.*;
import java.io.*;
import java.util.*;

/**
 * 
 * @author mustafa atay
 *
 */
public class Inbox extends Box{
	/**
	 * The messages are received from the server but haven't been read yet.
	 */
	private Stack<Message> unread = new Stack<Message>();
	
	/**
	 * The messages that are read
	 */
	private Queue<Message> read = new LinkedList<Message>();
	
	
	/**
	 * Creates a inbox whose owner is the given user
	 * @param user the owner of the inbox
	 */
	public Inbox(User user){
		super(user);
	}
	
	/**
	 * Receives the messages which are sent to the owner of this inbox
	 * from the given server, marks their received time as the given 
	 * time and add the received messages to unread messages stack
	 * If a message sender is not a friend of the owner of this inbox,
	 * this message can't be received.
	 * @param server the server where the messages will be searched
	 * @param time the time when messages are received
	 */
	public void receiveMessages(Server server , int time ) {
		Queue<Message> messages = server.giveMessages(owner);
		while(!messages.isEmpty()) {
			Message m = messages.poll();
			m.setTimeStampReceived(time);
			unread.add(m);
		}
	}
	
	/**
	 * Reads the given number of messages from top of the unread messages stack.
	 * The read times of the messages which are read are updated as following:
	 * The fist read mesages's is the given time. for the second messages it is 
	 * one more of the given time and continues incrementally as that.
	 * @param num the number of messages which is read
	 * @param time the time when the first message is read
	 * @return
	 */
	public int readMessages( int num , int time ) {
		if(num == 0) {
			num = unread.size();
		}
		int i = 0;
		for(; i<num && !unread.isEmpty(); i++) {
			Message m = unread.pop();
			m.setTimeStampRead(time+i);
			read.add(m);
		}
		
		// the operation takes time even if no messages are read.
		if(i==0) {
			return 1;
		}
		return i;
	}	
	
	/**
	 * Reads all messages sent from the given sender.
	 * The read times of the messages which are read are updated as following:
	 * The fist read mesages's is the given time. for the second messages it is 
	 * one more of the given time and continues incrementally as that.
	 * @param sender a user
	 * @param time the time when the first message is read
	 * @return
	 */
	public int readMessages(User sender , int time) {
		Stack<Message> messages = new Stack<Message>();
		int i = 0;
		while(!unread.isEmpty()) {
			Message m = unread.pop();
			if(m.getSender()==sender) {
				m.setTimeStampRead(time+i++);
				read.add(m);
			}
			else {
				messages.add(m);
			}
		}
		while(!messages.isEmpty()) {
			unread.add(messages.pop());
		}
		
		// the operation takes time even if no messages are read.
		if(i==0) {
			return 1;
		}
		return i;
	}
	
	/**
	 * Reads a specific message with the given id number. Then updates the
	 * read time of the message.
	 * @param msgId the id number of the message
	 * @param time the time the message is read
	 */
	public void readMessage( int msgId , int time) {
		Stack<Message> messages = new Stack<Message>();
		while(!unread.isEmpty()) {
			Message m = unread.pop();
			if(m.getId() == msgId) {
				m.setTimeStampRead(time);
				read.add(m);
				break;
			}
			messages.add(m);		
		}
		while(!messages.isEmpty()) {
			unread.add(messages.pop());
		}
	}
	
	/**
	 * Prints the last message read using the given PrintStream
	 * @param printer a PrintStream object to print the message
	 */
	public void printMessage(PrintStream printer) {
		// if there is no messages do nothing
		if(read.isEmpty()) {
			return;
		}
		Message first = read.poll(); // the message comes one after the result messages (needed)
		read.add(first);
		Message result = first; // if there is only one message, first is also result
		Message m = read.peek();
		
		// Searches for the last message in read queue. 
		while(m != first) {
			result = m;
			m = read.poll();
			read.add(m);
			m = read.peek();
		}
		printer.println(result.toString());
	}
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

