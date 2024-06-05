package motion.programming.users.exception;

public class SaveUserException extends RuntimeException {

    public SaveUserException() {
        super("There was a problem saving the user.");
    }
}
