package seedu.duke.commands;

/**
 * Represents the command that when executed, deletes an Exercise item from the ExerciseList.
 */
public class DeleteExerciseCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_EXERCISE + COMMAND_PREFIX_DELIMITER + "X" + QUOTATION
            + ", where X is the item number in the exercise list";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to delete an exercise item? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "An exercise item has been deleted:" + LS + "%s";

    private final int itemIndex;

    public DeleteExerciseCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public CommandResult execute() {
        //TODO: Check if list is empty first, print error if it is
        //TODO: Call relevant method, catch exceptions (index out of bounds)
        // and return CommandResult with error message if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.itemIndex));
    }
}
