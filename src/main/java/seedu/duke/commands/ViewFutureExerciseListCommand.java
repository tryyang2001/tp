package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, lists all the items in the FutureExerciseList.
 */
public class ViewFutureExerciseListCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_VIEW
            + " " + COMMAND_PREFIX_UPCOMING_EXERCISE + COMMAND_PREFIX_DELIMITER + QUOTATION;
    public static final String MESSAGE_FUTURE_EXERCISE = "You have %d upcoming exercise(s):"
            + LS + "%2$s";

    private static Logger logger = Logger.getLogger("ViewFutureExerciseCommand");

    @Override
    public CommandResult execute() {
        if (super.futureExerciseItems.getSize() == 0) {
            logger.log(Level.WARNING, "Future exercise list is empty");
            return new CommandResult(MESSAGE_EMPTY_EXERCISE_LIST);
        }
        assert futureExerciseItems.getSize() > 0 : "Future exercise list is not empty";
        return new CommandResult(String.format(MESSAGE_FUTURE_EXERCISE, super.futureExerciseItems.getSize(),
                super.futureExerciseItems.convertToString()));
    }
}
