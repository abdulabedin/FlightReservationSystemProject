/* Name: Abdul Abedin
 * Student Number: 501023078
 * 
 * A Long Haul Flight is a flight that travels a long distance and has two types of seats (First Class and Economy)
 */

public class LongHaulFlight extends Flight {
	int firstClassPassengers;

	public LongHaulFlight(String flightNum, String airline, String dest, String departure, int flightDuration,
			Aircraft aircraft) {
		super(flightNum, airline, dest, departure, flightDuration, aircraft);
		type = Flight.Type.LONGHAUL;
	}

	public LongHaulFlight() {
		firstClassPassengers = 0;
		type = Flight.Type.LONGHAUL;
	}

	public int getPassengerCount() {
		return getNumPassengers() + firstClassPassengers;
	}

	public void setFirstClassPassengers(){
		firstClassPassengers++;
	}

	public String toString() {
		return super.toString() + "\t LongHaul";
	}

	public Type getFlightType() {
		return type;
	}
}