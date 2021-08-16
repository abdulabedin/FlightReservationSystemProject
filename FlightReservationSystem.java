
/* Name: Abdul Abedin
 * Student Number: 501023078
 * Flight System for one single day at YYZ (Print this in title) Departing flights!!
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightReservationSystem {
	public static void main(String[] args) {
		try{
		FlightManager manager = new FlightManager();
		// List of reservations that have been made
		ArrayList<Reservation> myReservations = new ArrayList<Reservation>(); // my flight reservations
		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		

		while (scanner.hasNextLine()) {
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) {
				System.out.print("\n>");
				continue;
			}
		
			// The command line is a scanner that scans the inputLine string
			Scanner commandLine = new Scanner(inputLine);
			// The action string is the command to be performed (e.g. list, cancel etc)
			String action = commandLine.next();

			if (action == null || action.equals("")) {
				System.out.print("\n>");
				continue;
			}

			else if (action.equals("Q") || action.equals("QUIT"))
				return;

			else if (action.equalsIgnoreCase("LIST")) {
				manager.printAllFlights();
				// Reserve a flight based on Flight number, Passenger Name, Passport Number and seat
			} else if (action.equalsIgnoreCase("RES"))

			{
				/** Define a try block to check for exceptions */
				try {
					String flightNum = null;
					String passengerName = "";
					String passport = "";
					String seat = "";
					/** Get the flight number string from the commandLine scanner (used hasNext() to check if there is a flight number string entered) */
					if (commandLine.hasNext()) {
						flightNum = commandLine.next();
					}
					/** Get the passenger name string from the commandLine scanner (used hasNext() to check if there is a passenger name string entered) */
					if (commandLine.hasNext()) {
						passengerName = commandLine.next();
					}
					/** Get the passport number string from the commandLine scanner (used hasNext() to check if there is a passport number string entered) */
					if (commandLine.hasNext()) {
						passport = commandLine.next();
					}
					/** Get the seat string from the commandLine scanner (used hasNext() to check if there is a seat string entered) */
					if (commandLine.hasNext()) {
						seat = commandLine.next();
						/** Create a new reservation object and call the reserveSeatonFlight method and store the returning object to the new reservation object. */
						Reservation res = manager.reserveSeatOnFlight(flightNum, passengerName, passport, seat);
						if (res != null) {
							/** Add reservation to the arraylist myreservation. Then print the reservation. */
							myReservations.add(res);
							res.print();
						} 
					}
				}
				/** Using catch block to catch any DuplicatePassenger exceptions. If a exeception is found the program prints a message. */
				catch (DuplicatePassengerException exception) {
					System.out.println((exception.getMessage()));
				}
				/** Using catch block to catch any SeatOccupied exceptions. If a exeception is found the program prints a message. */
				catch (SeatOccupiedException exception) {
					System.out.println((exception.getMessage()));
				}
				catch (FlightNotFoundException exception) {
					System.out.println(exception.getMessage());
				}

			}
			/** Cancel an existing reservation */
			else if (action.equalsIgnoreCase("CANCEL")) {
				/** Define a try block to check for exceptions */
				try {
					Reservation res = null;

					String flightNum = null;
					String passengerName = "";
					String passport = "";
					/** Get the flight number string from the commandLine scanner (used hasNext() to check if there is a flight number string entered) */
					if (commandLine.hasNext()) {
						flightNum = commandLine.next();
					}
					/** Get the passenger name string from the commandLine scanner (used hasNext() to check if there is a passenger name string entered) */
					if (commandLine.hasNext()) {
						passengerName = commandLine.next();
					}
					/** Get the passport number string from the commandLine scanner (used hasNext() to check if there is a passport number string entered) */
					if (commandLine.hasNext()) {
						passport = commandLine.next();
						/** Intialize the res object by creating a new reservation using the flight number, passenger name and passport number provided by the user */
						res = new Reservation(flightNum, passengerName, passport);
						/** Call the cancelReservation method and pass the reservation object as the argument to cancel the reservation */
						manager.cancelReservation(res);
						int newss = myReservations.indexOf(res);
						/** Remove the reservation from the myreservations arraylist */
						myReservations.remove(res);

					}

				}
				/** Using catch block to catch any PassengerNotInManifest exceptions. If a exeception is found the program prints a message. */
				catch (PassengerNotInManifestException exception) {

					System.out.println(exception.getMessage());
				}
				catch (FlightNotFoundException exception) {
					System.out.println(exception.getMessage());
				}
			}
			/** Print the seats of a flight using the flight number */
			else if (action.equalsIgnoreCase("SEATS")) {
				try{
				String flightNum = "";
				/** Get the flight number string from the commandLine scanner (used hasNext() to check if there is a flight number string entered) */
				if (commandLine.hasNext()) {
					flightNum = commandLine.next();
					/** Call the printseat method to print out all the seats on the flight */
					manager.printseat(flightNum);

				}
			}
			/** Using catch block to catch any FlightNotFound exceptions. If a exeception is found the program prints a message. */
			catch (FlightNotFoundException exception) {
				System.out.println(exception.getMessage());
			}
			}
			// Print all the reservations in array list myReservations
			else if (action.equalsIgnoreCase("MYRES")) {
				/** Iterate through the myreservation arraylist and print the reservations by calling the print() method */
				for (Reservation myres : myReservations)
					myres.print();
			}
			/** Print all the passengers on a flight using the flight number */
			else if (action.equalsIgnoreCase("pasman")) {
				try{
				String flightNum = "";
				/** Get the flight number string from the commandLine scanner (used hasNext() to check if there is a flight number string entered) */
				if (commandLine.hasNext()) {
					flightNum = commandLine.next();
					/** Call the printPassenger method to print out all the passengers on the flight */
					manager.printPassenger(flightNum);
				}
			}
			/** Using catch block to catch any FlightNotFound exceptions. If a exeception is found the program prints a message. */
			catch (FlightNotFoundException exception) {
				System.out.println(exception.getMessage());
			}
			}
			System.out.print("\n>");
		}
	}
	/** Using catch block to catch any IO exceptions. If a exeception is found the program prints a message. */
	catch (IOException exception){
		System.out.println(exception.getMessage());
	}
}
	
}
