package seedu.duke.commands;

import seedu.duke.item.exercise.Exercise;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EditExerciseBankCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_EDIT
            + " " + COMMAND_PREFIX_EXERCISE_BANK + COMMAND_PREFIX_DELIMITER + "X "
            + "+" + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "Z " + QUOTATION
            + ", where X is the item number in the exercise bank, Y is the new name, Z is the new calories";
    public static final String MESSAGE_SUCCESS = "Exercise bank item number %d has been changed to:"
            + INDENTED_LS + "%s";

    private static Logger logger = Logger.getLogger(EditExerciseBankCommand.class.getName());

    private final int itemIndex;
    private final String newName;
    private final int newCalories;

    public EditExerciseBankCommand(int itemIndex, String newName, int newCalories) {
        this.itemIndex = itemIndex;
        this.newName = newName;
        this.newCalories = newCalories;
    }

    @Override
    public CommandResult execute() {
        if (super.exerciseBank.getSize() == 0) {
            logger.log(Level.WARNING, "Exercise bank list is empty.");
            return new CommandResult(MESSAGE_EMPTY_FOOD_BANK);
        }
        try {
            super.exerciseBank.getItem(this.itemIndex).setName(this.newName);
            super.exerciseBank.getItem(this.itemIndex).setCalories(this.newCalories);
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.itemIndex,
                    super.exerciseBank.getItem(this.itemIndex).toString()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected invalid exercise bank item index.");
            if (super.exerciseBank.getSize() == 1) {
                return new CommandResult(MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(MESSAGE_LIST_OUT_OF_BOUNDS, super.exerciseBank.getSize()));
        }
    }

}
