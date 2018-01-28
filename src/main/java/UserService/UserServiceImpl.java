package UserService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
//import java.util.logging.Logger;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService{
    protected static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
    Map<Integer, User> users = new HashMap<>();
    User loggedInUser;
    private boolean userIsLogged;

    @Override
    public boolean register(String name, String sureName, String login, String pass, String email, LocalDate birthDate)
            throws UserExistException, IncorrectUserPasswordException {
        User newUser = new User(name, sureName, login, pass, email, birthDate);
        for (User user : users.values()) {
            if (user.getLogin().equals(login) || user.getEmail().equals(email)) {
                log.info("Registering new user failed (login and email are not unique)!!!");
                throw new UserExistException("Registering new user failed (login and email are not unique)!!!");
            }
        }
        if (birthDate.isAfter(LocalDate.now().minusYears(18))) {
            log.info("User is too young (have to be 18 years old)!!!");
            return false;
        }
        if ((pass.length() < 8) ||
                (pass.chars().filter(Character::isLowerCase).count() < 1) ||
                (pass.chars().filter(Character::isUpperCase).count() < 1)) {
            log.info("Password is too simple (have to be at least 8 chars, have at least one big letter and at least one small letter)!!!");
            throw new IncorrectUserPasswordException("Password is too weak");
        }

        users.put(newUser.getId(), newUser);
        log.info("Registered new user : " + users.get(newUser.getId()));
        return true;
    }

    @Override
    public boolean removeUser() {
        if (userIsLogged) {
            users.remove(loggedInUser.getId());
            loggedInUser = null;
            userIsLogged = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean login(String login, String pass) {
        for (User user : users.values()) {
            if (user.getLogin().equals(login) && user.getPass().equals(pass)) {
                log.info("Successful log in of user " + user.getName());
                loggedInUser = user;
                userIsLogged = true;
                return true;
            } else {
                log.info("Unsuccessful login attempt of user " + login + " (entered password: " + pass +
                        ", should be " + user.getPass() + ")");
            }
        }
        log.info("Unsuccessful login attempt of user " + login + " (user does not exist)");
        return false;
    }

    @Override
    public boolean logout() {
        if (userIsLogged) {
            loggedInUser = null;
            userIsLogged = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean changePass(String newPass) {
        if (userIsLogged) {
            log.info("Successful changed password for user " + loggedInUser.getName());
            loggedInUser.setPass(newPass);
            return true;
        } else {
            log.info("Unsuccessful change password attempt (user not logged in)");
            return false;
        }
    }

    @Override
    public boolean isLogged() {
        return userIsLogged;
    }

    @Override
    public User whoIsLogged() {
        return (userIsLogged ? loggedInUser : null);
    }

    @Override
    public void listUsers() {
        for (User user : users.values()) {
            System.out.println(user.toString());
        }
    }

    @Override
    public User getUser(String name) {
        for (User user : users.values()) {
            if (user.getLogin().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void displayStatus() {
        System.out.println("Users in database : " + users.size() + "\n" +
                "User logged in : " + (userIsLogged ? loggedInUser.toString() : "none"));
    }
}
