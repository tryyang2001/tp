package seedu.duke.commands;

/**
 * Represents the command that when executed, deletes a Food item from the FoodList.
 */
public class DeleteFoodCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + "X" + QUOTATION
            + ", where X is the item number in the food list";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to delete a food item?"
            + "Use this format:" + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "A food item has been deleted:" + LS + "%s";

    private final int itemNum;

    public DeleteFoodCommand(int itemNum) {
        this.itemNum = itemNum;
    }

    @Override
    public CommandResult execute() {
        //TODO: Check if list is empty first, print error if it is
        //TODO: Call relevant method, catch exceptions (index out of bounds)
        // and return CommandResult with error message if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.itemNum));

    }
}
