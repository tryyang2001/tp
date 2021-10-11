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
    public static final String MESSAGE_SUCCESS = "You have done %1$d exercise(s):" + Ui.LS + "%2$s";


    @Override
    public CommandResult execute() {
        if (super.exerciseItems.getSize() == 0) {
            return new CommandResult(MESSAGE_EMPTY_EXERCISE_LIST);
        }
        final String stringOfAllItems = super.exerciseItems.convertToString();
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.exerciseItems.getSize(), stringOfAllItems));
    }
}
