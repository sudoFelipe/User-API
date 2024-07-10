package motion.programming.users.exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found.");
    }
}
