package seedu.duke.commands;

import seedu.duke.food.Food;
import seedu.duke.parser.Parser;
import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes a Food item from the FoodList.
 */
public class DeleteFoodCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + "X" + Ui.QUOTATION
            + ", where X is the item number in the food list";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to delete a food item?"
            + Ui.INDENTED_LS + "Use this format:" + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "A food item has been deleted:"
            + Ui.INDENTED_LS + "%1$s"
            + Ui.INDENTED_LS + "Number of food item(s) left: %2$d";
    public static final String MESSAGE_FOOD_CLEAR = "All food items have been removed.";

    private final int itemIndex;

    private static final Logger logger = Logger.getLogger(DeleteFoodCommand.class.getName());

    public DeleteFoodCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public CommandResult execute() {
        if (super.foodItems.getSize() == 0) {
            return new CommandResult(MESSAGE_EMPTY_FOOD_LIST);
        }
        if (this.itemIndex == Parser.PARAMS_ALL_INDICES) {
            super.foodItems.clearFoodList();
            assert foodItems.getSize() == 0 : "The size of the food list should be 0 after clear";
            return new CommandResult(MESSAGE_FOOD_CLEAR);
        }
        try {
            Food deletedFood;
            deletedFood = super.foodItems.deleteFood(this.itemIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, deletedFood, super.foodItems.getSize()));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.INFO, "Detected invalid food item index");
            if (super.foodItems.getSize() == 1) {
                return new CommandResult(MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(MESSAGE_LIST_OUT_OF_BOUNDS, super.foodItems.getSize()));
        }
    }
}
