package seedu.duke.commands;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, calculates the BMI with the given height and weight data.
 */
public class CalculateBmiCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_BMI
            + " " + COMMAND_PREFIX_HEIGHT + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_WEIGHT + COMMAND_PREFIX_DELIMITER + "Z"
            + Ui.QUOTATION + " where X is the height in CM and Y is the weight in KG";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to calculate BMI for a certain height and weight? Use this format:"
            + Ui.INDENTED_LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "The calculated BMI value is %1$,.1f (%2$s)";
    public static final String MESSAGE_HELP = "bmi -- "
            + "Calculates the BMI value based on the user's input height and weight." + Ui.INDENTED_LS
                 + Ui.FORMAT_HEADER + MESSAGE_COMMAND_FORMAT + Ui.INDENTED_LS
                 + Ui.FORMAT_PLACEHOLDER + CalculateBmiWithProfileCommand.MESSAGE_COMMAND_FORMAT + Ui.LS + Ui.LS;

    private final double height;
    private final double weight;

    public CalculateBmiCommand(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public CommandResult execute() {
        try {
            final double bmi = super.profile.calculateBmi(this.height, this.weight);
            return new CommandResult(String.format(MESSAGE_SUCCESS, bmi, super.profile.retrieveBmiStatus(bmi)));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }

    }
}
