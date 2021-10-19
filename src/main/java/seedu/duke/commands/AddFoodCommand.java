package seedu.duke.commands;

import seedu.duke.item.ItemNotFoundInBankException;
import seedu.duke.item.food.Food;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, adds a Food item to the FoodList.
 */
public class AddFoodCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_ADD
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + "food name"
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories"
            + " " + COMMAND_PREFIX_DATE + COMMAND_PREFIX_DELIMITER + "date"
            + " " + COMMAND_PREFIX_TIME + COMMAND_PREFIX_TIME + "time" + QUOTATION;
    public static final String MESSAGE_SUCCESS = "A food item has been added:"
            + INDENTED_LS + "%s";
    public static final String MESSAGE_INVALID_FOOD_CALORIES = "Food calories cannot be less than 0" + LS
            + "Try a positive value instead";
    public static final String MESSAGE_INVALID_FOOD_NOT_IN_BANK = "%s was not found in the food bank! "
            + "Please specify the calories for this item.";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_FOOD,
            COMMAND_PREFIX_CALORIES,
            COMMAND_PREFIX_DATE,
            COMMAND_PREFIX_TIME};

    private Logger logger = Logger.getLogger(AddFoodCommand.class.getName());
    private String description;
    private int calories;
    private LocalDateTime dateTime;
    private boolean isCaloriesFromBank;

    public AddFoodCommand(String description, int calories, LocalDateTime dateTime, boolean isCaloriesFromBank) {
        this.description = description;
        this.calories = calories;
        this.dateTime = dateTime;
        this.isCaloriesFromBank = isCaloriesFromBank;
    }

    @Override
    public CommandResult execute() {
        final Food food;

        if (isCaloriesFromBank) {
            try {
                this.calories = super.foodBank.getCaloriesOfItemWithMatchingName(this.description);
                food = new Food(this.description, this.calories, this.dateTime);
            } catch (ItemNotFoundInBankException e) {
                return new CommandResult(String.format(MESSAGE_INVALID_FOOD_NOT_IN_BANK, this.description));
            }
        } else {
            if (this.calories < 0) {
                logger.log(Level.WARNING, "Detected negative food calorie");
                return new CommandResult(MESSAGE_INVALID_FOOD_CALORIES);
            }
            food = new Food(this.description, this.calories, this.dateTime);
        }

        super.foodItems.addFood(food);
        assert foodItems.getSize() > 0 : "The size of the food list should at least larger than 0";
        logger.log(Level.FINE, "New food item has been added to the food list");
        return new CommandResult(String.format(MESSAGE_SUCCESS, food));

    }
}

