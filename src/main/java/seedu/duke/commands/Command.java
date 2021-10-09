package seedu.duke.commands;

/**
 * Abstract class used to represent executable Commands.
 * All Commands can be executed to return a CommandResult.
 */
public abstract class Command {

    //TODO: These constants are to be moved to UI class
    public static final String QUOTATION = "\"";
    public static final String LS = System.lineSeparator();

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
    public static final String MESSAGE_ERROR_ITEM_NOT_SPECIFIED = "Invalid format for this command! "
            + "Please follow one of the formats:" + LS
            + "1. %1$s" + LS
            + "2. %2$s";

    /* TODO: Add Profile, ExerciseList, FoodList as protected attributes
     protected Profile profile;
     protected ExerciseList exercises;
     protected FoodList foods;
     */

    /**
     * Returns the appropriate CommandResult after execution of the command.
     * Each child class that inherits this class represents an executable command and will have its own implementation
     * of this method specific to its functionality.
     */
    public abstract CommandResult execute();

    /* TODO:
    public void setData(Profile profile, ExerciseList exercises, FoodList foods) {
        this.profile = profile;
        this.exercises = exercises;
        this.foods = foods;
    };
     */
}
