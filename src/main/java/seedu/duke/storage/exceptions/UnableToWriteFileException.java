package seedu.duke.storage.exceptions;

public class UnableToWriteFileException extends Exception {
    private static final String ERROR_MESSAGE = "Unable to write to file, something went wrong while saving";

    public UnableToWriteFileException() {
        super(ERROR_MESSAGE);
    }
}
