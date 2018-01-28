package UserService;

import java.time.LocalDate;

public interface UserService {
    boolean register(String name, String sureName, String login, String pass, String email, LocalDate birthDate)
            throws UserExistException, IncorrectUserPasswordException;
    boolean removeUser();
    boolean login(String login, String pass);
    boolean logout();
    boolean changePass(String newPass);
    boolean isLogged();
    User whoIsLogged();
    void listUsers();
    User getUser(String name);
    void displayStatus();
}
