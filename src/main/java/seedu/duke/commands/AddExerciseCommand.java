package seedu.duke.commands;

import seedu.duke.exercise.Exercise;
import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, adds an Exercise item to the ExerciseList.
 */
public class AddExerciseCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_ADD
            + " " + COMMAND_PREFIX_EXERCISE + COMMAND_PREFIX_DELIMITER + "exercise name"
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories" + Ui.QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to add an exercise item? Use this format:"
            + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "An exercise item has been added:" + Ui.LS + "%s";
    public static final String MESSAGE_INVALID_EXERCISE_CALORIES = "Exercise calories cannot be less than or equal to 0"
            + Ui.LS + "Try a positive value instead";

    private Exercise exercise;
    private final String description;
    private final int calories;

    public AddExerciseCommand(String description, int calories) {
        this.exercise = new Exercise(description, calories);
        this.description = description;
        this.calories = calories;
    }

    @Override
    public CommandResult execute() {
        if (exercise.getCalories() <= 0){
            return new CommandResult(MESSAGE_INVALID_EXERCISE_CALORIES);
        }
        super.exerciseItems.addExercise(this.exercise);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.exercise));
    }
}
