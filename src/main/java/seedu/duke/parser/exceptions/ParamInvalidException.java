package seedu.duke.parser.exceptions;

/**
 * Represents an error where the user did not specify a required parameter,
 * or parameter specified is invalid for the command.
 */
public class ParamInvalidException extends Exception {
    private static String errorMessage;

    public ParamInvalidException(String errorMessage) {
        super(errorMessage);
        ParamInvalidException.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
