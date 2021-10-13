package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, calculates the BMI of the current Profile.
 */
public class CalculateBmiWithProfileCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_BMI + Ui.QUOTATION;
    public static final String MESSAGE_SUCCESS = "Your BMI value according to your current profile is:"
            + Ui.INDENTED_LS + "%1$,.1f (%2$s)";


    @Override
    public CommandResult execute() {
        final double bmi = super.profile.calculateBmi();
        return new CommandResult(String.format(MESSAGE_SUCCESS, bmi, super.profile.retrieveBmiStatus(bmi)));
    }
}
