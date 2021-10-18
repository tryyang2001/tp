package seedu.duke.commands;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;

/**
 * Represents the command that when executed, calculates the BMI of the current Profile.
 */
public class CalculateBmiWithProfileCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_BMI + QUOTATION;
    public static final String MESSAGE_SUCCESS = "Your BMI value according to your current profile is:"
            + INDENTED_LS + "%1$,.1f (%2$s)";
    public static final String MESSAGE_UNINITIALIZED_PROFILE = "Your profile has not been initialized yet.";

    @Override
    public CommandResult execute() {
        try {
            final double bmi = super.profile.calculateBmi();
            return new CommandResult(String.format(MESSAGE_SUCCESS, bmi, super.profile.retrieveBmiStatus(bmi)));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(MESSAGE_UNINITIALIZED_PROFILE);
        }
    }
}
