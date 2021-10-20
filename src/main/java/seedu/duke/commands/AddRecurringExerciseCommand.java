package seedu.duke.commands;

import seedu.duke.item.bank.exceptions.ItemNotFoundInBankException;
import seedu.duke.item.exercise.Exercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represent the command that when executed, adds all recurring Exercise items to the FutureExerciseList.
 */
public class AddRecurringExerciseCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_ADD
            + " " + COMMAND_PREFIX_RECURRING + COMMAND_PREFIX_DELIMITER + "exercise name"
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories"
            + " " + COMMAND_PREFIX_START_DATE + COMMAND_PREFIX_DELIMITER + "start date"
            + " " + COMMAND_PREFIX_END_DATE + COMMAND_PREFIX_DELIMITER + "end date"
            + " " + COMMAND_PREFIX_DAY_OF_THE_WEEK + COMMAND_PREFIX_DELIMITER + "days of the week (Mon:1 - Sun:7)"
            + QUOTATION;
    public static final String MESSAGE_INVALID_DATES = "Your start date %s is later than your end date %s";
    public static final String MESSAGE_NO_EXERCISE_ADDED = "Day(s) not present between %s and %s";
    public static final String MESSAGE_INVALID_EXERCISE_CALORIES = "Exercise calories cannot be less than or equal to 0"
            + LS + "Try a positive value instead";
    public static final String MESSAGE_SUCCESS = "Recurring exercise item for the future has been added";
    private static final int ONE_WEEK = 7;
    private static final int ONE_DAY = 1;
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_RECURRING,
            COMMAND_PREFIX_CALORIES,
            COMMAND_PREFIX_START_DATE,
            COMMAND_PREFIX_END_DATE,
            COMMAND_PREFIX_DAY_OF_THE_WEEK
    };


    private final String description;
    private int calories;
    private boolean isCaloriesFromBank;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ArrayList<Integer> dayOfTheWeek;

    private static Logger logger = Logger.getLogger(AddRecurringExerciseCommand.class.getName());

    public AddRecurringExerciseCommand(String description, int calories, LocalDate startDate,
                                       LocalDate endDate, ArrayList<Integer> dayOfTheWeek,
                                       boolean isCaloriesFromBank) {
        this.description = description;
        this.calories = calories;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayOfTheWeek = dayOfTheWeek;
        this.isCaloriesFromBank = isCaloriesFromBank;
    }

    /**
     * Adds all recurring exercises between two dates into the FutureExerciseList.
     */
    private void addRecurringExercises() {
        for (Integer day : dayOfTheWeek) {
            int dayOfTheWeek = startDate.getDayOfWeek().getValue();
            LocalDate currentDate = startDate;
            while (currentDate.isBefore(this.endDate) || currentDate.isEqual(this.endDate)) {
                if (dayOfTheWeek == day) {
                    super.futureExerciseItems.addFutureExercise(new Exercise(description, calories, currentDate));
                    currentDate = currentDate.plusDays(ONE_WEEK);
                    dayOfTheWeek = currentDate.getDayOfWeek().getValue();
                } else {
                    currentDate = currentDate.plusDays(ONE_DAY);
                    dayOfTheWeek = currentDate.getDayOfWeek().getValue();
                }
            }
        }
    }

    @Override
    public CommandResult execute() {
        if (this.startDate.isAfter(this.endDate)) {
            logger.log(Level.WARNING, "Start date is after end date");
            return new CommandResult(String.format(MESSAGE_INVALID_DATES, this.startDate, this.endDate));
        }

        if (isCaloriesFromBank) {
            try {
                this.calories = super.exerciseBank.getCaloriesOfItemWithMatchingName(this.description);
            } catch (ItemNotFoundInBankException e) {
                return new CommandResult(String.format(MESSAGE_INVALID_EXERCISE_NOT_IN_BANK, this.description));
            }
        } else {
            if (this.calories <= 0) {
                logger.log(Level.WARNING, "Exercise calorie is invalid");
                return new CommandResult(MESSAGE_INVALID_EXERCISE_CALORIES);
            }
        }
        assert this.calories > 0 : "Exercise calorie is valid";

        int numberOfFutureExercises = futureExerciseItems.getSize();
        addRecurringExercises();
        if (futureExerciseItems.getSize() == numberOfFutureExercises) {
            return new CommandResult(String.format(MESSAGE_NO_EXERCISE_ADDED, this.startDate, this.endDate));
        }

        logger.log(Level.FINE, "Recurring Exercise is successfully added");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}



