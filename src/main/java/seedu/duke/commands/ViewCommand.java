package seedu.duke.commands;

/**
 * Represents the command that when executed, lists all the items in the FoodList and ExerciseList.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
    public static final String MESSAGE_SUCCESS = "What you have recorded so far:" + LS
            + "Food items consumed:" + LS + "%1$s" + LS
            + "Exercises done:" + LS + "%2$s" + LS;

    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return InvalidCommand if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, "list_of_food", "list_of_exercises"));
    }
}
