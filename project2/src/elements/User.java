
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;

import boxes.*;
import java.util.*;

/**
 * 
 * @author mustafa atay
 *
 */

public class User {
	/**
	 * The identical number of this user. It starts from 0 and increases 1 by 1 for every new user.
	 */
	private int id ;
	
	/**
	 * The inbox this user has
	 */
	private Inbox inbox = new Inbox(this);
	
	/**
	 * The outbox this user have
	 */
	private Outbox outbox = new Outbox(this);
	
	/**
	 * This list contains all friends of this user. If a user is in this list, that user's friends list also
	 * contains this user.
	 */
	private ArrayList<User> friends = new ArrayList<User>();
	
	
	/**
	 * Creates a user with given id
	 * @param id identification number of the created user
	 */
	public User(int id) {
		this.id = id;
	}
	
	/**
	 * Adds the given user and this user eachother's friends list.
	 * @param other A user who is wanted to be added to friend list
	 */
	public void addFriend(User other) {
		if(!isFriendsWith(other)) {
			this.addFriendToMe(other);
			other.addFriendToMe(this);
		}
	}
	
	/**
	 * Removes the given user and this user eachother's friends list.
	 * @param other A user who is wanted to be removed from friend list
	 */
	public void removeFriend(User other) {
		if(isFriendsWith(other)) {
			this.removeFriendFromMe(other);
			other.removeFriendFromMe(this);
		}
	}
	
	/**
	 * Returns whether this user is friend with the given user
	 * @param other a user object
	 * @return	true if they are friends, false otherwise
	 */
	public boolean isFriendsWith(User other) {
		return this.friends.contains(other);
	}
	
	/**
	 * Sends a message
	 * @param receiver the receiver user of the message
	 * @param body the message body as a String
	 * @param time the send time of the messages
	 * @param server the server which the message will be sent
	 */
	public void sendMessage(User receiver, String body, int time, Server server) {
		Message m = new Message(this, receiver, body, server, time);
		outbox.addMessage(m);
	}
	
	/**
	 * Adds the given user to this user's friends list
	 * @param other a user
	 */
	private void addFriendToMe(User other) {
		this.friends.add(other);
	}
	
	/**
	 * Removes the given user to this user's friends list
	 * @param other a user
	 */
	private void removeFriendFromMe(User other) {
		this.friends.remove(other);
	}
	
	/**
	 * returns id of the user
	 * @return id as an integer
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * returns inbox of the user
	 * @return inbox as an Inbox object
	 */
	public Inbox getInbox() {
		return inbox;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

