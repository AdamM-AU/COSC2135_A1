public class Coach {
	private Object[] coach; // Storage Array
	private char[] seats; // Storage Array
	int counter; // Recyclable counter
	
	public Coach() {
		coach = new Object[9];
		// Init Variables
		// Import Database
	
	}
	
	public boolean coachAdd(String[] input) {
		this.coach[0] = input[0]; // Number of Rows
		this.coach[1] = Integer.parseInt(input[0]) * 4; // Total Number of Seats 
		this.coach[2] = input[1]; // Destination
		this.coach[3] = Double.parseDouble(input[2]); // Standard Price 
		this.coach[4] = Double.parseDouble(input[3]); // Pensioner Price
		this.coach[5] = Double.parseDouble(input[4]); // Frequent Price
		this.coach[6] = 0; // Standard Bookings
		this.coach[7] = 0; // Pensioner Bookings
		this.coach[8] = 0; // Frequent Bookings
		
		this.seats = new char[(int) coach[1] + 1]; // Create char array using total number of seats +1 :) 
		
		// Set seating as available
		this.counter = 0;
		while (this.seats.length > this.counter) {
			this.seats[counter] = '-';
			this.counter++;
		}
		this.seats[10] = 'S';
		this.seats[11] = 'S';
		this.coach[6] = 2;
		return true;
	}	
	
	// Modify Existing Coach
	public boolean coachModify(int ID) {
		
		return true;
	}
	
	/* Show coach configuration
	 * 0 - Number of Rows
	 * 1 - Total Seats
	 * 2 - Destination
	 * 3 - Standard Price
	 * 4 - Pensioner Price
	 * 5 - Frequent Price
	 * 6 - Standard Bookings
	 * 7 - Pensioner Bookings
	 * 8 - Frequent Bookings
	 * 
	 * 
	 */
	public void coachFetch() {
		int seatsTaken = (int) this.coach[6] + (int) this.coach[7] + (int) this.coach[8];
		System.out.println("Coach - Information");
		System.out.println("*******************");
		System.out.println("Destination      : " + this.coach[2]);
		System.out.println("Seats Avaliable  : " + ((int) this.coach[1] - seatsTaken));
		System.out.println("Seats Total      : " + this.coach[1]);
		System.out.println("");
		System.out.println("Standard  Booking/s : " + this.coach[6]);
		System.out.println("Pensioner Booking/s : " + this.coach[7]);
		System.out.println("Frequent  Booking/s : " + this.coach[8]);
		System.out.println("");
	}
	
	// Find Available seat numbers to use for booking
	// supply number of seats required
	public int[] coachFindSeats() {
		this.counter = 0;
		int tmpCounter = 0;
		int[] tmpArray = new int[this.seats.length - 1];
		int[] avaliableSeatNumbers;
		
		// Loop though array finding unbooked rooms
		int numSeats = this.seats.length - 1; // Taking in to account seat numbers start at 0 
		while (numSeats > this.counter) {
			if (this.seats[this.counter] == '-') {
				tmpArray[tmpCounter] = this.counter; 
				tmpCounter++;
			}
			this.counter++;
		}
		
		// Trim array - (removed un-required length)
		avaliableSeatNumbers = new int[tmpCounter];
		this.counter = 0;
		while (tmpCounter > this.counter) {
			avaliableSeatNumbers[this.counter] = tmpArray[this.counter];
			this.counter++;
		}
		
		return avaliableSeatNumbers;
	}
	
	// Book a ticket and seats
	public void coachBook() {
		int[] avaliableSeatNumbers = this.coachFindSeats();
		
		this.counter = 0;
		while (avaliableSeatNumbers.length > this.counter) {
			System.out.println(avaliableSeatNumbers[this.counter]);
			this.counter++;
		}
		
	}

	// Book a ticket and seats
	public void coachRefund(Object input) {
		
	}
	
	/* Prints Receipt
	 * type: true = normal, false = refund
	 * destination: the coach destination
	 * 2D input:
	 * 
	 */
	public void coachReceipt(Boolean type, String destination, String[][] input) {
		if (type) {
			// Per Receipt Calculations
			double totalCost = 0; // Total ticket cost
			int totalSeats = 0; // # of seats booked
			
			this.counter = 0;
			while(input.length > counter) {
				// Calculate total ticket cost, unless ticket count is 0
				if (!input[this.counter][0].equals("0")) {
					totalCost = totalCost + Double.parseDouble(input[this.counter][3]);
				}
				totalSeats = totalSeats + Integer.parseInt(input[this.counter][0]);
				this.counter++;
			}
			
			System.out.println("");
			System.out.println("Receipt");
			System.out.println("*******");
			System.out.println("Destination : " + destination);
			System.out.println("Number of seats booked : " + totalSeats);
			
			// Fancy Test Alignment parameters 
			String leftAlignFormat = "    %-2s %-2s %-9s %-1s %-7s %-1s %-7s %-9s %s %n";
			
			this.counter = 0;
			while(input.length > this.counter) {
				// Fancy Print the text
				System.out.format(leftAlignFormat,
						  		  input[this.counter][0],
						  		  "*",
						  		  input[this.counter][1],
						  		  "@",
						  		  "$" + input[this.counter][2],
						  		  " = ",
						  		  "$" + input[this.counter][3],
						  		  "seat(s):",
						  		  input[this.counter][4]);
				this.counter++;
			}
			System.out.println("\t\t\t\t  ------");
			System.out.println("\t\t\t  Total : $" + totalCost);
			System.out.println("");
		} else {
			
		}
	}
	
	// Return a seating data for seating report
	public void coachSeating() {
		System.out.println("Seating - Information");
		System.out.println("*********************");
		System.out.println("");
		
		// Fancy Test Alignment parameters 
		String leftAlignFormat = "  %-5s %-5s %-5s %-5s %n";
		
		this.counter = 0;
		int numSeats = seats.length - 1; // Taking in to account seat numbers start at 0 
		while(numSeats > this.counter) {
			// Fancy Print the text
			System.out.format(leftAlignFormat,
					  		  counter + ":" + this.seats[counter], 
					  		  counter + 1 + ":" + this.seats[counter + 1], 
					  		  counter + 2 + ":" + this.seats[counter + 2], 
					  		  counter + 3 + ":" + this.seats[counter + 3]);
			this.counter = this.counter + 4;	
		}
		
		int seatsTaken = (int) this.coach[6] + (int) this.coach[7] + (int) this.coach[8];
		System.out.println("  Number of available seats: " + ((int) this.coach[1] - seatsTaken));
		System.out.println("");
	}
}