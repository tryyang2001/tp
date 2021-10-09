package seedu.duke.commands;

/**
 * Represents the command that when executed, adds an Exercise item to the ExerciseList.
 */
public class AddExerciseCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_ADD
            + " " + COMMAND_PREFIX_EXERCISE + COMMAND_PREFIX_DELIMITER + "exercise name"
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories" + QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to add an exercise item? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "An exercise item has been added:" + LS + "%s";

    //private Exercise exercise;
    private final String description;
    private final int calories;

    public AddExerciseCommand(String description, int calories) {
        //this.exercise = new Exercise(description, calories);
        this.description = description;
        this.calories = calories;
    }

    @Override
    public CommandResult execute() {
        //TODO: Call method to add, catch exceptions and return InvalidCommand if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, description) + " Calories: " + calories);
    }
}
