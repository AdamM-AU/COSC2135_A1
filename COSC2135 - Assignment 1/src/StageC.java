import java.util.Scanner;  // Import the Scanner class

//  All user input is processed here?                                                                                                                                                             
public class StageC {
	Scanner consoleInput = new Scanner(System.in); // Creating Scanner Object
	String userInput = "";
	int coachNumRows = 0;
	int coachNumSeats = 0;
	String coachDestination = "";
	String[] input;
	int counter; // Recyclable counter
	private Coach backEnd;
	
	public StageC(Coach backEnd) {
		this.backEnd = backEnd;
		this.uiCoachAdd(); // Request Coach information
		
		while (!userInput.equals("q")) {
			// Main Application Menu
			System.out.println("Crappy Booking System 1.0");
			System.out.println("*************************");
			System.out.println("");
			System.out.println("( 1 ) - Show Coach Information");
			System.out.println("( 2 ) - Show Seating");
			System.out.println("( 3 ) - Show Ticket Report");
			System.out.println("");
			System.out.println("( 4 ) - Create a Booking/Ticket");
			System.out.println("( 5 ) - Refund a Booking/Ticket");
			System.out.println("");
			System.out.println("( q ) - Quit");
			System.out.println("");
			System.out.print("Please Make a Selection : ");
			this.userInput = this.consoleInput.nextLine();
			
			// Validate input and an integer & as expected input, if not match or failed to validate do nothing so we loop again
			if (this.userInput.matches("^[0-9]+$") && this.userInput.matches("1|2|3|4|5|6|7|8|9")) {
				int userSelection = Integer.parseInt(this.userInput); // Temp Input Store 
				
				// Match User Selection
				// Switch would have been nicer to use here...
				if (userSelection == 1) {
					// Call this method, then return
					System.out.println("");
					this.backEnd.coachFetch();
					System.out.println("Press anykey to continue...");
					this.userInput = this.consoleInput.nextLine();
				} 
				else if (userSelection == 2) {
					System.out.println("");
					this.backEnd.coachSeating();
					System.out.println("Press anykey to continue...");
					this.userInput = this.consoleInput.nextLine();
				}
				else if (userSelection == 4) {
					System.out.println("");
					this.uiCoachBook();
					System.out.println("Press anykey to continue...");
					this.userInput = this.consoleInput.nextLine();					
				}
				else if (userSelection == 5) {
					System.out.println("");
					this.uiCoachRefund();
					System.out.println("Press anykey to continue...");
					this.userInput = this.consoleInput.nextLine();
				}
			}

			// Print Message on Exit
			if (userInput.toLowerCase().equals("q")) {
				System.out.println("GoodBye! - Application Terminated.");	
			}
		}
	}
	
	public void uiCoachAdd() {
		this.input = new String[5];

		
		// START - Coach Configuration
		// Seats
		this.userInput = ""; // Reset Variable
		
		System.out.println("Coach - New Configuration");
		System.out.println("*************************");
		// Validate as Integer
		while (!this.userInput.matches("^[0-9]+$")) {
			if (!this.userInput.equals("") && !this.userInput.matches("^[0-9]+$")) {
				System.out.println("> Unexpected input \"" + this.userInput + "\", Expected Numerical Input...");
				System.out.println("");
			}
			
			System.out.print("Number of Rows : ");
			this.userInput = this.consoleInput.nextLine();
		}
		this.input[0] = this.userInput;
		
		// Destination
		System.out.print("Destination : ");
		this.userInput = this.consoleInput.nextLine();
		this.input[1]  = this.userInput;
		System.out.println("");
		
		// Ticket Pricing
		System.out.println("-- Ticket Pricing --");
		
		// Validate as Integer or Double 
		this.userInput = ""; // Reset Variable
		while (!this.userInput.matches("^[0-9]+$") && !this.userInput.matches("^\\d+\\.\\d+")) {
			System.out.println("Input Standard (S) : ");
			this.userInput = this.consoleInput.nextLine();
		}
		this.input[2] = this.userInput; // Add to array
		
		this.userInput = ""; // Reset Variable
		while (!this.userInput.matches("^[0-9]+$") && !this.userInput.matches("^\\d+\\.\\d+")) {
			System.out.println("Input Pensioner (P) : ");
			this.userInput = this.consoleInput.nextLine();
		}
		this.input[3] = this.userInput; // Add to array
		
		this.userInput = ""; // Reset Variable
		while (!this.userInput.matches("^[0-9]+$") && !this.userInput.matches("^\\d+\\.\\d+")) {
			System.out.println("Input Discounted Frequent Customers (F) : ");
			this.userInput = this.consoleInput.nextLine();
		}
		this.input[4] = this.userInput; // Add to array
		
		System.out.println("");
		// Push to backend
		this.backEnd.coachAdd(this.input);

	}
	
