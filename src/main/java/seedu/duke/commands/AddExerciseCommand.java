package seedu.duke.commands;

import seedu.duke.item.exercise.Exercise;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static final String MESSAGE_SUCCESS = "An exercise item has been added:"
            + INDENTED_LS + "%s";
    public static final String MESSAGE_INVALID_EXERCISE_CALORIES = "Exercise calories cannot be less than or equal to 0"
            + LS + "Try a positive value instead";

    private static Logger logger = Logger.getLogger(AddExerciseCommand.class.getName());

    private Exercise exercise;

    public AddExerciseCommand(String description, int calories) {
        this.exercise = new Exercise(description, calories);
    }

    public AddExerciseCommand(String description, int calories, LocalDate date) {
        this.exercise = new Exercise(description, calories, date);
    }

    @Override
    public CommandResult execute() {
        if (exercise.getCalories() <= 0) {
            logger.log(Level.WARNING, "Exercise calorie is invalid");
            return new CommandResult(MESSAGE_INVALID_EXERCISE_CALORIES);
        }
        assert exercise.getCalories() > 0 : "Exercise calorie is valid";
        super.exerciseItems.addExercise(this.exercise);
        logger.log(Level.FINE, "Exercise is successfully added");
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.exercise));
    }
}
