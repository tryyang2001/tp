package seedu.duke.logic.commands;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the command that when executed, deletes a Food item from the FoodBank.
 */
public class DeleteFoodBankCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_FOOD_BANK + COMMAND_PREFIX_DELIMITER + "X" + CommandMessages.QUOTATION
            + ", where X is the item number in the food bank list";
    public static final String MESSAGE_SUCCESS = "A food item has been deleted from the food bank:"
            + CommandMessages.INDENTED_LS + "%s"
            + CommandMessages.LS + "Number of food item(s) left in the food bank: %2$d";
    private static final String MESSAGE_FOOD_CLEAR = "All food items in the food bank have been removed.";
    public static final String[] EXPECTED_PREFIXES = {COMMAND_PREFIX_FOOD_BANK};
    private static final String MESSAGE_REMOVED_MULTIPLE_FOOD_BANK_ITEM =
            "All of the requested food bank items have been deleted.";


    private static Logger logger = Logger.getLogger(DeleteFoodBankCommand.class.getName());


    private boolean isClear = false;
    private ArrayList<Integer> itemIndexArray;


    public DeleteFoodBankCommand(boolean isClear) {
        this.isClear = isClear;
    }

    public DeleteFoodBankCommand(ArrayList<Integer> itemIndexArray) {
        this.itemIndexArray = itemIndexArray;
    }

    @Override
    public CommandResult execute() {
        if (super.foodBank.getSize() == 0) {
            logger.log(Level.WARNING, "food bank is empty.");
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_FOOD_BANK);
        }
        if (this.isClear) {
            logger.log(Level.WARNING, "Clearing food bank");
            super.foodBank.clearList();
            return new CommandResult(MESSAGE_FOOD_CLEAR);
        }

        logger.log(Level.WARNING, "Trying to delete item now");
        try {
            String listOfDeletedFoodBank = super.foodBank.deleteMultipleItems(this.itemIndexArray);
            return new CommandResult(listOfDeletedFoodBank + MESSAGE_REMOVED_MULTIPLE_FOOD_BANK_ITEM);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected invalid food item index.");
            if (super.foodBank.getSize() == 1) {
                return new CommandResult(CommandMessages.MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(
                    CommandMessages.MESSAGE_LIST_OUT_OF_BOUNDS, super.foodBank.getSize()));
        }
    }
}
