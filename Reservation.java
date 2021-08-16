/* Name: Abdul Abedin
 * Student Number: 501023078
 * A simple class to model an electronic airline flight reservation
 */
public class Reservation {
	String flightNum;
	String flightInfo;
	String name;
	String passport;
	String seat;

	public Reservation(String flightNum, String info) {
		this.flightNum = flightNum;
		this.flightInfo = info;
	}

	public Reservation(String flightNum, String name, String passport) {
		this.flightNum = flightNum;
		this.name = name;
		this.passport = passport;
	}

	public Reservation(String flightNum, String info, String name, String passport, String seat) {
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.name = name;
		this.passport = passport;
		this.seat = seat;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public String getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(String flightInfo) {
		this.flightInfo = flightInfo;
	}

	/**Declaring a method called equals that check if two reservation objects are equal by comparing their flight number, name and passport number. */
	public boolean equals(Object other) {
		Reservation otherRes = (Reservation) other;
		return flightNum.equals(otherRes.flightNum) && name.equals(otherRes.name) && passport.equals(otherRes.passport);
	}

	public void print() {
		System.out.println(flightInfo + " " + name + " " + passport + " " + seat);
	}
}
