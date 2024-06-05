package motion.programming.users.exception;

public class IbgeIntegrationException extends RuntimeException {

    public IbgeIntegrationException() {
        super("IBGE API integration fail.");
    }
}
