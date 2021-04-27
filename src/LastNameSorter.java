import java.util.Comparator;

/**
 * @author John Dennehy
 * @version 1.0
 */

/**
 * Class declared to store method for sorting records by last name field
 *
 * @returns sorted list by last name field
 */
public class LastNameSorter implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return p1.getLastName().compareToIgnoreCase(p2.getLastName());
    }
}
