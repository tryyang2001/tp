package seedu.duke.commands;

import seedu.duke.item.exercise.Exercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represent the command that when executed, adds all recurring Exercise items to the FutureExerciseList.
 */
public class AddRecurringExerciseCommand extends Command {
    //TODO: finalise on command format
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_ADD
            + " " + COMMAND_PREFIX_RECURRING + COMMAND_PREFIX_DELIMITER + "exercise name"
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories";
    public static final String MESSAGE_INVALID_DATES = "Your start date %s is later than your end date %s";
    public static final String MESSAGE_INVALID_START_DATE = "Your start date %s has already passed. "
            + "Make sure that your start and end dates are in the future";
    public static final String MESSAGE_NO_EXERCISE_ADDED = "Day(s) not present between %s and %s";
    public static final String MESSAGE_INVALID_EXERCISE_CALORIES = "Exercise calories cannot be less than or equal to 0"
            + LS + "Try a positive value instead";
    public static final String MESSAGE_SUCCESS = "Recurring exercise item for the future has been added";



    private final String description;
    private final int calories;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ArrayList<Integer> dayOfTheWeek;

    private static Logger logger = Logger.getLogger(AddRecurringExerciseCommand.class.getName());

    public AddRecurringExerciseCommand(String description, int calories, LocalDate startDate,
                                       LocalDate endDate, ArrayList<Integer> dayOfTheWeek) {
        this.description = description;
        this.calories = calories;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    @Override
    public CommandResult execute() {
        if (this.startDate.isAfter(this.endDate)) {
            logger.log(Level.WARNING, "Start date is after end date");
            return new CommandResult(String.format(MESSAGE_INVALID_DATES, this.startDate, this.endDate));
        }
        assert this.endDate.isAfter(this.startDate) : "End date is after start date";
        if (this.startDate.isBefore(LocalDate.now())) {
            logger.log(Level.WARNING, "Reoccurring exercises are for future only");
            return new CommandResult(String.format(MESSAGE_INVALID_START_DATE, this.startDate));
        }
        if (this.calories <= 0) {
            logger.log(Level.WARNING, "Exercise calorie is invalid");
            return new CommandResult(MESSAGE_INVALID_EXERCISE_CALORIES);
        }
        assert this.calories > 0 : "Exercise calorie is valid";

        int numberOfFutureExercises = futureExerciseItems.getSize();
        futureExerciseItems.addRecurringExercises(this.description, this.calories,
                this.startDate, this.endDate, this.dayOfTheWeek);
        if (futureExerciseItems.getSize() == numberOfFutureExercises) {
            return new CommandResult(String.format(MESSAGE_NO_EXERCISE_ADDED, this.startDate, this.endDate));
        }

        logger.log(Level.FINE, "Recurring Exercise is successfully added");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}



