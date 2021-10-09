package seedu.duke.commands;

/**
 * Represents the command that when executed, lists all the items in the FoodList.
 */
public class ViewFoodListCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_VIEW
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to view the food list? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "You have consumed %1$d food items:" + LS + "%2$s";


    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return InvalidCommand if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, 0, "list of food items (placeholder)"));
    }
}
