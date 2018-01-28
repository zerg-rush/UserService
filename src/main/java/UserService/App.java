package UserService;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UserExistException, IncorrectUserPasswordException {
        UserServiceImpl myService = new UserServiceImpl();

        myService.register("Jan", "Kowalski", "kowal", "Kowal123", "kowal@onet.pl",
                LocalDate.parse("1980-01-01"));

        myService.register("Adam", "Malinowski", "malina", "Malina321", "malina@onet.pl",
                LocalDate.parse("1985-01-01"));

        myService.register("Myszka", "Miki", "mouse", "Qwerty123", "mouse@onet.pl",
                LocalDate.parse("2000-01-01"));

        myService.listUsers();

        System.out.println("Trying to login as kowal with wrong password. Status : " +
                (myService.login("kowal", "Kowal123bad") ? "SUCCESS" : "FAIL"));

        System.out.println("Trying to login as kowal with correct password. Status : " +
                (myService.login("kowal", "Kowal123") ? "SUCCESS" : "FAIL"));

        myService.displayStatus();

        myService.changePass("Nowy_pass_kowala_123");

        System.out.println("user kowal after password change : " + myService.getUser("kowal"));
    }
}
