package UserService;

public class UserExistException extends Exception {
    public UserExistException(String msg) {
        super(msg);
    }
}