	public void uiCoachBook() {
		this.input = new String[3]; // Re Init array
		
		System.out.println("Coach - Ticket Booking");
		System.out.println("***************************");
		this.backEnd.coachPricing(); // Display ticket pricing
		System.out.println("Avaliable Seats: " + this.backEnd.coachAvaliableSeats());
		System.out.println("***************************");
		System.out.println("");

		// Standard Ticket - Validate as Integer
		this.userInput = ""; // Reset Variable
		while (!this.userInput.matches("^[0-9]+$")) {
			if (!this.userInput.equals("") && !this.userInput.matches("^[0-9]+$")) {
				System.out.println("> Unexpected input \"" + this.userInput + "\", Expected Numerical Input...");
				System.out.println("");
			}
			
			System.out.println("Standard Ticket/s # : ");
			this.userInput = this.consoleInput.nextLine();
		}
		this.input[0] = this.userInput; // Copy user input to input array
		
		// Pensioner Ticket - Validate as Integer
		this.userInput = ""; // Reset Variable
		while (!this.userInput.matches("^[0-9]+$")) {
			if (!this.userInput.equals("") && !this.userInput.matches("^[0-9]+$")) {
				System.out.println("> Unexpected input \"" + this.userInput + "\", Expected Numerical Input...");
				System.out.println("");
			}
			
			System.out.println("Pensioner Ticket/s # : ");
			this.userInput = this.consoleInput.nextLine();
		}
		this.input[1] = this.userInput; // Copy user input to input array

		// Frequent Ticket - Validate as Integer
		this.userInput = ""; // Reset Variable
		while (!this.userInput.matches("^[0-9]+$")) {
			if (!this.userInput.equals("") && !this.userInput.matches("^[0-9]+$")) {
				System.out.println("> Unexpected input \"" + this.userInput + "\", Expected Numerical Input...");
				System.out.println("");
			}
			
			System.out.println("Frequent Ticket/s # : ");
			this.userInput = this.consoleInput.nextLine();
		}
		this.input[2] = this.userInput; // Copy user input to input array		
		
		System.out.println("");
		
		// Pass array to the backend for processing
		this.backEnd.coachBook(this.input);
		
		
	}
	
	public void uiCoachRefund() {
		System.out.println("Coach - Ticket Refund");
		System.out.println("*********************");
		System.out.println("Example: 0 2 3 4");
		System.out.println("");
		this.userInput = "";
		
		// Make sure we have numbers and whitepace only!
		while (!this.userInput.matches("^[0-9\s]+$")) {		
			if (!this.userInput.equals("") && !this.userInput.matches("^[0-9]+$")) {
				System.out.println("> Unexpected input \"" + this.userInput + "\", Expected Numerical Input with whitepaces....");
				System.out.println("Example: 0 2 3 4");
				System.out.println("");
			}
			System.out.print("Seat Number/s : ");
			this.userInput = this.consoleInput.nextLine();
		}
		System.out.println("");
		
		// Pass input to backend
		this.backEnd.coachRefund(this.userInput);
	}
	
}
