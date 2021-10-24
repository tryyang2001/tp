package seedu.duke.logic.parser.exceptions;

/**
 * Represents an error where the parameter specified is invalid for the command.
 */
public class ParamInvalidException extends Exception {
    private static String errorMessage;

    public ParamInvalidException(String errorMessage) {
        super(errorMessage);
        ParamInvalidException.errorMessage = errorMessage;
    }
}
