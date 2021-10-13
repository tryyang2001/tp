package seedu.duke.commands;

import seedu.duke.exercise.Exercise;
import seedu.duke.ui.Ui;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes an Exercise item from the ExerciseList.
 */
public class DeleteExerciseCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_EXERCISE + COMMAND_PREFIX_DELIMITER + "X" + Ui.QUOTATION
            + ", where X is the item number in the exercise list";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to delete an exercise item? Use this format:"
            + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "An exercise item has been deleted:" + Ui.LS + "%s"
            + Ui.LS + "Number of exercise item(s) left: %2$d";
    private static final String MESSAGE_EXERCISE_CLEAR = "All exercise items have been removed.";

    private static Logger logger = Logger.getLogger("DeleteExerciseCommand");

    private final int itemIndex;

    public DeleteExerciseCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public CommandResult execute() {
        if (this.itemIndex == 0) {
            logger.log(Level.INFO,"Clearing exercise list");
            super.exerciseItems.clearExerciseList();
            return new CommandResult(MESSAGE_EXERCISE_CLEAR);
        }

        assert this.itemIndex > 0 : "Deleting an item only";

        if (super.exerciseItems.getSize() == 0) {
            logger.log(Level.WARNING,"Exercise list is empty");
            return new CommandResult(MESSAGE_EMPTY_EXERCISE_LIST);
        }

        logger.log(Level.INFO,"Trying to delete item now");

        try {
            Exercise deletedExercise;
            deletedExercise = super.exerciseItems.deleteExercise(this.itemIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, deletedExercise, super.exerciseItems.getSize()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING,"Index is out of bound");
            if (super.exerciseItems.getSize() == 1) {
                return new CommandResult(MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(MESSAGE_LIST_OUT_OF_BOUNDS, super.exerciseItems.getSize()));
        }
    }
}
