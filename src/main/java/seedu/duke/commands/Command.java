package seedu.duke.commands;

import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;
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
    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_WORD_VIEW = "view";
    public static final String COMMAND_WORD_BMI = "bmi";
    public static final String COMMAND_WORD_DELETE_ALL = "all";
    public static final String MESSAGE_ERROR_ITEM_NOT_SPECIFIED = "Invalid format for this command! "
            + "Please follow one of the formats:" + Ui.LS
            + "1. %1$s" + Ui.LS
            + "2. %2$s";
    public static final String MESSAGE_EMPTY_EXERCISE_LIST = "No exercise items yet!";
    public static final String MESSAGE_EMPTY_FOOD_LIST = "No food items yet!";
    public static final String MESSAGE_ONLY_ONE_IN_LIST = "You have only 1 item in the list!";
    public static final String MESSAGE_LIST_OUT_OF_BOUNDS = "Please input a valid item number from 1 to %s";

    protected Profile profile;
    protected ExerciseList exerciseItems;
    protected FoodList foodItems;



    /**
     * Returns the appropriate CommandResult after execution of the command.
     * Each child class that inherits this class represents an executable command and will have its own implementation
     * of this method specific to its functionality.
     */
    public abstract CommandResult execute();


    public void setData(Profile profile, ExerciseList exerciseItems, FoodList foodItems) {
        this.profile = profile;
        this.exerciseItems = exerciseItems;
        this.foodItems = foodItems;
    }

    ;

}
