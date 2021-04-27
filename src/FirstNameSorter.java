import java.util.Comparator;


/**
 * @author John Dennehy
 * @version 1.0
 */

/**
 * Class declared to store method for sorting records by first name field
 *
 * @returns sorted list by first name field
 */
public class FirstNameSorter implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
    }
}
