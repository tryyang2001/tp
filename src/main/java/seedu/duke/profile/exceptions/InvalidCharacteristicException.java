package seedu.duke.profile.exceptions;

/**
 * Exception that is thrown when the input human characteristics are invalid.
 */
public class InvalidCharacteristicException extends Exception {

    public InvalidCharacteristicException(String message) {
        super(message);
    }
}
