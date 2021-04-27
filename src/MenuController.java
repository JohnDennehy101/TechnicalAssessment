import java.util.*;


/**
 * @author John Dennehy
 * @version 1.0
 */
public class MenuController {
    private Scanner input = new Scanner(System.in);
    private Person person;
    private AddressBook addressBook;
    private ValidateUserInput validateUserInput;


    public static void main(String[] args) {

        MenuController c = new MenuController();

    }


    public MenuController() {
        /**
         * Instantiating addressBook class
         */
        addressBook = new AddressBook();


        try {
            /**
             * Add default Users
             */
            bootStrapDefaultUsers();

            /**
             * Load main menu
             */
            startUpMenu();
        } catch (Exception e) {
            System.out.println("Error loading start menu" + e);
        }
    }


    /**
     * Method which displays options to user. userInput function called within this to take user input.
     */
    private void startUpMenu() {
        System.out.println("\nAddress Book Application");
        System.out.println("------------------------");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. Edit User");
        System.out.println("4. List all information about a specific user");
        System.out.println("5. List All Users By Name");
        System.out.println("0. Exit programme");
        userInput();

    }

    /**
     * This method adds 3 default user addresses on launch of the programme.
     */
    private void bootStrapDefaultUsers() {

        addressBook.addAccount(new Person("John", "Dennehy", "johndennehy101@gmail.com", "Mallow, Co. Cork", "Male"));
        addressBook.addAccount(new Person("Fake", "Test", "fake@gmail.com", "Drumcondra, Co. Dublin", "Female"));
        addressBook.addAccount(new Person("Test", "User", "test@gmail.com", "Tramore, Co. Waterford", "Male"));
    }

    /**
     * This method ensures that the user provides a number as input for option selection.
     * While loop used to ensure that menu reloads after specific task is completed (unless user enters 0 to exit programme).
     * Switch statement in place to call relevant method based on user input.
     */
    private void userInput() {
        boolean goodInput = false;
        int menuChoice = 0;

        while (!goodInput) {
            try {
                System.out.print("==> ");
                menuChoice = input.nextInt();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Invalid input - please enter an integer value.");
            }
        }


        while (menuChoice != 0) {
            switch (menuChoice) {
                case 1:
                    addAddress();

                case 2:
                    removeAddress();

                case 3:
                    editAddress();

                case 4:
                    listUserInformation();
                case 5:

                    listAllUserNames();

                case 0:
                    System.out.println("Exiting Programme...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Option. Exiting Programme...");
                    System.exit(0);

            }
        }
    }


    /**
     * Method for adding user address
     * While loops used for basic validation checks.
     * User cannot register same email twice (and external method with regex validates email input).
     * For other pieces of info, just ensure that user provides some input.
     */
    private void addAddress() {
        boolean emailAlreadyRegistered = true;
        boolean validAddress = false;
        boolean validGender = false;
        boolean validFirstName = false;
        boolean validLastName = false;
        String firstName = "";
        String lastName = "";
        String emailInputted = "";
        String gender = "";
        String address = "";

        input.nextLine();
        while (!validFirstName) {
            System.out.print("First Name: ");
            firstName = input.nextLine();

            if (firstName.length() > 0) {
                validFirstName = true;
            } else {
                System.out.println("Please provide some input.");
            }
        }

        while (!validLastName) {
            System.out.print("Last Name: ");
            lastName = input.nextLine();

            if (lastName.length() > 0) {
                validLastName = true;
            } else {
                System.out.println("Please provide some input.");

            }

        }


        while (emailAlreadyRegistered == true) {
            System.out.print("\nEmail: ");
            emailInputted = input.next();

            if (addressBook.searchByEmail(emailInputted) == null && validateUserInput.isValid(emailInputted)) {
                emailAlreadyRegistered = false;
            } else if (addressBook.searchByEmail(emailInputted) != null) {
                System.out.println("\nInvalid Email. Account already exists with this email.");
            } else if (!validateUserInput.isValid(emailInputted)) {
                System.out.println("\nInvalid Email. Please provide valid input.");
            }
        }

        while (!validAddress) {
            input.nextLine();
            System.out.print("Address: ");
            address = input.nextLine();

            if (address.length() > 0) {
                validAddress = true;
            } else {
                input.nextLine();
                System.out.println("Please provide an address");
            }
        }

        while (!validGender) {
            System.out.print("Gender: ");
            gender = input.nextLine().toUpperCase();

            if (gender.length() > 0) {
                validGender = true;
                if (gender.charAt(0) != 'M' || gender.charAt(0) != 'F') {
                    gender = "Unspecified";
                }
            } else {
                System.out.println("Please provide some input.");
            }

        }

        addressBook.addAccount(new Person(firstName, lastName, emailInputted, address, gender));
        System.out.println("Successfully added User!\n");
        System.out.println(addressBook.searchByEmail(emailInputted));
        startUpMenu();


        input.nextLine();

    }


