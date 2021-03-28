public class Coach {
	private Object[] coach; // Storage Array
	private char[] seats; // Storage Array
	int counter; // Recyclable counter
	
	public Coach() {
		// Init Variables
		this.coach = new Object[9];
		this.counter = 0;
	}
	
	public void coachAdd(String[] input) {
		this.coach[0] = input[0]; // Number of Rows
		this.coach[1] = Integer.parseInt(input[0]) * 4; // Total Number of Seats 
		this.coach[2] = input[1]; // Destination
		this.coach[3] = Double.parseDouble(input[2]); // Standard Price 
		this.coach[4] = Double.parseDouble(input[3]); // Pensioner Price
		this.coach[5] = Double.parseDouble(input[4]); // Frequent Price
		this.coach[6] = (int) 0; // Standard Bookings
		this.coach[7] = (int) 0; // Pensioner Bookings
		this.coach[8] = (int) 0; // Frequent Bookings
		
		this.seats = new char[(int) coach[1] + 1]; // Create char array using total number of seats +1 :) 
		
		// Set seating as available
		this.counter = 0;
		while (this.seats.length > this.counter) {
			this.seats[counter] = '-';
			this.counter++;
		}
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
	 */
	public void coachFetch() {
		
		System.out.println("Coach - Information");
		System.out.println("*******************");
		System.out.println("Destination      : " + this.coach[2]);
		System.out.println("Seats Avaliable  : " + this.coachAvaliableSeats()); // Recycled code method 
		System.out.println("Seats Total      : " + this.coach[1]);
		System.out.println("");
		System.out.println("Standard  Booking/s : " + this.coach[6]);
		System.out.println("Pensioner Booking/s : " + this.coach[7]);
		System.out.println("Frequent  Booking/s : " + this.coach[8]);
		System.out.println("");
		this.coachPricing(); // Fetch ticket pricing (recycled code)
		System.out.println("");
	}
	
	public void coachPricing() {
		System.out.println("Standard  Price (S): $" + this.coach[3]);
		System.out.println("Pensioner Price (P): $" + this.coach[4]);
		System.out.println("Frequent  Price (F): $" + this.coach[5]);
	}
	
	public int coachAvaliableSeats() {
		int seatsTaken = (int) this.coach[6] + (int) this.coach[7] + (int) this.coach[8];
		return ((int) this.coach[1] - seatsTaken);
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
	public void coachBook(String[] input) {
		int[] avaliableSeatNumbers; // Array of available seats
		int[] processedInput = new int[3];
		int totalSeatsRequired = 0; // Temp array to track the required seats
		
		Object[][] output = new Object[3][5]; // Array for coachReceipt()
		
		// Process input in loop convert strings to int's and store in processedInputs array
		this.counter = 0;
		while (input.length > this.counter) {
			processedInput[this.counter] = Integer.parseInt(input[this.counter]);
			output[this.counter][0] = processedInput[this.counter];

			// calculate total required seats
			totalSeatsRequired = totalSeatsRequired + Integer.parseInt(input[this.counter]);
			this.counter++;
		}
		
		// Check if we have enough available seats to accommodate the booking
		if (totalSeatsRequired >  this.coachAvaliableSeats()) {
			// Throw an error
			System.out.println("> ERROR: Required number of seats exceeds avaliable seats.");
			System.out.println("> Total Requested # : " + totalSeatsRequired);
			System.out.println("> Avaliable Seats " + this.coachAvaliableSeats());
			System.out.println("");
		} else {
			// Book Seating - Standard
			output[0][1] = "Standard"; // Receipt Item Name
			output[0][2] = this.coach[3]; // Receipt Item Cost
			output[0][3] = (Double) this.coach[3] * processedInput[0]; //Receipt Item total cost
			avaliableSeatNumbers = this.coachFindSeats(); // Array of available seats	
			this.counter = 0;
			
			while (processedInput[0] > this.counter) {
				//Store the target seat here for easier reading code
				int targetSeat = avaliableSeatNumbers[this.counter]; 
				this.seats[targetSeat] = 'S'; // Mark Seat as booked by ticket type
				
				// Add Seat to ticket, check if array is null, if null do not include null contents
				if (output[0][4] == null) {
					output[0][4] =  targetSeat + ", "; // Receipt Seat Numbers
				} else {
					output[0][4] = (String) output[0][4] + targetSeat + ", "; // Receipt Seat Numbers
				}
				
				this.coach[6] = (int) this.coach[6] + 1; // Increment booking tracking
				this.counter++;
			}

			// Book Seating - Pensioner
			output[1][1] = "Pensioner"; // Receipt Item Name
			output[1][2] = this.coach[4]; // Receipt Item Cost
			output[1][3] = (Double) this.coach[4] * processedInput[1]; //Receipt Item total cost
			avaliableSeatNumbers = this.coachFindSeats(); // Array of available seats	
			this.counter = 0;
			
			while (processedInput[1] > this.counter) {
				//Store the target seat here for easier reading code
				int targetSeat = avaliableSeatNumbers[this.counter]; 
				this.seats[targetSeat] = 'P'; // Mark Seat as booked by ticket type

				// Add Seat to ticket, check if array is null, if null do not include null contents
				if (output[1][4] == null) {
					output[1][4] =  targetSeat + ", "; // Receipt Seat Numbers
				} else {
					output[1][4] = (String) output[1][4] + targetSeat + ", "; // Receipt Seat Numbers
				}
				
				this.coach[7] = (int) this.coach[7] + 1; // Increment booking tracking
				this.counter++;
			}

			// Book Seating - Frequent
			output[2][1] = "Frequent"; // Receipt Item Name
			output[2][2] = this.coach[5]; // Receipt Item Cost
			output[2][3] = (Double) this.coach[5] * processedInput[2]; //Receipt Item total cost
			avaliableSeatNumbers = this.coachFindSeats(); // Array of available seats	
			this.counter = 0;
			
			while (processedInput[2] > this.counter) {
				//Store the target seat here for easier reading code
				int targetSeat = avaliableSeatNumbers[this.counter]; 
				this.seats[targetSeat] = 'F'; // Mark Seat as booked by ticket type

				// Add Seat to ticket, check if array is null, if null do not include null contents
				if (output[2][4] == null) {
					output[2][4] =  targetSeat + ", "; // Receipt Seat Numbers
				} else {
					output[2][4] = (String) output[2][4] + targetSeat + ", "; // Receipt Seat Numbers
				}
				
				this.coach[8] = (int) this.coach[8] + 1; // Increment booking tracking
				this.counter++;
			}			
			
			this.coachReceipt(true, (String) this.coach[2], output);
		}
	}

	/* Refund ticket seats
	 * expects sting of numbers and whitespace only
	 * - will refund seat/s based of booked ticket type
	 * - will error on un-booked seat/invalid seat
	 */
	
	public void coachRefund(String input) {
		// split string in to string array using whitespace
		String[] tmpInput = input.split(" ");
		// create new int array using length of tmpInput array
		int[] processedInput = new int[tmpInput.length];
		
		// Convert from string to int 
		this.counter = 0;
		while (tmpInput.length > this.counter ) {
			processedInput[this.counter] = Integer.parseInt(tmpInput[this.counter]);
			this.counter++;
		}
		
		// Ensure all seats exist, and are booked.
		
		this.counter = 0;
		Object[][] errors = new Object[processedInput.length][2];
		boolean error = false;
		int errorCount = 0;
		
		while (processedInput.length > this.counter) {
			// Check if we have a seat number higher than the max available
			if (processedInput[this.counter] > this.seats.length - 1) {
				errors[errorCount][0] = processedInput[this.counter];
				errors[errorCount][1] = "Non Existant Seat!";
				error = true; // flag an error
				errorCount++; // Increment error count

				// Check if the seat was booked
			} else if (this.seats[processedInput[this.counter]] == '-') {
				errors[errorCount][0] = processedInput[this.counter];
				errors[errorCount][1] = "Seat is not booked!";
				error = true; // flag an error
				errorCount++; // increment error count
			}
			this.counter++;
		}
		/* We had errors bummer
		 * - Trim error array
		 * - print errors
		 * - do nothing
		 */
		if (error) {
			// Trim array - (removed un-required length)
			
			Object[][] tmpArray = new Object[errorCount][2];
			this.counter = 0;
			int tmpCounter = 0;
			while (errors.length > this.counter) {
				if (errors[this.counter][0] != null && errors[this.counter][0] != "") {
					tmpArray[tmpCounter][0] = errors[this.counter][0];
					tmpArray[tmpCounter][1] = errors[this.counter][1];
					tmpCounter++;
				}
				this.counter++;
			}
			errors = tmpArray; // Copy new array over old one
			
			// Print Errors
			this.counter = 0;
			while (errors.length > this.counter) {
				System.out.println("> ERROR: " + errors[this.counter][1] + "  (Seat: " + errors[this.counter][0] +")") ;
				this.counter++;
			}
			System.out.println("");
		} else {
			// No errors sweet, lets cancel and refund some bookings
			
			// Track booking types for the refund
			int[] bookingTypeCount = new int[3];
			bookingTypeCount[0] = 0;
			bookingTypeCount[1] = 0;
			bookingTypeCount[2] = 0;
			
			// Track and format seat numbers for the receipt text
			Object[] tmpSeats = new Object[3];
			
			for (int seat : processedInput) {
				char current = this.seats[seat];
				
				// Update Coach Booking counters
				if (current == 'S') {
					this.coach[6] = (int) this.coach[6] - 1;
					bookingTypeCount[0]++;
					if (tmpSeats[0] == null) {
						tmpSeats[0] = seat + ", ";
					} else {
						tmpSeats[0] = tmpSeats[0] + "" + seat + ", "; 
					}
				} else if (current == 'P') {
					this.coach[7] = (int) this.coach[7] - 1;
					bookingTypeCount[1]++;
					if (tmpSeats[1] == null) {
						tmpSeats[1] = seat + ", ";
					} else {
						tmpSeats[1] = tmpSeats[1] + "" + seat + ", ";  
					}					
				} else if (current == 'F') {
					this.coach[8] = (int) this.coach[8] - 1;
					bookingTypeCount[2]++;
					if (tmpSeats[2] == null) {
						tmpSeats[2] = seat + ", ";
					} else {
						tmpSeats[2] = tmpSeats[2] + "" + seat + ", ";   
					}					
				}	
				// Update Seat Status
				this.seats[seat] = '-';
			}
			
			// Process Receipt
			
			Object[][] output = new Object[3][5];
			
			// Standard
			output[0][0] = bookingTypeCount[0];
			output[0][1] = "Standard";
			output[0][2] = this.coach[3];
			output[0][3] = (Double) this.coach[3] * bookingTypeCount[0]; 
			output[0][4] = tmpSeats[0];

			// Pensioner
			output[1][0] = bookingTypeCount[1];
			output[1][1] = "Pensioner";
			output[1][2] = this.coach[4];
			output[1][3] = (Double) this.coach[4] * bookingTypeCount[1]; 
			output[1][4] = tmpSeats[1];
			
			// Frequent
			output[2][0] = bookingTypeCount[2];
			output[2][1] = "Frequent";
			output[2][2] = this.coach[5];
			output[2][3] = (Double) this.coach[5] * bookingTypeCount[2]; 
			output[2][4] = tmpSeats[2];
			
			this.coachReceipt(false, (String) this.coach[2], output);
		}
	}
	
	/* Prints Receipt
	 * type: true = normal, false = refund
	 * destination: the coach destination
	 * 2D input:
	 * 
	 */
	public void coachReceipt(Boolean type, String destination, Object[][] input) {
		// Per Receipt Calculations
		double totalCost = 0; // Total ticket cost
		int totalSeats = 0; // # of seats booked
		
		this.counter = 0;
		while(input.length > counter) {
			// Calculate total ticket cost, unless ticket count is 0
			if (!input[this.counter][0].equals("0")) {
				totalCost = totalCost + (Double) input[this.counter][3];
			}
			totalSeats = totalSeats + (int) input[this.counter][0];
			this.counter++;
		}
		
		System.out.println("");
		System.out.println("Receipt");
		System.out.println("*******");
		System.out.println("Destination : " + destination);
		if (type) {
			System.out.println("Number of seats booked : " + totalSeats);
		} else {
			System.out.println("Number of seats refunded : " + totalSeats);
		}
		
		// Fancy Test Alignment parameters 
		String leftAlignFormat = "    %-2s %-2s %-9s %-1s %-7s %-1s %-7s %-9s %s %n";
		
		this.counter = 0;
		// Normal Receipt V Refund Receipt
		if (type) {
			// Normal Receipt
			while(input.length > this.counter) {
				// Clean up seat numbers * remove nulls
				if (input[this.counter][4] == null) {
					input[this.counter][4] = "";
				}
				
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
			// Refund Receipt
			while(input.length > this.counter) {
				// Clean up seat numbers * remove nulls
				if (input[this.counter][4] == null) {
					input[this.counter][4] = "";
				}
				
				// Fancy Print the text			
				System.out.format(leftAlignFormat,
						  		  input[this.counter][0],
						  		  "*",
						  		  input[this.counter][1],
						  		  "@",
						  		  "$" + input[this.counter][2],
						  		  " = ",
						  		  "-$" + input[this.counter][3],
						  		  "seat(s):",
						  		  input[this.counter][4]);
				this.counter++;
			}
			System.out.println("\t\t\t\t  ------");
			System.out.println("\t\t\t  Total : -$" + totalCost);
			System.out.println("");
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