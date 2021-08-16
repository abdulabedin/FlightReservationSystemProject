
/* Name: Abdul Abedin
 * Student Number: 501023078
 * Class to model an airline flight. In this simple system, all flights originate from Toronto
 * This class models a simple flight that has only economy seats
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class Flight extends Exception {

	private static final long serialVersionUID = -7194670998851888750L;

	public enum Status {
		DELAYED, ONTIME, ARRIVED, INFLIGHT
	};

	public static enum Type {
		SHORTHAUL, MEDIUMHAUL, LONGHAUL
	};

	public static enum SeatType {
		ECONOMY, FIRSTCLASS, BUSINESS
	};

	private String flightNum;
	private String airline;
	private String origin, dest;
	private String departureTime;
	private Status status;
	private int flightDuration;
	protected Aircraft aircraft;
	protected int numPassengers;
	protected Type type;
	protected ArrayList<Passenger> manifest;
	protected TreeMap<String, Passenger> seatMap;

	protected Random random = new Random();

	public Flight() {
		this.flightNum = "";
		this.airline = "";
		this.dest = "";
		this.origin = "Toronto";
		this.departureTime = "";
		this.flightDuration = 0;
		this.aircraft = null;
		numPassengers = 0;
		status = Status.ONTIME;
		type = Type.MEDIUMHAUL;
		manifest = new ArrayList<Passenger>();
		seatMap = new TreeMap<String, Passenger>();
	}

	public Flight(String flightNum) {
		this.flightNum = flightNum;
	}

	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration,
			Aircraft aircraft) {
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = aircraft;
		numPassengers = 0;
		status = Status.ONTIME;
		type = Type.MEDIUMHAUL;
		manifest = new ArrayList<Passenger>();
		seatMap = new TreeMap<String, Passenger>();
	}

	public Type getFlightType() {
		return type;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getFlightDuration() {
		return flightDuration;
	}

	public void setFlightDuration(int dur) {
		this.flightDuration = dur;
	}

	public int getNumPassengers() {
		return numPassengers;
	}

	public void setNumPassengers(int numPassengers) {
		this.numPassengers = numPassengers;
	}

	public boolean seatsAvailable() {

		return numPassengers < aircraft.numEconomySeats;
	}
    /**Declaring a method called cancelSeat which is used to cancel a seat on a flight.  */
	public boolean cancelSeat(Passenger p) throws PassengerNotInManifestException {
		/**Using conditional statement to check if the manifest arraylist contains the passenger that user wants to cancel the seat for. */
		if (manifest.contains(p)) {
			int n = manifest.indexOf(p);
			/**Using the get() method of arraylist to get the seat number that the passenger has reserved. */
			String seas = manifest.get(n).getSeat();
			/**Removing passenger from the arraylist manifest. */
			manifest.remove(manifest.get(n));
			/**Removing the reservation from the occupied seats. */
			seatMap.remove(seas);
			if (numPassengers > 0)
				numPassengers--;
			return true;
		} else {
			/**Throwing a exception if the passenger is not found */
			throw new PassengerNotInManifestException(
					"Passenger " + p.getName() + " " + p.getPassport() + " " + "not found");
		}
	}
	/**Declaring a method called reserveSeat which is used to reserve a seat on a flight.  */
	public boolean reserveSeat(String name, String passport, String seat) throws DuplicatePassengerException, SeatOccupiedException {
		Passenger p = new Passenger(name, passport, seat);
		/**Using conditional statement to check if the passenger already exists. If it does the program throws a DuplicatePassenger exception */
		if (manifest.indexOf(p) >= 0) {
			throw new DuplicatePassengerException("Duplicate Passenger " + p.getName() + " " + p.getPassport());
		}
		numPassengers++;
		if (seatMap != null) {
			/**Using conditional statement to check if the seat is already occupied. If it is the program throws a SeatOccupied exception */
			if (seatMap.containsKey(seat) == true) {
				throw new SeatOccupiedException("Seat " + seat + " already occupied");
			}
		}
		if (seat.contains("+")){
			p.setSeatType("First Class");
		}
		else{
			p.setSeatType("Economy");
		}
		/**Adding passenger to the arraylist manifest */
		manifest.add(p);
		/**Adding the seat the passenger reserved to seatMap */
		seatMap.put(seat, p);
		return true;
	}
	/**Declaring a method called equals that checks if two objects are equal by compating there flight number*/
	public boolean equals(Object other) {
		Flight otherFlight = (Flight) other;
		return this.flightNum.equals(otherFlight.flightNum);
	}

	public String toString() {
		return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime
				+ "\t Duration: " + flightDuration + "\t Status: " + status;
	}
	/**Declaring a method called printPassenger() that prints all the passengers on the flight */
	public void printPassenger() {
	/**Using for loop to iterate through the manifest arraylist and printing the passengers. */
		for (Passenger e : manifest) {
			System.out.println(e.toString());
		}
	}
	/**Declaring a method called getPassenger which returns a passenger with the given name and passport number.  */
	public Passenger getPassenger(String name, String passport) {
		/**Using for loop to iterate through the arraylist manifest */
		for (Passenger e : manifest) {
			if (e.getName().equals(name) && e.getPassport().equals(passport)) {
				return e;
			}
		}
		return null;
	}
	/**Declaring a method called printSeats() which prints out the seats of the aircraft */
	public void printSeats() {
		String[][] news = aircraft.layout(aircraft.numEconomySeats + aircraft.numFirstClassSeats);
		/**Using for loop to iterate through the seatlayout array.  */
		for (int row = 0; row < news.length; row++) {
			System.out.println("");
			for (int col = 0; col < news[row].length; col++) {
				/**Using conditional statement to check if the seat is alredy occupied. If it is print XX */
				if (seatMap.containsKey(news[row][col])) {
					System.out.print("\t" + "XX");
				} else {
					System.out.print("\t" + news[row][col]);
				}
			}
		}
		System.out.println();
		System.out.println("\n" + "\t" + "XX = Occupied" + "\t" + "+ = First Class");
	}
}

/**Declaring a exception class called DuplicatePassengerException which will deal with exceptions caused by duplicate passengers. */
class DuplicatePassengerException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicatePassengerException() {
	}
/**Declaring a method called DuplicatePassengerException which prints out the message that was passed to it. */
	public DuplicatePassengerException(String message) {
		super(message);
	}
}
/**Declaring a exception class called PassengerNotInManifestException which will deal with exceptions caused by the program not being able to find the passenger. */
class PassengerNotInManifestException extends Exception {

	private static final long serialVersionUID = 1L;

	public PassengerNotInManifestException() {
	}
/**Declaring a method called PassengerNotInManifestException which prints out the message that was passed to it. */
	public PassengerNotInManifestException(String message) {
		super(message);
	}

}
/**Declaring a exception class called SeatOccupiedException which will deal with exceptions caused by seats already being occupied. */
class SeatOccupiedException extends Exception {

	private static final long serialVersionUID = 1L;

	public SeatOccupiedException() {
	}
	/**Declaring a method called SeatOccupiedException which prints out the message that was passed to it. */
	public SeatOccupiedException(String message) {
		super(message);
	}

}
