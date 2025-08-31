package pepero;

/**
 * Represents a custom exception for the Pepero application.
 */
public class PeperoException extends Exception {

    /**
     * Constructs a new PeperoException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public PeperoException(String message) {
        super(message);
    }
}
