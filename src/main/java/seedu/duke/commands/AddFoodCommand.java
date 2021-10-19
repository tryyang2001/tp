package seedu.duke.commands;

import seedu.duke.item.food.Food;
import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, adds a Food item to the FoodList.
 */
public class AddFoodCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_ADD
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + "food name"
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories" + Ui.QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to add a food item? Use this format:"
            + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "A food item has been added:"
            + Ui.INDENTED_LS + "%s";
    public static final String MESSAGE_INVALID_FOOD_CALORIES = "Food calories cannot be less than 0" + Ui.LS
            + "Try a positive value instead";

    private Logger logger = Logger.getLogger(AddFoodCommand.class.getName());
    private Food food;

    public AddFoodCommand(String description, int calories) {
        this.food = new Food(description, calories);
    }

    @Override
    public CommandResult execute() {
        if (this.food.getCalories() < 0) {
            logger.log(Level.WARNING, "Detected negative food calorie");
            return new CommandResult(MESSAGE_INVALID_FOOD_CALORIES);
        }
        super.foodItems.addItem(this.food);
        assert foodItems.getSize() > 0 : "The size of the food list should at least larger than 0";
        logger.log(Level.FINE, "New food item has been added to the food list");
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.food));
    }
}
