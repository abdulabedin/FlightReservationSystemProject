/* Name: Abdul Abedin
 * Student Number: 501023078
 * This class models an passenger with a name, passport number, and a flight number */

public class Passenger {
	/** Declare instance variables */
	private String name;
	private String passport;
	private String seat;
	private String seatType;

	/** Constructor to initialize instance variables */
	public Passenger(String name, String passport, String seat) {
		this.name = name;
		this.passport = passport;
		this.seat = seat;
	}

	/** Constructor to initialize instance variables */
	public Passenger(String name, String passport) {
		this.name = name;
		this.passport = passport;
	}

	/** Checks if two passenger objects have the same name and passport number */
	public boolean equals(Object other) {
		Passenger otherP = (Passenger) other;
		return name.equals(otherP.name) && passport.equals(otherP.passport);
	}

	/** A method that returns a passenger's seat type */
	public String getSeatType() {
		return seatType;
	}

	/** A method that sets a passenger's seat type */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	/** A method that returns a passenger's name */
	public String getName() {
		return name;
	}

	/** A method that sets a passenger's name */
	public void setName(String name) {
		this.name = name;
	}

	/** A method that returns a passenger's passport number */
	public String getPassport() {
		return passport;
	}

	/** A method that sets a passenger's passport number */
	public void setPassport(String passport) {
		this.passport = passport;
	}

	/** A method that returns a passenger's seat number */
	public String getSeat() {
		return seat;
	}

	/** A method that sets a passenger's seat number */
	public void setSeat(String seat) {
		this.seat = seat;
	}

	/** A method that returns a passenger's name, passport number, and seat number */
	public String toString() {
		return name + " " + passport + " " + seat;
	}
}
