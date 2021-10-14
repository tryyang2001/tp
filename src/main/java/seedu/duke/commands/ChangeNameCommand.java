package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, changes the value of name in the Profile.
 */
public class ChangeNameCommand extends Command {
    public static final String COMMAND_WORD = "name";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD
            + " X" + Ui.QUOTATION + ", where X is your name";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to update your name? Use this format:"
            + Ui.INDENTED_LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_DO_NOT_USE_DELIMITER = "Sorry! We do not allow the character "
            + Ui.QUOTATION + COMMAND_PREFIX_DELIMITER + Ui.QUOTATION + " in your name!";
    public static final String MESSAGE_SUCCESS = "Your name has been updated!" + Ui.LS + "Hello %s!";
    public static final String MESSAGE_HELP = "name -- change the name in the profile." + Ui.LS
                 + Ui.FORMAT_HEADER + MESSAGE_COMMAND_FORMAT + Ui.LS;

    private final String name;

    public ChangeNameCommand(String name) {
        assert name != null : "parser should have ensured name is not null";
        this.name = name;
    }

    @Override
    public CommandResult execute() {
        if (this.name.isEmpty()) {
            return new CommandResult(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        if (this.name.contains(COMMAND_PREFIX_DELIMITER)) {
            return new CommandResult(MESSAGE_DO_NOT_USE_DELIMITER);
        }

        super.profile.setName(this.name);
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.profile.getName()));
    }
}
