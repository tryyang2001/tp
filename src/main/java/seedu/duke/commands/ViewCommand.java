package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, lists all the items in the FoodList and ExerciseList.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    public static final String MESSAGE_SUCCESS = "What you have recorded so far:" + Ui.LS
            + "Food items consumed:" + Ui.LS + "%1$s" + Ui.LS
            + "Exercises done:" + Ui.LS + "%2$s" + Ui.LS;

    @Override
    public CommandResult execute() {
        String foodMessage = "";
        if (super.foodItems.getSize() == 0) {
           foodMessage = foodMessage + MESSAGE_EMPTY_FOOD_LIST;
        }
        foodMessage = foodMessage + super.foodItems.convertToString();

        String exerciseMessage = "";
        if (super.exerciseItems.getSize() == 0) {
            exerciseMessage = exerciseMessage + MESSAGE_EMPTY_EXERCISE_LIST;
        }
        exerciseMessage = exerciseMessage + "exercises list (placeholder)";

        return new CommandResult(String.format(MESSAGE_SUCCESS, foodMessage, exerciseMessage));
    }
}
