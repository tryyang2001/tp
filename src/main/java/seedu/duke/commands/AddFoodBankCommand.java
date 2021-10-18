package seedu.duke.commands;


import seedu.duke.item.food.Food;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, adds a Food item to the FoodBank.
 */
public class AddFoodBankCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_ADD
            + " " + COMMAND_PREFIX_FOOD_BANK + COMMAND_PREFIX_DELIMITER + "exercise name"
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories" + QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to add a food item to your food bank? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "A food item has been added to the food bank:"
            + INDENTED_LS + "%s";
    public static final String MESSAGE_INVALID_FOOD_CALORIES = "Food calories cannot be less than 0"
            + LS + "Try a positive value instead";

    private static Logger logger = Logger.getLogger(AddFoodBankCommand.class.getName());

    private Food food;

    public AddFoodBankCommand(String description, int calories) {
        this.food = new Food(description, calories);
    }

    @Override
    public CommandResult execute() {
        if (food.getCalories() < 0) {
            logger.log(Level.WARNING, "Food calorie is invalid");
            return new CommandResult(MESSAGE_INVALID_FOOD_CALORIES);
        }
        assert food.getCalories() >= 0 : "Food calorie is valid";
        super.foodBank.addItem(this.food);
        logger.log(Level.FINE, "Food is successfully added to food bank");
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.food));
    }
}
