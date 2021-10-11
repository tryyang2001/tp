package seedu.duke.commands;

/**
 * Represents the command that when executed, adds a Food item to the FoodList.
 */
public class AddFoodCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_ADD
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + "food name"
            + " " + COMMAND_PREFIX_CALORIES + COMMAND_PREFIX_DELIMITER + "calories" + QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to add a food item? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "A food item has been added:" + LS + "%s";

    //private Food food;
    private final String description;
    private final int calories;

    public AddFoodCommand(String description, int calories) {
        //this.food = new Food(description, calories);
        this.description = description;
        this.calories = calories;
    }

    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return CommandResult with error message if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, description) + " Calories: " + calories);
    }
}
