
/* Name: Abdul Abedin
 * Student Number: 501023078
 * A class that manages the commands from the flightreservationsystem.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import javax.print.attribute.standard.Destination;

public class FlightManager {
	Map<String, Flight> flights = new TreeMap<String, Flight>();
	String[] cities = { "Dallas", "New York", "London", "Paris", "Tokyo" };
	final int DALLAS = 0;
	final int NEWYORK = 1;
	final int LONDON = 2;
	final int PARIS = 3;
	final int TOKYO = 4;

	int[] flightTimes = { 3, // Dallas
			1, // New York
			7, // London
			8, // Paris
			16,// Tokyo
	};

	ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>();
	ArrayList<String> flightNumbers = new ArrayList<String>();

	Random random = new Random();

	public FlightManager() throws IOException {
		File file = new File("flights.txt");
		/**Using scanner object to read the file which contains a list of flights */
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			/**Splitting the line by white space by using split method and storing the string objects in array line */
			String[] line = scanner.nextLine().split(" ");
			String[] flightnames = line[0].split("_");
			String flightname = flightnames[0] + " " + flightnames[1];
			/**Using the generateFlightNumber method to generate a flight number for the flight. */
			String flightNum = generateFlightNumber(flightname);
			String destination = line[1];
			/**Intializing the departure time using the string that was read from the flights.txt file */
			String departure = line[2];
			int passCap = Integer.parseInt(line[3]);
			/**Intializing the variable flighttime to zero. */
			int flighttime = 0;

			/**Using conditional statement to check if the destination is Dallas. If it is set the flight time accordingly. */
			if (destination.equals("Dallas")) {
				flighttime = 0;
			} else if (destination.equals("New_York")) {
				flighttime = 1;
			} 
			/**Using conditional statement to check if the destination is London. If it is set the flight time accordingly. */
			else if (destination.equals("London")) {
				flighttime = 2;
			} 
			else if (destination.equals("Paris")) {
				flighttime = 3;
			}
			/**Using conditional statement to check if the destination is Tokyo. If it is set the flight time accordingly. */
			 else if (destination.equals("Tokyo")) {
				flighttime = 4;
			}
			/**Adding a aircraft to the arraylist airplanes. */
			airplanes.add(new Aircraft(44, "Boeing 737"));
			airplanes.add(new Aircraft(80, "Airbus 320"));
			airplanes.add(new Aircraft(36, "Dash-8 100"));
			airplanes.add(new Aircraft(4, "Bombardier 5000"));
			airplanes.add(new Aircraft(100, 12, "Boeing 747"));

			Aircraft aircraftType = null;
			/**Using conditional statement to check if the passenger capacity of the flight is 4. If it is an aircraft with the appropriate capacity is assigned to that flight. */
			if (passCap == 4) {
				aircraftType = airplanes.get(3);
			} else if (passCap > 4 && passCap <= 36) {
				aircraftType = airplanes.get(2);
			} 
			/**Using conditional statement to check if the passenger capacity of the flight is within a specific range. If it is an aircraft with the appropriate capacity is assigned to that flight. */
			else if (passCap > 36 && passCap <= 44) {
				aircraftType = airplanes.get(0);
			} else if (passCap > 44 && passCap <= 80) {
				aircraftType = airplanes.get(1);
			} 
			/**Using conditional statement to check if the passenger capacity of the flight is within a specific range. If it is an aircraft with the appropriate capacity is assigned to that flight. */
			else if (passCap > 44 && passCap <= 80) {
				aircraftType = airplanes.get(1);
			} else if (passCap > 88 && passCap <= 100) {
				aircraftType = airplanes.get(4);
			}

			if (aircraftType == airplanes.get(4)) {
				/**Creating a new LongHaul flight object using the parameters that were found using the flights.txt file. */
				Flight flight = new LongHaulFlight(flightNum, flightname, destination, departure,
						flightTimes[flighttime], aircraftType);
				/**Adding the flight to the arraylist flights */
				flights.put(flightNum, flight);
			} else {
				/**Creating a new flight object using the parameters that were found using the flights.txt file. */
				Flight flight = new Flight(flightNum, flightname, destination, departure, flightTimes[flighttime],
						aircraftType);
				/**Adding the flight to the arraylist flights */
				flights.put(flightNum, flight);
			}
		}
	}
	/**private helper method generates and returns a flight number string*/
	private String generateFlightNumber(String airline) {
		String word1, word2;
		/**Using scanner object to read airline name */
		Scanner scanner = new Scanner(airline);
		word1 = scanner.next();
		word2 = scanner.next();
		/**Intializes the letter1 String to the first letter of the airline name */
		String letter1 = word1.substring(0, 1);
		String letter2 = word2.substring(0, 1);
		/**Converting letter1 to uppercase */
		letter1.toUpperCase();
		letter2.toUpperCase();

		// Generate random number between 101 and 300
		// boolean duplicate = false;
		int flight = random.nextInt(200) + 101;
		String flightNum = letter1 + letter2 + flight;
		return flightNum;
	}

	/**Declaring a method called printAllFlights() which iterates through the values of the flights mpa and prints out all the flights  */
	public void printAllFlights() {
		for (Flight f : flights.values())
			System.out.println(f);
	}

	public boolean seatsAvailable(String flightNum) throws FlightNotFoundException {
		/**Using conditional statement to check a flight with the flight number provided exists. If it doesnt, print a error message. */
		if (flights.containsKey(flightNum) == false) {
			throw new FlightNotFoundException("Flight " + flightNum + " " + "not found");
		}
		/**Using seatsAvailable method from flight class to check if there are seats available on the flight and then returning the result. */
		return flights.get(flightNum).seatsAvailable();
	}

	/**Declaring a method called reserveSeatOnFlight which reserves a seat on a flight for a passenger.  */
	public Reservation reserveSeatOnFlight(String flightNum, String name, String passport, String seat) throws DuplicatePassengerException, SeatOccupiedException, FlightNotFoundException {
		/**Using conditional statement to check a flight with the flight number provided exists. If it doesnt, print a error message. */
		if (flights.containsKey(flightNum) == false) {
			throw new FlightNotFoundException("Flight " + flightNum + " " + "not found");
		}
		/**Create a new flight object and intialize it to the flight with the sameflight number as provided by the user.  */
		Flight flight = flights.get(flightNum);
		/**Use conditional statement to check if the program was able to reserve a seat by calling the reserveSeat method. */
		if (!flight.reserveSeat(name, passport, seat)) {
			return null;
		}
		return new Reservation(flightNum, flight.toString(), name, passport, seat);
	}
	/**Declaring a method called cancelReservation which cancels a reseveration*/
	public boolean cancelReservation(Reservation res) throws PassengerNotInManifestException, FlightNotFoundException {
		/*Use conditional statement to check if a flight exists with the given flight number*/
		if (flights.containsKey(res.getFlightNum()) == false) {
			throw new FlightNotFoundException("Flight " + res.getFlightNum() + " " + "not found");
		}
		/**Creating a new flight object and intializing it to the flight with the same flight number as provided by the user. */
		Flight flight = flights.get(res.getFlightNum());
		/**Creating a new passenger object with the name and passport number of the reservation */
		Passenger m = new Passenger(res.name, res.passport);
		flight.getPassenger(res.name, res.passport);
		/**Calling the cancelSeat method and passing the passenger as the argument. */
		flight.cancelSeat(m);

		return true;
	}
	/**Declaring a method called printAllAircraft() which iterates through the arraylist airplanes and prints out all the different aircrafts*/
	public void printAllAircraft() {
		for (Aircraft craft : airplanes)
			craft.print();
	}
/**Declaring a method called printseat which prints all the seats of a flight using the flight number */
	public void printseat(String flightNum) throws FlightNotFoundException {
		/**Using conditional statement to check if the flight exists with the given flight number. If it doesn't print out a message. */
		if (flights.containsKey(flightNum) == false) {
			throw new FlightNotFoundException("Flight " + flightNum + " " + "not found");
		}
		else{
		/**Print out the seats of the flight using the printSeats method. */
		flights.get(flightNum).printSeats();
		}
	}

/**Declaring a method called printPassenger which prints all the passengers on a specific flight using the flight number */
	public void printPassenger(String flightNum) throws FlightNotFoundException {
		if (flights.containsKey(flightNum) == false) {
			throw new FlightNotFoundException("Flight " + flightNum + " " + "not found");
		}
		else{
		flights.get(flightNum).printPassenger();
		}
	}
}

class FlightNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	public FlightNotFoundException() {
	}
	/**Declaring a method called SeatOccupiedException which prints out the message that was passed to it. */
	public FlightNotFoundException(String message) {
		super(message);
	}

}
