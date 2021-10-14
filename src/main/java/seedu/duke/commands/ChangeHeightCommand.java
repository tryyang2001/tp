package seedu.duke.commands;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, changes the value of height in the Profile.
 */
public class ChangeHeightCommand extends Command {
    public static final String COMMAND_WORD = "height";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD
            + " X" + Ui.QUOTATION + ", where X is your height in CM";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to update your height? Use this format:"
            + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Your height has been updated!" + Ui.LS + "Your height is %scm.";
    public static final String MESSAGE_HELP =  "height -- changes height in profile." + Ui.INDENTED_LS
                 + Ui.FORMAT_HEADER + MESSAGE_COMMAND_FORMAT + Ui.LS + Ui.LS;

    private final double height;

    public ChangeHeightCommand(double height) {
        this.height = height;
    }

    @Override
    public CommandResult execute() {
        try {
            super.profile.setHeight(this.height);
            return new CommandResult(String.format(MESSAGE_SUCCESS, super.profile.getHeight()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
