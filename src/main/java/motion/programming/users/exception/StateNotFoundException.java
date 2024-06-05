package motion.programming.users.exception;

public class StateNotFoundException extends RuntimeException {
    public StateNotFoundException() {
        super("State not found.");
    }
}
