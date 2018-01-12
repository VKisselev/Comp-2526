package ca.bcit.comp2526.assignment1;

import java.util.Scanner;

/**
 * AddressBook class describes a storage program for names and phone numbers.
 * AddressBook is capable of adding, removing, searching, and displaying objects
 * of type People.
 *
 * @author Vladimir Kisselev A00798129
 * @version Jan 10, 2018
 */
public class AddressBook {

	/**
	 * Array of Person objects used for storing the address book.
	 */
	private Person[] database;

	/**
	 * Scanner used for user input.
	 */
	private Scanner input;

	/**
	 * Constructs an object of type AddressBook.
	 */
	public AddressBook() {
		database = new Person[0];
		input = new Scanner(System.in);
	}

	/**
	 * This method is used for adding a new Person object
	 * to the database array.
	 * @param p used as the person being added
	 */
	public void add(final Person p) {
		int pos = 0;
		if (database.length == 0) {
			pos = 1;
		} else {
			pos = database.length + 1;
		}
		//Checking if this is the first person to be added
		
		Person[] temp = new Person[pos];
		//Creating new array that will become the new database
		
		if (pos == 1) {
			temp[0] = p;
		} else {
			for (int i = 0; i < database.length; i++) {
				temp[i] = database[i];
			}
			//Copying the previous names from the old array
			
			temp[pos - 1] = p;
			//Adding new name
		}
		
		database = temp;
	}

	/**
	 * This method is used to search for a specific person.
	 * @param name used to compare to the person in the database
	 * @return integer of location in the array
	 */
	public int search(final String name) {
		
		for (int i = 0; i < database.length; i++) {
			if (database[i].getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
		//returns negative if name not found
	}

	/**
	 * Method used to print all names in the database.
	 */
	public void displayAll() {
		/**
		 * Value for spaces needed to maintain format
		 */
		int spaces = 0;
		
		/**
		 * Value for the longest name in the database
		 */
		int maxWordLength = 0;

		final int spacesBetweenWords = 3;
		final int nameLength = 4;
		
		for (Person p : database) {
			if (p.getName().length() > maxWordLength) {
				maxWordLength = p.getName().length();
			}
		}
		//Looping through the database for the longest name
		
		spaces = maxWordLength - nameLength + spacesBetweenWords;
		System.out.println("Name" + String.format("%" + spaces + "s", "") 
					+ "Phone Number");
		//Using the specific spacing for table headers

		for (Person p : database) {
			spaces = maxWordLength - p.getName().length() + spacesBetweenWords;
			System.out.println(p.getName() 
					+ String.format("%" + spaces + "s", "") 
					+ p.getPhoneNumber());
		}
		//Looping through the database to print all data
	}
	
	/**
	 * Method used to display a single Person object.
	 * @param p used as the Preson in database
	 */
	public void displayOne(Person p) {
		final int spacesBetweenWords = 3;
		final int nameLength = 4;
		
		int spaces = p.getName().length() - nameLength + spacesBetweenWords;
		//Calculating spaces needed for headers
		
		System.out.println("Name" + String.format("%" + spaces + "s", "") 
		+ "Phone Number");
		//Using the specific spacing for table headers
		
		spaces = spacesBetweenWords;
		//Calculating spaces between data
		
		System.out.println(p.getName() 
				+ String.format("%" + spaces + "s", "") 
				+ p.getPhoneNumber());
		//Printing Person data
	}

	/**
	 * Method used to remove a specific Person from the database.
	 * @param name used to search for a specific person
	 * @return boolean if found
	 */
	public boolean remove(final String name) {
		int pos = search(name);
		
		if (pos >= 0) {
			Person[] temp = new Person[database.length - 1];
			//Creating new database that is smaller than current
			
			System.arraycopy(database, 0, temp, 0, pos);
			System.arraycopy(database, pos + 1, temp, pos, 
					database.length - pos - 1);
			database = temp;
			//Setting the new database
			
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method used to output the menu to the console.
	 */
	public void displayMenu() {
		System.out.println("\n\n\n1) Add");
		System.out.println("2) Delete");
		System.out.println("3) Search");
		System.out.println("4) Display All");
		System.out.println("5) Exit\n");
	}

	/**
	 * This method registers the user input,
	 * qualifies user input for proper range.
	 * @return integer to be used for run()
	 */
	public int getChoice() {
		final int choiceOutOfBounds = 5;
		final int defaultChoice = 4;
		int choice = defaultChoice;
		// default
		
		boolean done = false;
		
		do {
			System.out.print("choice? ");
			try {
				choice = input.nextInt();
			} catch (Exception e) {
				System.out.println(e);
			}
			if (choice > 0 && choice <= choiceOutOfBounds) {
				done = true;
			} else {
				System.out.println("\nYour choice is incorrect, "
						+ "please try again");
			}
			//Qualifying choice
				
		} while (!done);
		//Running loop while choice is unqualified
		
		return choice;
	}

	/**
	 * Method used to qualify a new Person and add() 
	 * to the database.
	 */
	public void addPerson() {
		String name = "";
		String phone = "";
		Person tempPerson = null;
		
		try {
			System.out.print("Enter the persons name ");
			name = input.next();
			System.out.print("\nEnter the persons phone number ");
			phone = input.next();
			System.out.println("");
		} catch (Exception e) {
			System.out.println(e);
		}
		//Qualifying the name and phone number
		
		try {
			tempPerson = new Person(name, phone);
		} catch (Exception e) {
			System.out.println(e);
		}
		//Creating new Person
		
		add(tempPerson);
		//Adding person
	}

	/**
	 * Method used to qualify user input 
	 * and remove Person from database.
	 */
	public void deletePerson() {
		String name = "";
		
		try {
			System.out.print("Enter the persons name ");
			name = input.next();
			System.out.println("");
		} catch (Exception e) {
		}
		//Qualifying user input
		
		if (!remove(name)) {
			System.out.println("Could not delete " + name);
		} else {
			System.out.println(name + " was deleted successfully");
		}
		//Attempting to remove() name
	}

	/**
	 * Method used to qualify user input
	 * and search() for Person in the database.
	 */
	public void findPerson() {
		String name = "";
		
		try {
			System.out.print("Enter the persons name ");
			name = input.next();
			System.out.println("");
		} catch (Exception e) {
		}
		// Qualifying user input
		
		int pos = search(name);
		// Passing the name to the search() method
		
		if (pos >= 0) {
			displayOne(database[pos]);
		} else {
			System.out.println("No such person");
		}
		//Displaying Person if found
	}

	/**
	 * Method used to display the main menu,
	 * input user choice and call further methods.
	 */
	public void run() {
		int choice = 0;
		final int choiceThree = 3;
		final int choiceFour = 4;
		final int choiceOutOfBounds = 5;
		
		do {
			displayMenu();
			choice = getChoice();
			switch (choice) {
			case 1:
				addPerson();
				break;
			case 2:
				deletePerson();
				break;
			case choiceThree:
				findPerson();
				break;
			case choiceFour:
				displayAll();
				break;
			default:
				// should not get here
			}

		} while (choice != choiceOutOfBounds);
	}

	/**
	 * Main method that is called at the start of the program.
	 * @param args unused
	 */
	public static void main(String[] args) {
		new AddressBook().run();
	}
}
