/* Name: Abdul Abedin
 * Student Number: 501023078
 * This class models an aircraft type with a model name, a maximum number of economy seats, and a max number of first class seats 
 */
public class Aircraft implements Comparable<Aircraft> {
	int numEconomySeats;
	int numFirstClassSeats;
	String[][] seatLayout;
	String model;

	public Aircraft(int seats, String model) {
		this.seatLayout = null;
		this.numEconomySeats = seats;
		this.numFirstClassSeats = 0;
		this.model = model;
	}

	public Aircraft(int economy, int firstClass, String model) {
		this.numEconomySeats = economy;
		this.numFirstClassSeats = firstClass;
		this.model = model;
	}

	public int getNumSeats() {
		return numEconomySeats;
	}

	public int getTotalSeats() {
		return numEconomySeats + numFirstClassSeats;
	}

	public int getNumFirstClassSeats() {
		return numFirstClassSeats;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void print() {
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: "
				+ numFirstClassSeats);
	}

	public int compareTo(Aircraft other) {
		/**Checking if current Aircraft object has the same number of economy seats as the other Aircraft object. If they do return the difference between number of first class seats of the current and other Aircraft object.  */
		if (this.numEconomySeats == other.numEconomySeats)
			return this.numFirstClassSeats - other.numFirstClassSeats;
		/**If the two objects dont have the same number of economy seats return the difference */
		return this.numEconomySeats - other.numEconomySeats;
	}
	/**Declaring a method called layout which will create a 2d array of seats in the aircraft */
	public String[][] layout(int seats) {
		int numFirstClass = this.numFirstClassSeats;
		int row;
		int coloumn;
		/**Using conditional statement to check if the number of seats is equal to 4. */
		if (seats ==4){
			 row = 2;
			 coloumn = 2;
		}
		else{
			 row = 4;
			 coloumn = seats / 4;
		}
		/**Intializing seatLayout array using the number of rows and columns that were calculated above */
		seatLayout = new String[row][coloumn];
		/**Using for loop to iterate through the array */
		for (int j = 0; j < coloumn; j++) {
			for (int i = 0; i <= row - 1; i++) {
				/**Using conditional statement to check if the row number is 0 */
				if (i == 0) {
					if (numFirstClass > 0) {
						seatLayout[i][j] = (j + 1) + "A+";
						numFirstClass--;
					} else {
						seatLayout[i][j] = (j + 1) + "A";
					}
				} 
				/**Using conditional statement to check if the row number is 1 */
				else if (i == 1) {
					/**Using conditional statement to check if number of firtclass seats is greater then zero. If it is add + to letter */
					if (numFirstClass > 0) {
						seatLayout[i][j] = (j + 1) + "B+";
						/**Decrement numFirstClass variable by 1 */
						numFirstClass--;
					} else {
						seatLayout[i][j] = (j + 1) + "B";
					}
				} 
				/**Using conditional statement to check if the row number is 2 */
				else if (i == 2) {
					/**Using conditional statement to check if number of firtclass seats is greater then zero. If it is add + to letter */
					if (numFirstClass > 0) {
						seatLayout[i][j] = (j + 1) + "C+";
						/**Decrement numFirstClass variable by 1 */
						numFirstClass--;
					} else {
						seatLayout[i][j] = (j + 1) + "C";
					}
				} 
				/**Using conditional statement to check if the row number is 3. */
				else if (i == 3) {
					/**Using conditional statement to check if number of firtclass seats is greater then zero. If it is add + to letter */
					if (numFirstClass > 0) {
						seatLayout[i][j] = (j + 1) + "D+";
						/**Decrement numFirstClass variable by 1 */
						numFirstClass--;
					} else {
						seatLayout[i][j] = (j + 1) + "D";
					}
				}
			}
		}
		return seatLayout;
	}
}
