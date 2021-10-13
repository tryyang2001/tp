package seedu.duke.commands;

import seedu.duke.food.Food;
import seedu.duke.ui.Ui;

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

    private Food food;

    public AddFoodCommand(String description, int calories) {
        this.food = new Food(description, calories);
    }

    @Override
    public CommandResult execute() {
        if (this.food.getCalories() < 0) {
            return new CommandResult(MESSAGE_INVALID_FOOD_CALORIES);
        }
        super.foodItems.addFood(this.food);
        assert foodItems.getSize() > 0 : "The size of the food list should at least larger than 0";
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.food));
    }
}
