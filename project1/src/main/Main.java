
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.util.*;
import vehicles.*;
import java.io.*;
import passengers.*;
import locations.*;

/**
 * 
 * @author Mustafa Atay
 *
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(new File(args[0])); // input is read from a file name of which is given in args
		PrintStream output = new PrintStream(new File(args[1])); // output is written to a file name of which is given in args 
		
		//Scanner input = new Scanner(System.in);
		

		ArrayList<Passenger> passengers = new ArrayList<Passenger>(); // the list of passengers
		ArrayList<Location> locations = new ArrayList<Location>(); // the list of locations
		ArrayList<PublicTransport> vehicles = new ArrayList<PublicTransport>(); // the list of public transportation vehicles
		
		Location l = new Location(0, 0, 0); // The first location is always (0,0).
		locations.add(l);
		Location.count++;
		
		int operations = input.nextInt(); // operation count
		input.nextLine(); // Go to the next line.
		
		for(int i = 0; i<operations; i++) {
			String line = input.nextLine();
			Scanner inputLine = new Scanner(line);
			
			int code = inputLine.nextInt(); // first number of a input line
			
			// creates passenger
			if(code==1) { 
				createPassenger(inputLine, passengers, l);
			}
			
			// creates location
			else if(code == 2) { 
				double x = inputLine.nextDouble();
				double y = inputLine.nextDouble();
				locations.add(new Location(Location.count++,x,y)); // a location is created and added to the list of locations
				
			}
			
			// creates public transportation vehicle
			else if(code == 3) { 
				int a = inputLine.nextInt();
				double b = inputLine.nextDouble(); //x1
				double c = inputLine.nextDouble(); //y1
				double d = inputLine.nextDouble(); //x2
				double e = inputLine.nextDouble(); //y2
				
				if(a==1) {
					PublicTransport p = new Bus(PublicTransport.count++, b,c,d,e);  // a bus is created
					vehicles.add(p); // the vehicle is added to the list of vehicles
				}
				else if(a==2){
					PublicTransport p = new Train(PublicTransport.count++, b,c,d,e); // a train is created
					vehicles.add(p); // the vehicle is added to the list of vehicles 
				}
			}
			
			// a passenger rides or drives
			else if(code == 4) { 
				int a = inputLine.nextInt(); // passenger ID
				int b = inputLine.nextInt(); // location ID
				int c = inputLine.nextInt(); // 1 for bus, 2 for train, 3 for car
				Passenger pGoing = passengers.get(a);
				Location lGO = locations.get(b);
				
				// the passenger drives
				if(c == 3) {
					pGoing.drive(lGO); // the passenger drives to the location lGO
				}
				
				// the passenger rides
				else {
					int d = inputLine.nextInt(); // vehicle ID
					PublicTransport tGo = vehicles.get(d);
					
					// c is 1 for bus
					if(c==1 && (tGo instanceof Bus)) {
						pGoing.ride(tGo, lGO); // the passenger rides to the location lGO via vehicle tGO
					}
					
					// c is 2 for train
					else if(c==2 && (tGo instanceof Train)) {
						pGoing.ride(tGo, lGO); // the passenger rides to the location lGO via vehicle tGO
					}	
				}
			}
			
			// a passenger purchases a car
			else if(code == 5) {
				int a = inputLine.nextInt(); // passenger ID
				double b = inputLine.nextDouble(); // the car's fuel consumption amount
				Passenger p = passengers.get(a);
				p.purchaseCar(b); // the passenger purchases a car with the given fuel consumption amount
			}
			
			// a passenger refuels his/her car
			else if(code == 6) {
				int a = inputLine.nextInt(); // passenger ID
				double b = inputLine.nextDouble(); // the amount of fuel
				Passenger p = passengers.get(a); 
				p.refuel(b); // the passenger refuels his/her car
			}
			
			// a passenger refills his/her card
			else if(code == 7) {
				int a = inputLine.nextInt(); // passenger ID
				double b = inputLine.nextDouble(); // the amount of money that is refilled
				Passenger p = passengers.get(a); 
				p.refillCard(b); // the passenger refills his/her card
			}
		}
		
		// write output
		for(int i = 0; i<locations.size(); i++) {
			locations.get(i).write(output); // writes a location's coordinates and passengers on that location
		}
	}
	
	// a function to create passenger
	public static void createPassenger(Scanner inputLine, ArrayList<Passenger> passengers, Location l) {
		String a = inputLine.next(); // D for a discounted passenger, S for a standard passenger
		int b = inputLine.nextInt(); 
		int c = inputLine.nextInt();
		
		// if the passenger has a  car
		if(inputLine.hasNextDouble()) {
			double d = inputLine.nextDouble(); // the amount of fuel consumption of the car
			if(a.startsWith("D")) {
				Passenger p = new DiscountedPassenger(Passenger.count++, l, d); // creates a discounted passenger with a car
				passengers.add(p);
			}
			else if(a.startsWith("S")) {
				Passenger p = new StandardPassenger(Passenger.count++, l, d); // creates a standard passenger with a car
				passengers.add(p);
			}
		}
		
		// if the passenger doesn't have a car
		else {
			if(a.startsWith("D")) {
				Passenger p = new DiscountedPassenger(Passenger.count++, b==1, l); // creates a discounted passenger without a car
				passengers.add(p);
			}
			else if(a.startsWith("S")) {
				Passenger p = new StandardPassenger(Passenger.count++, b==1, l); //creates a standard passenger without a car
				passengers.add(p);
			}
		}
		return;
	}

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

