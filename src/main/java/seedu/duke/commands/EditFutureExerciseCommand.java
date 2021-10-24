package seedu.duke.commands;

import seedu.duke.item.exercise.Exercise;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EditFutureExerciseCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_EDIT
            + " " + COMMAND_PREFIX_UPCOMING_EXERCISE + COMMAND_PREFIX_DELIMITER + "X "
            + "+" + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "Z " + QUOTATION
            + ", where X is the item number in the future exercise list, Y is the new name, Z is the new calories";
    public static final String MESSAGE_SUCCESS = "Exercise item number %d has been changed to:"
            + INDENTED_LS + "%s";

    private static Logger logger = Logger.getLogger(EditFutureExerciseCommand.class.getName());

    private final int itemIndex;
    private final String newName;
    private final int newCalories;

    public EditFutureExerciseCommand(int itemIndex, String newName, int newCalories) {
        this.itemIndex = itemIndex;
        this.newName = newName;
        this.newCalories = newCalories;
    }

    @Override
    public CommandResult execute() {
        if (super.futureExerciseItems.getSize() == 0) {
            logger.log(Level.WARNING, "Future exercise list is empty.");
            return new CommandResult(MESSAGE_EMPTY_FUTURE_EXERCISE_LIST);
        }
        try {
            super.futureExerciseItems.getItem(this.itemIndex).setName(this.newName);
            super.futureExerciseItems.getItem(this.itemIndex).setCalories(this.newCalories);
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.itemIndex,
                    super.futureExerciseItems.getItem(this.itemIndex).toString()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected invalid exercise item index.");
            if (super.futureExerciseItems.getSize() == 1) {
                return new CommandResult(MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(MESSAGE_LIST_OUT_OF_BOUNDS, super.futureExerciseItems.getSize()));
        }
    }
}
