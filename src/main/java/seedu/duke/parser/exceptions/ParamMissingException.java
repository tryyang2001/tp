package seedu.duke.parser.exceptions;

/**
 * Represents an error where a parameter required for the command is not specified.
 */
public class ParamMissingException extends Exception {
    private static String errorMessage;

    public ParamMissingException(String errorMessage) {
        super(errorMessage);
        ParamMissingException.errorMessage = errorMessage;
    }
}
