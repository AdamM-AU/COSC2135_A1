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
			System.out.println("( 3 ) - unused");
			System.out.println("( 4 ) - unsed");
			System.out.println("");
			System.out.println("( 5 ) - Create a Booking/Ticket");
			System.out.println("( 6 ) - Refund a Booking/Ticket");
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
				else if (userSelection == 5) {
					this.backEnd.coachBook();
				}
				else if (userSelection == 8) {
					// Call this method, then return
					//uiCoachShow(0); // Requires ID of target
					String[][] temp = new String[3][5];
					temp[0][0] = "2";
					temp[0][1] = "Standard";
					temp[0][2] = "30.99";
					temp[0][3] = "61.98";
					temp[0][4] = "1, 2";

					temp[1][0] = "0";
					temp[1][1] = "Pensioner";
					temp[1][2] = "10.99";
					temp[1][3] = "0.00";
					temp[1][4] = "";
					
					temp[2][0] = "2";
					temp[2][1] = "Frequent";
					temp[2][2] = "20.99";
					temp[2][3] = "41.98";
					temp[2][4] = "3, 4";
					
					this.backEnd.coachReceipt(true,"someshit hole", temp);
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
		while (!this.userInput.matches("^[0-9]+$")) {
			if (!this.userInput.equals("") && !this.userInput.matches("^[0-9]+$")) {
				System.out.println("> Unexpected input \"" + userInput + "\", Expected Numerical Input...");
				System.out.println("");
			}
			
			System.out.print("Number of Rows : ");
			userInput = consoleInput.nextLine();
		}
		input[0] = userInput;
		
		// Destination
		System.out.print("Destination : ");
		userInput = consoleInput.nextLine();
		input[1]  = userInput;
		System.out.println("");
		
		// Ticket Pricing
		System.out.println("-- Ticket Pricing --");
		
		// Validate as Integer or Double 
		this.userInput = ""; // Reset Variable
		while (!this.userInput.matches("^[0-9]+$") && !this.userInput.matches("^\\d+\\.\\d+")) {
			System.out.println("Input Standard (S) : ");
			this.userInput = consoleInput.nextLine();
		}
		input[2] = this.userInput; // Add to array
		
		this.userInput = ""; // Reset Variable
		while (!this.userInput.matches("^[0-9]+$") && !this.userInput.matches("^\\d+\\.\\d+")) {
			System.out.println("Input Pensioner (P) : ");
			this.userInput = consoleInput.nextLine();
		}
		input[3] = this.userInput; // Add to array
		
		this.userInput = ""; // Reset Variable
		while (!this.userInput.matches("^[0-9]+$") && !this.userInput.matches("^\\d+\\.\\d+")) {
			System.out.println("Input Discounted Frequent Customers (F) : ");
			this.userInput = consoleInput.nextLine();
		}
		input[4] = this.userInput; // Add to array
		
		System.out.println("");
		// Push to backend
		this.backEnd.coachAdd(input);

	}
	
	public void uiCoachBook() {
		
	}
	
	public void uiCoachRefund() {
		
	}
	
}
