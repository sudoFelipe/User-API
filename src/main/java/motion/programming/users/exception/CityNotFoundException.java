package motion.programming.users.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException() {
        super("City not found.");
    }
}
