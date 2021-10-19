package seedu.duke.commands;

import seedu.duke.item.ItemNotFoundInBankException;
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
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories"
            + " " + COMMAND_PREFIX_DATE + COMMAND_PREFIX_DELIMITER + "date" + QUOTATION;
    public static final String MESSAGE_SUCCESS = "An exercise item has been added:"
            + INDENTED_LS + "%s";
    public static final String MESSAGE_INVALID_EXERCISE_CALORIES = "Exercise calories cannot be less than or equal to 0"
            + LS + "Try a positive value instead";
    public static final String MESSAGE_INVALID_EXERCISE_NOT_IN_BANK = "%s was not found in the exercise bank! "
            + "Please specify the calories for this item.";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_EXERCISE,
            COMMAND_PREFIX_CALORIES,
            COMMAND_PREFIX_DATE
    };


    private static Logger logger = Logger.getLogger(AddExerciseCommand.class.getName());

    private String description;
    private int calories;
    private LocalDate date;
    private boolean isCaloriesFromBank;

    public AddExerciseCommand(String description, int calories, LocalDate date, boolean isCaloriesFromBank) {
        this.description = description;
        this.calories = calories;
        this.date = date;
        this.isCaloriesFromBank = isCaloriesFromBank;
    }

    @Override
    public CommandResult execute() {
        final Exercise exercise;

        if (isCaloriesFromBank) {
            try {
                this.calories = super.exerciseBank.getCaloriesOfItemWithMatchingName(this.description);
                exercise = new Exercise(this.description, this.calories, this.date);
            } catch (ItemNotFoundInBankException e) {
                return new CommandResult(String.format(MESSAGE_INVALID_EXERCISE_NOT_IN_BANK, this.description));
            }
        } else {
            if (this.calories <= 0) {
                logger.log(Level.WARNING, "Exercise calorie is invalid");
                return new CommandResult(MESSAGE_INVALID_EXERCISE_CALORIES);
            }
            exercise = new Exercise(this.description, this.calories, this.date);
        }

        assert exercise.getCalories() > 0 : "Exercise calorie is valid";
        super.exerciseItems.addExercise(exercise);
        logger.log(Level.FINE, "Exercise is successfully added");
        return new CommandResult(String.format(MESSAGE_SUCCESS, exercise));
    }
}
