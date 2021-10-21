package seedu.duke.commands;

import seedu.duke.profile.Profile;
import seedu.duke.profile.attributes.Name;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;

/**
 * Represents the command that when executed, changes the value of name in the Profile.
 */
public class ChangeNameCommand extends Command {
    public static final String COMMAND_WORD = "name";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD
            + " X" + QUOTATION + ", where X is your name";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to update your name? Use this format:"
            + INDENTED_LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_DO_NOT_USE_DELIMITER = "Sorry! We do not allow the character "
            + QUOTATION + COMMAND_PREFIX_DELIMITER + QUOTATION + " in your name!";
    public static final String MESSAGE_SUCCESS = "Your name has been updated!" + LS + "Hello %s!";


    private final Name name = new Name();

    public ChangeNameCommand(String name) {
        assert name != null : "parser should have ensured name is not null";
        this.name.setName(name);
    }

    private void checkIfCommandShouldExecute() throws InvalidCharacteristicException {
        if (name.getName().isEmpty()) {
            throw new InvalidCharacteristicException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        if (!name.isValid()) {       //This checks for delimiters "\" and "|"
            throw new InvalidCharacteristicException(MESSAGE_DO_NOT_USE_DELIMITER);
        }
    }

    @Override
    public CommandResult execute() {
        try {
            checkIfCommandShouldExecute();
            super.profile.setProfileName(this.name);
            return new CommandResult(String.format(MESSAGE_SUCCESS, super.profile.getProfileName().getName()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }

    }
}
