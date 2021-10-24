package seedu.duke.logic.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, lists all the items in the ExerciseList.
 */
public class ViewExerciseListCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_VIEW
            + " " + COMMAND_PREFIX_EXERCISE + COMMAND_PREFIX_DELIMITER + QUOTATION;
    public static final String MESSAGE_SUCCESS = "You have done %1$d exercise(s):"
            + LS + "%2$s";


    private static Logger logger = Logger.getLogger("ViewExerciseCommand");

    @Override
    public CommandResult execute() {
        if (super.exerciseItems.getSize() == 0) {
            logger.log(Level.WARNING, "Exercise list is empty");
            return new CommandResult(MESSAGE_EMPTY_EXERCISE_LIST);
        }
        assert exerciseItems.getSize() > 0 : "Exercise list is not empty";
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.exerciseItems.getSize(),
                super.exerciseItems.convertToString()));
    }
}
