package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, lists all the items in the FoodList.
 */
public class ViewFoodListCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_VIEW
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + Ui.QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to view the food list? Use this format:"
            + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "You have consumed %1$d food item(s):"
            + Ui.LS + "%2$s"
            + Ui.LS + "Total calories consumed: %3$s";
    public static final String MESSAGE_HELP = "view f/ -- Shows all food in food record." + Ui.INDENTED_LS
            + Ui.FORMAT_HEADER + MESSAGE_COMMAND_FORMAT + Ui.LS;

    @Override
    public CommandResult execute() {
        if (super.foodItems.getSize() == 0) {
            return new CommandResult(MESSAGE_EMPTY_FOOD_LIST);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.foodItems.getSize(),
                super.foodItems.convertToString(),
                super.foodItems.getTotalCalories()));
    }
}
