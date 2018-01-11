package ca.bcit.comp2526.assignment1;

/**
 * 
 * Person object contains name and phone number variables.
 *
 * @author Vladimir Kisselev A00798129
 * @version Jan 10, 2018
 */
public class Person {
	/**
	 * Used to store the name for a Person object.
	 */
    private String name;
    
    /**
     * Used to store the phone number for a Person object.
     */
    private String phoneNumber;
    
    /**
     * 
     * Constructs an object of type Person.
     * @param nameParameter used as name
     * @param phoneNumberParameter used as phone number
     */
    public Person(String nameParameter, String phoneNumberParameter) {
        if (!nameParameter.equalsIgnoreCase(" ") 
        		|| !phoneNumberParameter.equalsIgnoreCase(" ")) {
            name = nameParameter;
            phoneNumber = phoneNumberParameter;
        } else {
            throw new IllegalArgumentException(
            		"Name and number cannot be blank!");
        }
        //Qualifying and adding the name/number of Person
    }

    /**
     * Gets the name for this Person.
     * @return the name for this Person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for this Person.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the phoneNumber for this Person.
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phoneNumber for this Person.
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
}
