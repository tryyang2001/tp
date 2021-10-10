package seedu.duke.commands;

/**
 * Represents the command that when executed, changes the value of height in the Profile.
 */
public class ChangeHeightCommand extends Command {
    public static final String COMMAND_WORD = "height";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD
            + " X" + QUOTATION + "where X is your height in CM";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to update your height? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Your height has been updated!" + LS + "Your height is %scm.";

    private final double height;

    public ChangeHeightCommand(double height) {
        this.height = height;
    }

    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return CommandResult with error message if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.height));
    }
}
