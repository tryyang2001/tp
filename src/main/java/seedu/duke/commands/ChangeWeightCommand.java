package seedu.duke.commands;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, changes the value of weight in the Profile.
 */
public class ChangeWeightCommand extends Command {
    public static final String COMMAND_WORD = "weight";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD
            + " X" + Ui.QUOTATION + "where X is your weight in KG";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to update your weight? Use this format:"
            + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Your weight has been updated!" + Ui.LS + "Your weight is %skg.";

    private final double weight;

    public ChangeWeightCommand(double weight) {
        this.weight = weight;
    }

    @Override
    public CommandResult execute() {
        try {
            super.profile.setWeight(this.weight);
            return new CommandResult(String.format(MESSAGE_SUCCESS, super.profile.getWeight()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
