package UserService;

import java.time.LocalDate;

public class User {
    private static int idGenerator = 0;
    private Integer id;
    private String name;
    private String sureName;
    private String login;
    private String pass;
    private String email;
    private LocalDate birthDate;

    public User(String name, String sureName, String login, String pass, String email, LocalDate birthDate) {
        this.id = idGenerator++;
        this.name = name;
        this.sureName = sureName;
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.birthDate = birthDate;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(Integer idGenerator) {
        User.idGenerator = idGenerator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sureName='" + sureName + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
