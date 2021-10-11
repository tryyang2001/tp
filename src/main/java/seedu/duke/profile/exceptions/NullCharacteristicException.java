package seedu.duke.profile.exceptions;

public class NullCharacteristicException extends Exception {
    private static final String ERROR_MESSAGE = " is empty." + System.lineSeparator()
            + "Please key in a value.";

    public NullCharacteristicException(String characteristic) {
        super(characteristic + ERROR_MESSAGE);
    }
}
