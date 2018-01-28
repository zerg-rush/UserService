package UserService;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserServiceImpl myService = new UserServiceImpl();

        myService.register("Jan", "Kowalski", "kowal", "kowal123", "kowal@onet.pl",
                LocalDate.parse("1980-01-01"));

        myService.register("Adam", "Malinowski", "malina", "malina321", "malina@onet.pl",
                LocalDate.parse("1985-01-01"));

        myService.register("Myszka", "Miki", "mouse", "qwerty", "mouse@onet.pl",
                LocalDate.parse("1950-01-01"));

        myService.listUsers();

        System.out.println("Trying to login as kowal with wrong password. Status : " +
                (myService.login("kowal", "kowal123bad") ? "SUCCESS" : "FAIL"));

        System.out.println("Trying to login as kowal with correct password. Status : " +
                (myService.login("kowal", "kowal123") ? "SUCCESS" : "FAIL"));

        myService.displayStatus();

        myService.changePass("nowy_pass_kowala");

        System.out.println("user kowal after password change : " + myService.getUser("kowal"));
    }
}
