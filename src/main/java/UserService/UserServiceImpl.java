package UserService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService{
    protected static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
    Map<Integer, User> users = new HashMap<>();
    User loggedInUser;
    private boolean userIsLogged;

    @Override
    public boolean register(String name, String sureName, String login, String pass, String email, LocalDate birthDate) {
        User newUser = new User(name, sureName, login, pass, email, birthDate);
        users.put(newUser.getId() , newUser);
        //log.info("Registered new user : " + users.get(newUser.getId()));
        return false;
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
                log.info("Successfull log in of user " + user.getName());
                loggedInUser = user;
                userIsLogged = true;
                return true;
            }
        }
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
            log.info("Successfull changed password for user " + loggedInUser.getName());
            loggedInUser.setPass(newPass);
            return true;
        } else {
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
