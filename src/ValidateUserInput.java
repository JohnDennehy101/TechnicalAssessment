/**
 * @author John Dennehy
 * @version 1.0
 */


/**
 * Class declared to store method for validating user input
 */
public class ValidateUserInput {
    /**
     * Method to validate if input is valid email
     *
     * @returns true if valid email, false if not
     */
    static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    /**
     * Method to validate that user input is not empty
     *
     * @returns true if input not empty, false if empty
     */
    static boolean notEmptyInput(String input) {
        if (input.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
