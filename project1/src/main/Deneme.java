package main;

import locations.*;
import passengers.*;
import vehicles.*;

import java.util.*;

public class Deneme {

	public static void main(String[] args) {
		Location l = new Location(0, 0, 0);
		Location l2 = new Location (1,20,0);
		StandardPassenger p = new StandardPassenger(0, false, l);
		DiscountedPassenger p2 = new DiscountedPassenger(0, false, l);
		
		Train t = new Train(0, 0, 0 , 30,30);
		Bus bus = new Bus(0, 0, 0 , 30,30);
		
		System.out.println(p.getTotalPrice(bus, l2));
	}

}
