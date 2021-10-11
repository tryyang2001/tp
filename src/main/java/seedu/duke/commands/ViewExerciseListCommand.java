package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, lists all the items in the ExerciseList.
 */
public class ViewExerciseListCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_VIEW
            + " " + COMMAND_PREFIX_EXERCISE + COMMAND_PREFIX_DELIMITER + Ui.QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format!"
            + "Trying to view the exercise list? Use this format:"
            + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "You have done %1$d exercises:" + Ui.LS + "%2$s";


    @Override
    public CommandResult execute() {
        //TODO: Check if list is empty first, print error if it is
        //TODO: Call relevant method, catch exceptions and return CommandResult with error message if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, 0, "list of exercise items (placeholder)"));
    }
}
