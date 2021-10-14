package seedu.duke.commands;

import seedu.duke.ui.Ui;


/**
 * Represents the command that when executed, lists all the items in the FoodList and ExerciseList.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    public static final String MESSAGE_SUCCESS = "This is what you have recorded so far!" + Ui.LS;
    public static final String MESSAGE_FOOD = "You have consumed %1$d food item(s):"
            + Ui.LS + "%2$s"
            + Ui.LS + "Total calories consumed: %3$s";
    public static final String MESSAGE_EXERCISE = "You have done %1$d exercise(s):"
            + Ui.LS + "%2$s"
            + Ui.LS + "Total calories burnt: %3$s";
    public static final String MESSAGE_HELP = "view -- Views all the food and/or exercises added." + Ui.INDENTED_LS
                 + Ui.FORMAT_HEADER + MESSAGE_COMMAND_FORMAT + Ui.LS + Ui.LS;

    @Override
    public CommandResult execute() {
        String foodMessage;
        if (super.foodItems.getSize() == 0) {
            foodMessage = MESSAGE_EMPTY_FOOD_LIST;
        } else {
            foodMessage = String.format(MESSAGE_FOOD, super.foodItems.getSize(),
                    super.foodItems.convertToString(),
                    super.foodItems.getTotalCalories());
        }

        String exerciseMessage;
        if (super.exerciseItems.getSize() == 0) {
            exerciseMessage = MESSAGE_EMPTY_EXERCISE_LIST;
        } else {
            exerciseMessage = String.format(MESSAGE_EXERCISE, super.exerciseItems.getSize(),
                    super.exerciseItems.convertToString(),
                    super.exerciseItems.getTotalCalories());
        }

        return new CommandResult(MESSAGE_SUCCESS + foodMessage + exerciseMessage);
    }
}
