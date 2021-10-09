package seedu.duke.commands;

/**
 * Represents the command that when executed, calculates the BMI of the current Profile.
 */
public class CalculateBmiWithProfileCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_BMI + QUOTATION;
    public static final String MESSAGE_SUCCESS = "Your BMI value according to your current profile is %1$d (%2$s)";


    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return InvalidCommand if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, 0, "bmi_status"));
    }
}
