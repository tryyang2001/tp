package seedu.duke.logic.commands;

import seedu.duke.data.item.food.Food;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes a Food item from the FoodList.
 */
public class DeleteFoodCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + "INDEX " + COMMAND_PREFIX_DATE
            + COMMAND_PREFIX_DELIMITER + "DATE_IN_DD-MM-YYYY " + COMMAND_PREFIX_TIME + COMMAND_PREFIX_DELIMITER
            + "TIME_IN_HHmm" + QUOTATION + ", where INDEX is the item number in the food list";
    public static final String MESSAGE_SUCCESS = "A food item has been deleted:"
            + INDENTED_LS + "%1$s"
            + INDENTED_LS + "Number of food item(s) left: %2$d";
    public static final String MESSAGE_FOOD_CLEAR = "All food items have been removed.";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_FOOD,
            COMMAND_PREFIX_DATE,
            COMMAND_PREFIX_TIME,
    };


    private final int itemIndex;
    private final LocalDate date;
    private final LocalTime time;
    private boolean isClear = false;

    private static final Logger logger = Logger.getLogger(DeleteFoodCommand.class.getName());

    public DeleteFoodCommand(int itemIndex, LocalDate date, LocalTime time) {
        this.itemIndex = itemIndex;
        this.date = date;
        this.time = time;
    }

    public DeleteFoodCommand(boolean isClear) {
        this.itemIndex = -1;
        this.isClear = isClear;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    @Override
    public CommandResult execute() {
        if (this.isClear) {
            logger.log(Level.FINE, "Clearing food list");
            super.foodItems.clearList();
            assert foodItems.getSize() == 0 : "The size of the food list should be 0 after clear";
            return new CommandResult(MESSAGE_FOOD_CLEAR);
        }
        assert this.itemIndex > 0 : "Deleting an object only";
        if (super.foodItems.getSize() == 0) {
            logger.log(Level.WARNING, "Food list is empty.");
            return new CommandResult(MESSAGE_EMPTY_FOOD_LIST);
        }
        logger.log(Level.FINE, "Trying to delete item now");
        try {
            Food deletedFood;
            deletedFood = super.foodItems.deleteItem(this.itemIndex, this.date, this.time);
            return new CommandResult(String.format(MESSAGE_SUCCESS, deletedFood, super.foodItems.getSize()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.FINE, "Detected invalid food item index.");
            if (super.foodItems.getSize() == 1) {
                return new CommandResult(MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(MESSAGE_LIST_OUT_OF_BOUNDS, super.foodItems.getSize()));
        }
    }
}