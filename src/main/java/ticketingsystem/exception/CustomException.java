package ticketingsystem.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L; // Unique identifier for serialization

    // Constructor for creating a CustomException with a message
    public CustomException(String message) {
        super(message);
    }

    // Constructor for creating a CustomException with a message and a cause
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
