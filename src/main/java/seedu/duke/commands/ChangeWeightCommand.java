package seedu.duke.commands;

/**
 * Represents the command that when executed, changes the value of weight in the Profile.
 */
public class ChangeWeightCommand extends Command {
    public static final String COMMAND_WORD = "weight";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD
            + " X" + QUOTATION + "where X is your weight in KG";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to update your weight? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Your weight has been updated!" + LS + "Your weight is %skg.";

    private final double weight;

    public ChangeWeightCommand(double weight) {
        this.weight = weight;
    }

    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return CommandResult with error message if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.weight));
    }
}
