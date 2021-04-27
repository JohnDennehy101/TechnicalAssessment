

/**
 * @author John Dennehy
 * @version 1.0
 */
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String gender;


    /**
     * Constructor for Person class
     */
    public Person(String firstName, String lastName, String email, String address, String gender) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAddress(address);
        setGender(gender);
    }

    /**
     * Setter for firstName
     */
    public void setFirstName(String firstName) {
        if (firstName.length() > 0) {
            this.firstName = firstName;
        }

    }

    /**
     * Setter for lastName
     */
    public void setLastName(String lastName) {
        if (lastName.length() > 0) {
            this.lastName = lastName;
        }

    }

    /**
     * Setter for address
     */
    public void setAddress(String address) {
        if (address.length() > 0) {
            this.address = address;
        }

    }

    /**
     * Setter for email
     */
    public void setEmail(String email) {
        if (email.length() > 0) {
            this.email = email;
        }

    }

    /**
     * Setter for gender
     */
    public void setGender(String gender) {
        if (gender.charAt(0) == 'M' || gender.charAt(0) == 'F') {
            this.gender = gender;
        } else {
            this.gender = "Unspecified";
        }

    }

    /**
     * Getter for firstName
     *
     * @return The firstName associated with the record
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for lasttName
     *
     * @return The lastName associated with the record
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for fullName
     *
     * @return The full name associated with the record (concatenation of first and last names)
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Getter for email
     *
     * @return The email associated with the record
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for address
     *
     * @return The address associated with the record
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter for gender
     *
     * @return The gender associated with the record
     */
    public String getGender() {
        return gender;
    }

    /**
     * Return string representation of the user
     */
    public String toString() {
        return "Person name: " + firstName + " " + lastName +
                ", email: " + email +
                ", address: " + address +
                ", gender: " + gender;

    }

}
