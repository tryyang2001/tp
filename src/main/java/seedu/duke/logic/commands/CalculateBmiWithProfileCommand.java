package seedu.duke.logic.commands;

import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;

/**
 * Represents the command that when executed, calculates the BMI of the current Profile.
 */
public class CalculateBmiWithProfileCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_BMI + CommandMessages.QUOTATION;
    public static final String MESSAGE_SUCCESS = "Your BMI value according to your current profile is:"
            + CommandMessages.INDENTED_LS + "%1$,.1f (%2$s)";
    public static final String MESSAGE_UNINITIALIZED_PROFILE = "Your profile has not been initialized yet.";

    private void checkIfCommandShouldExecute() throws InvalidCharacteristicException {
        if (!super.profile.getProfileWeight().isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_WEIGHT);
        }
        if (!super.profile.getProfileHeight().isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_HEIGHT);
        }
    }

    @Override
    public CommandResult execute() {
        try {
            checkIfCommandShouldExecute();
            final double bmi = super.profile.calculateBmi();
            return new CommandResult(String.format(MESSAGE_SUCCESS, bmi, super.profile.retrieveBmiStatus(bmi)));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(MESSAGE_UNINITIALIZED_PROFILE);
        }
    }
}
