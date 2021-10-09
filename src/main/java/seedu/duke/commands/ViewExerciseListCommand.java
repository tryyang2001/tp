package seedu.duke.commands;

/**
 * Represents the command that when executed, lists all the items in the ExerciseList.
 */
public class ViewExerciseListCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_VIEW
            + " " + COMMAND_PREFIX_EXERCISE + COMMAND_PREFIX_DELIMITER + QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format!"
            + "Trying to view the exercise list? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "You have done %1$d exercises:" + LS + "%2$s";


    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return InvalidCommand if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, 0, "list of exercise items (placeholder)"));
    }
}
