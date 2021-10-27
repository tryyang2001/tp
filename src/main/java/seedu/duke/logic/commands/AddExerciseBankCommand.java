package seedu.duke.logic.commands;

import seedu.duke.data.item.exceptions.DuplicateItemInBankException;
import seedu.duke.data.item.exercise.Exercise;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, adds an Exercise item to the ExerciseBank.
 */
public class AddExerciseBankCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_ADD
            + " " + COMMAND_PREFIX_EXERCISE_BANK + COMMAND_PREFIX_DELIMITER + "exercise name"
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories" + CommandMessages.QUOTATION;
    public static final String MESSAGE_SUCCESS = "An exercise item has been added to the exercise bank:"
            + CommandMessages.INDENTED_LS + "%s";
    public static final String MESSAGE_INVALID_EXERCISE_CALORIES = "Exercise calories cannot be less than or equal to 0"
            + CommandMessages.LS + "Try a positive value instead";
    public static final String MESSAGE_EXERCISE_ALREADY_EXISTS = "The exercise with name "
            + CommandMessages.QUOTATION + "%s" + CommandMessages.QUOTATION + " already exists in the exercise bank! (Names are case insensitive)"
            + CommandMessages.LS + "Try using another name, or delete/edit the existing item first!";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_EXERCISE_BANK,
            COMMAND_PREFIX_CALORIES
    };

    private static Logger logger = Logger.getLogger(AddExerciseBankCommand.class.getName());

    private Exercise exercise;

    public AddExerciseBankCommand(String description, int calories) {
        this.exercise = new Exercise(description, calories);
    }

    @Override
    public CommandResult execute() {
        if (exercise.getCalories() <= 0) {
            logger.log(Level.WARNING, "Exercise calorie is invalid");
            return new CommandResult(MESSAGE_INVALID_EXERCISE_CALORIES);
        }
        assert exercise.getCalories() > 0 : "Exercise calorie is valid";
        try {
            super.exerciseBank.addItem(this.exercise);
            logger.log(Level.FINE, "Exercise is successfully added to exercise bank");
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.exercise));
        } catch (DuplicateItemInBankException e) {
            return new CommandResult(String.format(MESSAGE_EXERCISE_ALREADY_EXISTS, this.exercise.getName()));
        }
    }
}
