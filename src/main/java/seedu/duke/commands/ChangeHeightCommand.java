package seedu.duke.commands;

import seedu.duke.profile.Profile;
import seedu.duke.profile.attributes.Height;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, changes the value of height in the Profile.
 */
public class ChangeHeightCommand extends Command {
    public static final String COMMAND_WORD = "height";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD
            + " X" + QUOTATION + ", where X is your height in CM";
    public static final String MESSAGE_SUCCESS = "Your height has been updated!" + LS + "Your height is %scm.";

    private Logger logger = Logger.getLogger(ChangeHeightCommand.class.getName());
    private final Height height = new Height();

    public ChangeHeightCommand(double height) {
        this.height.setHeight(height);
    }

    private void checkIfCommandShouldExecute() throws InvalidCharacteristicException {
        if (!height.isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_HEIGHT);
        }
    }

    @Override
    public CommandResult execute() {
        try {
            checkIfCommandShouldExecute();
            super.profile.setProfileHeight(this.height);
            return new CommandResult(String.format(MESSAGE_SUCCESS, super.profile.getProfileHeight().getHeight()));
        } catch (InvalidCharacteristicException e) {
            logger.log(Level.WARNING, "Detected negative height input");
            return new CommandResult(e.getMessage());
        }
    }
}
