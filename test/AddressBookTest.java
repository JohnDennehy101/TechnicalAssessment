import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Tests for Address Book")
public class AddressBookTest {

    AddressBook addressBook;
    Person person1;
    Person person2;
    Person person3;

    @BeforeEach
    public void setUp() {
        addressBook = new AddressBook();

        person1 = new Person("John", "Dennehy", "johndennehy101@gmail.com", "Mallow, Co. Cork", "Male");
        person2 = new Person("Fake", "Test", "fake@gmail.com", "Drumcondra, Co. Dublin", "Female");
        person3 = new Person("Test", "User", "test@gmail.com", "Tramore, Co. Waterford", "Male");


    }

    @Nested
    @DisplayName("isValidIndex method test")
    class PersonIndex {
        @Nested
        @DisplayName("Given ArrayList with length of 3")
        class Valid {

            @BeforeEach
            public void setUp() {
                addressBook.addAccount(person1);
                addressBook.addAccount(person2);
                addressBook.addAccount(person3);
            }

            @Test
            @DisplayName("0 index is valid")
            public void lower() {
                boolean result = addressBook.isValidIndex(0);
                assertTrue(result, "0 index is good");
            }

            @Test
            @DisplayName("2 index is valid")
            public void upper() {
                boolean result = addressBook.isValidIndex(2);
                assertTrue(result, "2 index is good");
            }

            @Test
            @DisplayName("3 index is invalid")
            public void outOfBounds() {
                boolean result = addressBook.isValidIndex(3);
                assertFalse(result, "3 index is invalid");
            }

        }

        @Nested
        @DisplayName("Given empty arrayList")
        class Empty {
            @Test
            @DisplayName("0 index of ArrayList is invalid")
            public void lower() {
                boolean result = addressBook.isValidIndex(0);
                assertFalse(result, "0 index is invalid");
            }
        }


    }


    @Nested
    @DisplayName("addAccount method test")
    class addAccount {
        @Nested
        @DisplayName("Given ArrayList with length of 2")
        class Some {
            @BeforeEach
            public void setUp() {
                addressBook.addAccount(person1);
                addressBook.addAccount(person2);
                //addressBook.addAccount(person3);
            }

            @Test
            @DisplayName("Correct number of parameters results in valid user")
            public void match() {
                addressBook.addAccount(person3);
                ArrayList<Person> afterAddition = addressBook.getPersonList();

                assertEquals(afterAddition.size(), 3);
                assertEquals(afterAddition.get(2).getFullName(), "Test User");
            }

            @Test
            @DisplayName("Adding existing user results in error")
            public void noMatch() {
                addressBook.addAccount(person1);
                ArrayList<Person> result = addressBook.getPersonList();
                assertEquals(3, result.size());
            }
        }

        @Nested
        @DisplayName("Given empty ArrayList")
        class Empty {
            @Test
            @DisplayName("Method does not change anything")
            public void noMatch() {
                addressBook.addAccount(person1);
                assertNotEquals(0, addressBook.getPersonList().size());
            }
        }
    }

    @Nested
    @DisplayName("deleteAccount method test")
    class deleteAccount {
        @Nested
        @DisplayName("Given ArrayList with length of 3")
        class Some {
            @BeforeEach
            public void setUp() {
                addressBook.addAccount(person1);
                addressBook.addAccount(person2);
                addressBook.addAccount(person3);
            }

            @Test
            @DisplayName("Valid email deletes user")
            public void match() {
                addressBook.deleteAccount("johndennehy101@gmail.com");
                ArrayList<Person> result = addressBook.getPersonList();
                assertEquals(2, result.size());
            }

            @Test
            @DisplayName("Invalid email does not impact size of ArrayList")
            public void noMatch() {
                addressBook.deleteAccount("email545656");
                ArrayList<Person> result = addressBook.getPersonList();

                assertEquals(3, result.size());
            }
        }

        @Nested
        @DisplayName("Given empty ArrayList")
        class Empty {
            @Test
            @DisplayName("Method does not change anything")
            public void noMatch() {
                addressBook.deleteAccount("email546565");
                assertEquals(0, addressBook.getPersonList().size());
            }
        }
    }


    @Nested
    @DisplayName("searchByEmail method test")
    class SearchUserEmail {
        @Nested
        @DisplayName("Given ArrayList with length of 3")
        class Some {
            @BeforeEach
            public void setUp() {
                addressBook.addAccount(person1);
                addressBook.addAccount(person2);
                addressBook.addAccount(person3);
            }

            @Test
            @DisplayName("Valid email returns Person data")
            public void match() {
                Person result = addressBook.searchByEmail("johndennehy101@gmail.com");
                assertEquals("John", result.getFirstName());
            }

            @Test
            @DisplayName("Invalid email returns null")
            public void noMatch() {
                Person result = addressBook.searchByEmail("email545656");
                assertNull(result);
            }
        }

        @Nested
        @DisplayName("Given empty ArrayList")
        class Empty {
            @Test
            @DisplayName("Email returns null")
            public void noMatch() {
                Person result = addressBook.searchByEmail("email546565");
                assertNull(result);
            }
        }
    }

    @Nested
    @DisplayName("listAccountNames method test")
    class listAccountNames {
        @Nested
        @DisplayName("Given ArrayList with length of 3")
        class Some {
            @BeforeEach
            public void setUp() {
                addressBook.addAccount(person1);
                addressBook.addAccount(person2);
                addressBook.addAccount(person3);
            }

            @Test
            @DisplayName("Passing true results in names sorted by first name")
            public void match() {
                String result = addressBook.listAccountNames(true);

                String[] arrOfStr = result.split("\n", 3);
                String firstResult = "";

                for (int i = 0; i <= 1; i++) {
                    firstResult += arrOfStr[i];
                }

                assertEquals("Name: Fake Test", firstResult);

            }

            @Test
            @DisplayName("Passing false results in names sorted by last name")
            public void noMatch() {
                String result = addressBook.listAccountNames(false);
                String[] arrOfStr = result.split("\n", 3);
                String firstResult = "";
                for (int i = 0; i <= 1; i++) {
                    firstResult += arrOfStr[i];
                }
                assertNotEquals("Name: Fake Test", firstResult);
            }
        }

        @Nested
        @DisplayName("Given empty ArrayList")
        class Empty {
            @Test
            @DisplayName("Method does not list any names. Default message listed.")
            public void Match() {
                String result = addressBook.listAccountNames(true);
                assertEquals("No accounts currently present", result);
            }
        }
    }
}
