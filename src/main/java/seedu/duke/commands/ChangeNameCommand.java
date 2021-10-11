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
            + "Trying to update your name? Use this format:" + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Your name has been updated!" + Ui.LS + "Hello %s!";

    private final String name;

    public ChangeNameCommand(String name) {
        this.name = name;
    }

    @Override
    public CommandResult execute() {
        if (this.name.isEmpty()) {
            return new CommandResult(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        super.profile.setName(this.name);
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.profile.getName()));
    }
}
