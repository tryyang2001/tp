package seedu.duke.commands;

import seedu.duke.item.food.Food;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes a Food item from the FoodList.
 */
public class DeleteFoodCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + "X" + QUOTATION
            + ", where X is the item number in the food list";
    public static final String MESSAGE_SUCCESS = "A food item has been deleted:"
            + INDENTED_LS + "%1$s"
            + INDENTED_LS + "Number of food item(s) left: %2$d";
    public static final String MESSAGE_FOOD_CLEAR = "All food items have been removed.";

    private final int itemIndex;
    private boolean isClear = false;

    private static final Logger logger = Logger.getLogger(DeleteFoodCommand.class.getName());

    public DeleteFoodCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public DeleteFoodCommand(boolean isClear) {
        this.itemIndex = -1;
        this.isClear = isClear;
    }

    @Override
    public CommandResult execute() {
        if (this.isClear) {
            logger.log(Level.FINE, "Clearing food list");
            super.foodItems.clearFoodList();
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
            deletedFood = super.foodItems.deleteFood(this.itemIndex);
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
