package seedu.duke.commands;

/**
 * Represents the result of the execution of the commands to be displayed to the user.
 */
public class CommandResult {
    public final String messageToBeShown;

    public CommandResult(String messageToBeShown) {
        this.messageToBeShown = messageToBeShown;
    }

    @Override
    public String toString() {
        return this.messageToBeShown;
    }
}
