package seedu.duke.commands;

/**
 * Represents the command that when executed, calculates the BMI with the given height and weight data.
 */
public class CalculateBmiCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_BMI
            + " " + COMMAND_PREFIX_HEIGHT + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_WEIGHT + COMMAND_PREFIX_DELIMITER + "Z"
            + QUOTATION + " where X is the height in CM and Y is the weight in KG";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to calculate BMI for a certain height and weight? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "The calculated BMI value is %1$d (%2$s)";

    private final double height;
    private final double weight;

    public CalculateBmiCommand(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return InvalidCommand if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, 0, "bmi_status"));
    }
}
