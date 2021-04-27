import java.util.ArrayList;

/**
 * The AddressBook class allows users to
 *
 * @author John Dennehy
 * @version 1.0
 */

/**
 * Class declared to store methods for operations with address book
 */
public class AddressBook {
    /**
     * Records are stored in arraylist (of type Person)
     */
    private ArrayList<Person> personList;

    /**
     * Instantiating the personList ArrayList
     */
    public AddressBook() {
        personList = new ArrayList<Person>();
    }

    /**
     * Method for obtaining contents of personList ArrayList
     * @return arrayList with info on each record in the personList ArrayList
     */
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * Method for adding an object of type Person to the personList ArrayList
     */
    public void addAccount(Person person) {
        personList.add(person);
    }

    /**
     * Method for deleting user record
     */
    public void deleteAccount(String email) {
        Person accountToDelete = this.searchByEmail(email);

        if (accountToDelete != null) {
            personList.remove(accountToDelete);
        }


    }

    /**
     * Method for checking if valid index is provided for personList ArrayList
     * @returns boolean - true if valid index, false if invalid index
     */
    public boolean isValidIndex(int index) {
        if ((index >= 0) && (index < personList.size())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method for listing account names (records in personList ArrayList)
     * If 'sortByFirstName' is true, records are sorted by first name
     * Else, records are sorted by last name
     * @returns String with full name of each user record (sorted based on user's preference)
     */
    public String listAccountNames(Boolean sortByFirstName) {
        String accountNames = "";
        ArrayList<Person> accountList = this.getPersonList();

        if (sortByFirstName) {
            accountList.sort(new FirstNameSorter());
        } else {
            accountList.sort(new LastNameSorter());
        }

        if (accountList != null && accountList.size() > 0) {
            for (int i = 0; i < accountList.size(); i++) {
                accountNames += "\nName: " + accountList.get(i).getFullName();
            }
        } else {
            accountNames = "No accounts currently present";
        }

        return accountNames;

    }


    /**
     * Method for obtaining record by email provided by user.
     * If record found, returned. If not, null returned
     * @returns Person object associated with emailEntered (if none, null returned).
     */
    public Person searchByEmail(String emailEntered) {
        if (personList != null) {
            for (Person individualPerson : personList) {
                if (emailEntered.toLowerCase().equals(individualPerson.getEmail().toLowerCase())) {
                    return individualPerson;
                }
            }
        }
        return null;
    }
}
