package seedu.duke.commands;

import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.item.exercise.FutureExerciseList;
import seedu.duke.item.food.FoodList;
import seedu.duke.profile.Profile;
import seedu.duke.ui.Ui;

/**
 * Abstract class used to represent executable Commands.
 * All Commands can be executed to return a CommandResult.
 */
public abstract class Command {

    public static final String COMMAND_PREFIX_DELIMITER = "/";
    public static final String COMMAND_PREFIX_EXERCISE = "e";
    public static final String COMMAND_PREFIX_FOOD = "f";
    public static final String COMMAND_PREFIX_CALORIES = "c";
    public static final String COMMAND_PREFIX_NAME = "n";
    public static final String COMMAND_PREFIX_HEIGHT = "h";
    public static final String COMMAND_PREFIX_WEIGHT = "w";
    public static final String COMMAND_PREFIX_GOAL = "g";
    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_WORD_VIEW = "view";
    public static final String COMMAND_WORD_BMI = "bmi";
    public static final String COMMAND_WORD_PROFILE = "profile";
    public static final int COMMAND_ADD_EXPECTED_NUM_DELIMITERS = 2;
    public static final int COMMAND_BMI_EXPECTED_NUM_DELIMITERS = 2;
    public static final String COMMAND_WORD_DELETE_ALL = "all";
    public static final String MESSAGE_ERROR_ITEM_NOT_SPECIFIED = "Invalid format for this command! "
            + "Please follow one of the formats:" + Ui.LS
            + "1. %1$s" + Ui.LS
            + "2. %2$s";
    public static final String MESSAGE_EMPTY_EXERCISE_LIST = "No exercise items yet!";
    public static final String MESSAGE_EMPTY_FUTURE_EXERCISE_LIST = "No future exercise items yet!";
    public static final String MESSAGE_EMPTY_FOOD_LIST = "No food items yet!";
    public static final String MESSAGE_ONLY_ONE_IN_LIST = "You have only 1 item in the list!";
    public static final String MESSAGE_LIST_OUT_OF_BOUNDS = "Please input a valid item number from 1 to %s";

    protected Profile profile;
    protected ExerciseList exerciseItems;
    protected FoodList foodItems;
    protected FutureExerciseList futureExerciseItems;


    /**
     * Returns the appropriate CommandResult after execution of the command.
     * Each child class that inherits this class represents an executable command and will have its own implementation
     * of this method specific to its functionality.
     */
    public abstract CommandResult execute();


    /**
     * Provides the necessary data structures for the command to operate on.
     */
    public void setData(Profile profile, ExerciseList exerciseItems,
                        FutureExerciseList futureExerciseItems, FoodList foodItems) {
        this.profile = profile;
        this.exerciseItems = exerciseItems;
        this.foodItems = foodItems;
        this.futureExerciseItems = futureExerciseItems;
        assert profile != null : "Profile supplied to command should not be null";
        assert exerciseItems != null : "Exercise items supplied to command should not be null";
        assert foodItems != null : "Food items supplied to command should not be null";
        assert futureExerciseItems != null : "Future exercise items supplied to command should not be null";
    }


    /**
     * Returns true if the command requires the profile storage file to be rewritten after execution.
     *
     * @param command Command that has just been executed
     * @return True if profile storage file is to be rewritten after execution of the command
     */
    public static boolean requiresProfileStorageRewrite(Command command) {
        return command instanceof ChangeHeightCommand
                || command instanceof ChangeNameCommand
                || command instanceof ChangeWeightCommand
                || command instanceof ProfileCreateCommand
                || command instanceof SetGoalCommand;
    }

    /**
     * Returns true if the command requires the exercise list storage file to be rewritten after execution.
     *
     * @param command Command that has just been executed
     * @return True if exercise list storage file is to be rewritten after execution of the command
     */
    public static boolean requiresExerciseListStorageRewrite(Command command) {
        return command instanceof AddExerciseCommand
                || command instanceof DeleteExerciseCommand;
    }

    /**
     * Returns true if the command requires the food list storage file to be rewritten after execution.
     *
     * @param command Command that has just been executed
     * @return True if food list storage file is to be rewritten after execution of the command
     */
    public static boolean requiresFoodListStorageRewrite(Command command) {
        return command instanceof AddFoodCommand
                || command instanceof DeleteFoodCommand;
    }
    
    public static boolean requiresFutureExerciseListStorageRewrite(Command command) {
        return command instanceof AddFutureExerciseCommand
                || command instanceof DeleteFutureExerciseCommand;
    }

}
