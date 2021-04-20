
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package executable;

import java.io.*;
import elements.*;
import java.util.*;

/**
 * 
 * @author mustafa atay
 *
 */
public class Main {
	public static void main (String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(args[0])); // input is read from a file name of which is given in args
		PrintStream printer = new PrintStream(new File(args[1]));
		
		String line = scan.nextLine(); // scans the first line of the input
		Scanner lineScan = new Scanner(line);
		int numOfUsers = lineScan.nextInt(); // total number of users
		int numOfQuaries = lineScan.nextInt(); // total number of quaries
		long capacityOfServer = lineScan.nextLong(); // capacity of the server
		int time = 0; // time of the program (at the beginning it is 0)
		
		User[] users = new User[numOfUsers]; // users array

		// users are created
		for(int i=0; i<numOfUsers; i++) {
			users[i] = new User(i);
		}
		Server server = new Server(capacityOfServer); // the server is created
		
		// quaries are handled here
		for(int i=0; i<numOfQuaries; i++) {
			line = scan.nextLine();
			lineScan = new Scanner(line);
			int type = lineScan.nextInt();
			//sending a message
			if(type == 0) {
				int user1 = lineScan.nextInt();
				int user2 = lineScan.nextInt();
				String text = lineScan.nextLine();
				text = text.substring(1);
				users[user1].sendMessage(users[user2], text, time, server);
				time ++; // the operation takes time
				//String message = "User#" + user1 + " sends " + "user#" + user2 + " the message \"" + text + "\"."; 
				//printer.println(message);
				server.checkServerLoad(printer); // server load is changed
			}
			//Receive a message
			else if(type == 1) {
				int user1 = lineScan.nextInt();
				users[user1].getInbox().receiveMessages(server, time);
				time ++; // the operation takes time
				//String message = "User#" + user1 + " receives all the messages that are sent to him/her from server.";
				//printer.println(message);
				server.checkServerLoad(printer); // server load could be changed
			}
			//read a message
			else if(type == 2) {
				int user1 = lineScan.nextInt();
				int num = lineScan.nextInt();
				int added = users[user1].getInbox().readMessages(num, time);
				time += added; // the operation takes time
			}
			// read all the messages from a sender
			else if(type == 21) {
				int user1 = lineScan.nextInt();
				int user2 = lineScan.nextInt();
				int added = users[user1].getInbox().readMessages(users[user2], time);
				time += added; // the operation takes time
			}
			// read a specific message
			else if(type == 22) {
				int user1 = lineScan.nextInt();
				int id = lineScan.nextInt();
				users[user1].getInbox().readMessage(id, time);
				time ++; // the operation takes time
			}
			//add a friend
			else if(type == 3) {
				int user1 = lineScan.nextInt();
				int user2 = lineScan.nextInt();
				users[user1].addFriend(users[user2]);
				time ++; // the operation takes time
			}
			//remove a friend
			else if(type == 4) {
				int user1 = lineScan.nextInt();
				int user2 = lineScan.nextInt();
				users[user1].removeFriend(users[user2]);
				time ++; // the operation takes time
			}
			// flush server
			else if(type == 5) {
				//printer.println("Deleting all massages...");
				server.flush();
				time ++; // the operation takes time
				server.checkServerLoad(printer); //server load is changed
			}
			// print the current size of the server
			else if(type == 6) {
				long num = server.getCurrentSize();
				String message = "Current load of the server is " + num + " characters.";
				printer.println(message);
				time ++; // the operation takes time
			}
			// print the last message a user has read
			else if(type == 61) {
				int user1 = lineScan.nextInt();
				users[user1].getInbox().printMessage(printer);
				time ++; // the operation takes time
			}
		}
		
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

