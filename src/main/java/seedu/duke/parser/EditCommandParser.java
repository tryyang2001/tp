package seedu.duke.parser;

import seedu.duke.commands.AddExerciseBankCommand;
import seedu.duke.commands.AddExerciseCommand;
import seedu.duke.commands.AddFoodBankCommand;
import seedu.duke.commands.AddFoodCommand;
import seedu.duke.commands.AddRecurringExerciseCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteExerciseBankCommand;
import seedu.duke.commands.DeleteExerciseCommand;
import seedu.duke.commands.DeleteFoodBankCommand;
import seedu.duke.commands.DeleteFoodCommand;
import seedu.duke.commands.DeleteFutureExerciseCommand;
import seedu.duke.commands.EditExerciseBankCommand;
import seedu.duke.commands.EditFoodBankCommand;
import seedu.duke.commands.EditFutureExerciseCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.parser.exceptions.ParamInvalidException;
import seedu.duke.parser.exceptions.ParamMissingException;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses input arguments for Edit commands.
 */
public class EditCommandParser implements Parser {

    protected static final Logger logger = Logger.getLogger(EditCommandParser.class.getName());

    @Override
    public Command parse(String params) {
        try {
            final String itemTypePrefix = ParserUtils.extractItemTypePrefix(params);
            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE_BANK:
                //Fallthrough
            case Command.COMMAND_PREFIX_FOOD_BANK:
                return parseEditBank(params, itemTypePrefix);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(
                    ParserUtils.correctCommandFormatSuggestions(
                            EditExerciseBankCommand.MESSAGE_COMMAND_FORMAT,
                            EditFoodBankCommand.MESSAGE_COMMAND_FORMAT,
                            EditFutureExerciseCommand.MESSAGE_COMMAND_FORMAT));
        }
    }

    protected Command parseEditBank(String params, String itemTypePrefix)
            throws ItemNotSpecifiedException {
        try {
            final int itemIndex = ParserUtils.extractItemIndex(params, itemTypePrefix);
            final String description = ParserUtils.extractItemDescription(params, Command.COMMAND_PREFIX_EDIT_NAME);
            final int calories = ParserUtils.extractItemCalories(params);
            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE_BANK:
                //if (ParserUtils.hasExtraDelimiters(params, DeleteExerciseBankCommand.EXPECTED_PREFIXES)) {
                //    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                //}
                return new EditExerciseBankCommand(itemIndex, description, calories);
            case Command.COMMAND_PREFIX_FOOD_BANK:
                //if (ParserUtils.hasExtraDelimiters(params, DeleteFoodBankCommand.EXPECTED_PREFIXES)) {
                //    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                //}
                return new EditFoodBankCommand(itemIndex, description, calories);
            case Command.COMMAND_PREFIX_UPCOMING_EXERCISE:
                //if (ParserUtils.hasExtraDelimiters(params, DeleteFutureExerciseCommand.EXPECTED_PREFIXES)) {
                //  return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                //}
                return new EditFutureExerciseCommand(itemIndex, description, calories);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
