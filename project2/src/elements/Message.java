
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;

/**
 * 
 * @author mustafa atay
 *
 */
public class Message {
	/**
	 * The total number of message objects created
	 */
	static int numOfMessages = 0;
	
	/**
	 * The identical number of this message. It starts from 0 and increases 1 by 1 for every new message.
	 */
	private int id ;
	
	/**
	 * The message body
	 */
	private String body ;
	
	/**
	 * User who sent this message
	 */
	private User sender ;
	
	/**
	 * User to who this message is sent
	 */
	private User receiver ;
	
	/**
	 * The time this message is sent. It is -1 if this message hasn't been sent yet.
	 */
	private int timeStampSent = -1;
	
	/**
	 * The time this message is read. It is -1 if this message hasn't been read yet.
	 */
	private int timeStampRead = -1;
	
	/**
	 * The time this message is received. It is -1 if this message hasn't been received yet.
	 */
	private int timeStampReceived = -1;
	
	
	/**
	 * Creates a message
	 * @param sender the user who sent the message
	 * @param receiver the user to who the message sent
	 * @param body the message body
	 * @param server the server where the message goes
	 * @param time the time which the message is sent
	 */
	public Message(User sender , User receiver , String body , Server server , int time) {
		this.sender = sender;
		this.receiver = receiver;
		this.body = body;
		this.id = numOfMessages++;
		this.timeStampSent = time;
		server.addMessage(this);
	}
	
	/**
	 * Compares the messages according to their body lengths
	 * @param o Compared message
	 * @return	1 if this message is longer, -1 otherwise, 0 if they are equally long
	 */
	public int compareTo(Message o) {
		int length1 = this.body.length();
		int length2 = o.body.length();
		if(length1 > length2) {
			return 1;
		}
		else if (length1 < length2) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * returns whether two messages are the same object
	 * @param o the compared object
	 * @return true if they are the same object, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		return this == o;
	}
	
	/**
	 * Returns a String of the message as the following format:
	 * From: <sender_id> to: <user_id>
	 * Received: <time_stamp1> Read: <time_stamp2>
	 * <message_body>
	 * return a String
	 */
	@Override
	public String toString() {
		String received = "";
		String read = "";
		if (timeStampReceived != -1) {
			received = Integer.toString(timeStampReceived);
		}
		if (timeStampRead != -1) {
			read = Integer.toString(timeStampRead);
		}
		return "\tFrom: " + sender.getId() + " to: " + receiver.getId() 
				+ "\n\tReceived: " + received + " Read: " + read
				+ "\n\t" + body;
	}
	
	/**
	 * Sets the time when the message sent
	 * @param timeStampSent the time when the message sent
	 */
	public void setTimeStampSent(int timeStampSent) {
		this.timeStampSent = timeStampSent;
	}

	/**
	 * Sets the time when the message read
	 * @param timeStampRead the time when the message read
	 */
	public void setTimeStampRead(int timeStampRead) {
		this.timeStampRead = timeStampRead;
	}
	
	/**
	 * Sets the time when the message received
	 * @param timeStampReceived the time when the message received
	 */
	public void setTimeStampReceived(int timeStampReceived) {
		this.timeStampReceived = timeStampReceived;
	}


	/**
	 * Returns the id of the message
	 * @return the id as an integer
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the user to who the message is sent
	 * @return a user object
	 */
	public User getReceiver() {
		return receiver;
	}
	
	/**
	 * Returns the user who sent the message
	 * @return a user object
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * Returns the body of the message
	 * @return the body as a String
	 */
	public String getBody() {
		return body;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

