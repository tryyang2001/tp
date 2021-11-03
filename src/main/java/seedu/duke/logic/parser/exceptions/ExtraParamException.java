package seedu.duke.logic.parser.exceptions;

/**
 * Represents an error where there are extra parameters specified for the command.
 */
public class ExtraParamException extends Exception {
    private static String errorMessage;

    /**
     * General constructor without error message.
     */
    public ExtraParamException() {
    }

    /**
     * Constructor with specific error message.
     * @param errorMessage Error message to be shown when exception is caught and printed
     */
    public ExtraParamException(String errorMessage) {
        super(errorMessage);
    }
}