    /**
     * Method for removing user address.
     * User is prompted for email of account they wish to remove.
     * If record is found with that email, record is retrieved and removed.
     * If not, user is informed that no account was found with that email.
     */
    private void removeAddress() {
        String emailInputted = "";


        input.nextLine();
        System.out.print("Email: ");

        emailInputted = input.nextLine();

        if (addressBook.searchByEmail(emailInputted) == null) {
            System.out.println("No user account found with that email.");
        } else {
            System.out.println(addressBook.searchByEmail(emailInputted));
            addressBook.deleteAccount(emailInputted);
            System.out.println("Successfully deleted User Address!\n");
        }


        startUpMenu();


    }

    /**
     * Method for editing existing record.
     * User can edit all pieces of info apart from email (as this is a unique identifier preventing duplicate account sign-ups).
     */
    private void editAddress() {
        String emailInputted = "";


        input.nextLine();
        System.out.print("Email: ");

        emailInputted = input.nextLine();

        Person account = addressBook.searchByEmail(emailInputted);

        if (account == null) {
            System.out.println("No user account found with that email.");
            startUpMenu();
        } else {

            System.out.print("First Name: ");
            String firstName = input.nextLine();

            boolean validFirstName = validateUserInput.notEmptyInput(firstName);

            if (!validFirstName) {
                System.out.println("No input provided. Exiting to main menu");
                startUpMenu();
            }


            System.out.print("Last Name: ");
            String lastName = input.nextLine();

            boolean validLastName = validateUserInput.notEmptyInput(lastName);

            if (!validLastName) {
                System.out.println("No input provided. Exiting to main menu");
                startUpMenu();
            }


            System.out.print("Address: ");
            String address = input.nextLine();

            boolean validAddress = validateUserInput.notEmptyInput(address);

            if (!validAddress) {
                System.out.println("No input provided. Exiting to main menu");
                startUpMenu();
            }

            System.out.print("Gender: ");
            String gender = input.nextLine().toUpperCase();

            if (gender.charAt(0) != 'M' || gender.charAt(0) != 'F' || gender.length() == 0) {
                gender = "Unspecified";
            }
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setAddress(address);
            account.setGender(gender);
        }

        startUpMenu();
    }


    /**
     * Method to list information associated with a particular email.
     * if associated record with email found, info is printed to console.
     * Else, user is informed that no user account is present with that email.
     */
    public void listUserInformation() {
        String emailInputted = "";


        input.nextLine();
        System.out.print("Email: ");

        emailInputted = input.nextLine();

        Person account = addressBook.searchByEmail(emailInputted);

        if (account == null) {
            System.out.println("No user account found with that email.");
            return;
        } else {
            System.out.println("Account Details");
            System.out.println("---------------");
            System.out.println("Name: " + account.getFullName());
            System.out.println("Email: " + account.getEmail());
            System.out.println("Address: " + account.getAddress());
            System.out.println(("Gender: " + account.getGender()));

        }

        startUpMenu();

    }

    /**
     * Method for listing the name of each record in the address book.
     * User is prompted if they want to search by first or last name (default is first name).
     */
    public void listAllUserNames() {
        String sortChoice = "";
        Boolean sortByFirstName = true;


        input.nextLine();

        System.out.print("Do you want to sort by first or last name: ");

        sortChoice = input.nextLine();


        if (sortChoice.charAt(0) == 'L' || sortChoice.charAt(0) == 'l') {
            sortByFirstName = false;
        }


        if (sortByFirstName) {
            System.out.println("\nOrdered by First Name\n");
            System.out.println("-----------------------");
        } else {
            System.out.println("\nOrdered by Last Name\n");
            System.out.println("-----------------------");
        }
        System.out.println(addressBook.listAccountNames(sortByFirstName));
        startUpMenu();
    }


}
