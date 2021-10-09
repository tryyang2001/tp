package seedu.duke.commands;

/**
 * Represents the command that when executed, displays the overview of calorie statistics.
 */
public class OverviewCommand extends Command {
    public static final String COMMAND_WORD = "overview";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
    public static final String MESSAGE_SUCCESS = "This is an overview of your day:" + LS
            + "Calories consumed: %1$d calories" + LS
            + "Calories lost: %2$d calories" + LS
            + "Net calories: %3$d calories" + LS + "%4$s";


    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return InvalidCommand if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, 1, 1, 2, "goal_status"));
    }
}
