package seedu.duke.logic.commands;


import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditFutureExerciseCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_EDIT
            + " " + COMMAND_PREFIX_UPCOMING_EXERCISE + COMMAND_PREFIX_DELIMITER + "W "
            + COMMAND_PREFIX_NAME  + COMMAND_PREFIX_DELIMITER + "X "
            + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_DATE + COMMAND_PREFIX_DELIMITER + "Z" + CommandMessages.QUOTATION
            + ", where W is the item number in the future exercise list, X is the new name,"
            + "Y is the new calories, Z is the new date";
    public static final String MESSAGE_SUCCESS = "Exercise item number %d has been changed to:"
            + CommandMessages.INDENTED_LS + "%s";
    public static final String MESSAGE_INVALID_DATE = "The new date must be after today's date!";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_EXERCISE_BANK,
            COMMAND_PREFIX_NAME,
            COMMAND_PREFIX_CALORIES,
            COMMAND_PREFIX_DATE,
    };

    private static Logger logger = Logger.getLogger(EditFutureExerciseCommand.class.getName());

    private final int itemIndex;
    private final String newName;
    private final int newCalories;
    private final LocalDate newDate;

    public EditFutureExerciseCommand(int itemIndex, String newName, int newCalories, LocalDate newDate) {
        this.itemIndex = itemIndex;
        this.newName = newName;
        this.newCalories = newCalories;
        this.newDate = newDate;
    }

    @Override
    public CommandResult execute() {
        if (!this.newDate.isAfter(LocalDate.now())) {
            return new CommandResult(MESSAGE_INVALID_DATE);
        }

        if (super.futureExerciseItems.getSize() == 0) {
            logger.log(Level.WARNING, "Future exercise list is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_FUTURE_EXERCISE_LIST);
        }
        try {
            if (!this.newName.equals(NULL_STRING)) {
                super.futureExerciseItems.getItem(this.itemIndex).setName(this.newName);
            }
            if (this.newCalories != NULL_CALORIES) {
                super.futureExerciseItems.getItem(this.itemIndex).setCalories(this.newCalories);
            }
            if (!this.newName.equals(NULL_DATE)) {
                super.futureExerciseItems.getItem(this.itemIndex).setDate(this.newDate);
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS, this.itemIndex + 1,
                    super.futureExerciseItems.getItem(this.itemIndex).toString()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected invalid exercise item index.");
            if (super.futureExerciseItems.getSize() == 1) {
                return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(
                    CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS, super.futureExerciseItems.getSize()));
        }
    }
}
