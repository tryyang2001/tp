package seedu.duke.commands;

import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, lists all the items in the FutureExerciseList.
 */
public class ViewFutureExerciseList extends Command{
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_VIEW
            + " " + COMMAND_PREFIX_EXERCISE + COMMAND_PREFIX_DELIMITER + Ui.QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format!"
            + "Trying to view the future exercise list? Use this format:"
            + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_FUTURE_EXERCISE = "You have %d upcoming exercise(s):"
            + Ui.LS + "%2$s";

    private static Logger logger = Logger.getLogger("ViewExerciseCommand");

    @Override
    public CommandResult execute() {
        if (super.futureExerciseItems.getSize() == 0) {
            logger.log(Level.WARNING,"Future exercise list is empty");
            return new CommandResult(MESSAGE_EMPTY_EXERCISE_LIST);
        }
        assert futureExerciseItems.getSize() > 0 : "Future exercise list is not empty";
        return new CommandResult(String.format(MESSAGE_FUTURE_EXERCISE, super.futureExerciseItems.getSize(),
                super.futureExerciseItems.convertToString()));
    }
}
