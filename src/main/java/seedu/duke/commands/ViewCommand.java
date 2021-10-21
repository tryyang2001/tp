package seedu.duke.commands;

/**
 * Represents the command that when executed, lists all the items in the FoodList and ExerciseList.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
    public static final String MESSAGE_SUCCESS = "This is what you have recorded so far!";
    public static final String MESSAGE_FOOD = "You have consumed %1$d food item(s):"
            + LS + "%2$s";
    public static final String MESSAGE_EXERCISE = "You have done %1$d exercise(s):"
            + LS + "%2$s";


    @Override
    public CommandResult execute() {
        String foodMessage;
        if (super.foodItems.getSize() == 0) {
            foodMessage = MESSAGE_EMPTY_FOOD_LIST;
        } else {
            foodMessage = String.format(MESSAGE_FOOD, super.foodItems.getSize(),
                    super.foodItems.convertToString());
        }
        String exerciseMessage;
        if (super.exerciseItems.getSize() == 0) {
            exerciseMessage = MESSAGE_EMPTY_EXERCISE_LIST;
        } else {
            exerciseMessage = String.format(MESSAGE_EXERCISE, super.exerciseItems.getSize(),
                    super.exerciseItems.convertToString());
        }
        return new CommandResult(MESSAGE_SUCCESS + LS + foodMessage + LS + LS + exerciseMessage);
    }

}
