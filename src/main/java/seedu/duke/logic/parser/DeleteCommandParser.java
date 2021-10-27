package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.DeleteExerciseBankCommand;
import seedu.duke.logic.commands.DeleteExerciseCommand;
import seedu.duke.logic.commands.DeleteFoodBankCommand;
import seedu.duke.logic.commands.DeleteFoodCommand;
import seedu.duke.logic.commands.DeleteFutureExerciseCommand;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.logic.parser.exceptions.ParamInvalidException;
import seedu.duke.logic.parser.exceptions.ParamMissingException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses input arguments for Delete commands.
 */
public class DeleteCommandParser implements Parser {

    protected static final Logger logger = Logger.getLogger(DeleteCommandParser.class.getName());

    @Override
    public Command parse(String params) {
        try {
            final String itemTypePrefix = ParserUtils.extractItemTypePrefix(params);
            final String description = ParserUtils.extractItemDescription(params, itemTypePrefix).split(" ")[0].trim();
            boolean isClear = description.equalsIgnoreCase(Command.COMMAND_WORD_DELETE_ALL);

            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE:
                return isClear ? new DeleteExerciseCommand(true)
                        : parseDeleteFromItems(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_FOOD:
                return isClear ? new DeleteFoodCommand(true)
                        : parseDeleteFromItems(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_UPCOMING_EXERCISE:
                return isClear ? new DeleteFutureExerciseCommand(true)
                        : parseDeleteFromFutureOrBank(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_EXERCISE_BANK:
                return isClear ? new DeleteExerciseBankCommand(true)
                        : parseDeleteFromFutureOrBank(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_FOOD_BANK:
                return isClear ? new DeleteFoodBankCommand(true)
                        : parseDeleteFromFutureOrBank(params, itemTypePrefix);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(
                    ParserUtils.correctCommandFormatSuggestions(
                            DeleteExerciseCommand.MESSAGE_COMMAND_FORMAT,
                            DeleteFoodCommand.MESSAGE_COMMAND_FORMAT,
                            DeleteExerciseBankCommand.MESSAGE_COMMAND_FORMAT,
                            DeleteFoodBankCommand.MESSAGE_COMMAND_FORMAT,
                            DeleteFutureExerciseCommand.MESSAGE_COMMAND_FORMAT));
        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected Command parseDeleteFromItems(String params, String itemTypePrefix) throws ItemNotSpecifiedException {
        try {
            final int itemIndex = ParserUtils.extractItemIndex(params, itemTypePrefix);
            final LocalDate date = ParserUtils.extractDate(params, true);
            logger.log(Level.FINE, String.format("date detected is: %s", date));

            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE:
                if (ParserUtils.hasExtraDelimiters(params, DeleteExerciseCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                logger.log(Level.FINE, String.format("deleting exercise item %s from %s", itemIndex, date));
                return new DeleteExerciseCommand(itemIndex, date);
            case Command.COMMAND_PREFIX_FOOD:
                if (ParserUtils.hasExtraDelimiters(params, DeleteFoodCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                final LocalTime time = ParserUtils.extractTime(params, true);
                logger.log(Level.FINE, String.format("deleting food item %s from %s %s", itemIndex, date, time));
                return new DeleteFoodCommand(itemIndex, date, time);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected Command parseDeleteFromFutureOrBank(String params, String itemTypePrefix)
            throws ItemNotSpecifiedException {
        try {
            final int itemIndex = ParserUtils.extractItemIndex(params, itemTypePrefix);
            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE_BANK:
                if (ParserUtils.hasExtraDelimiters(params, DeleteExerciseBankCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new DeleteExerciseBankCommand(itemIndex);
            case Command.COMMAND_PREFIX_FOOD_BANK:
                if (ParserUtils.hasExtraDelimiters(params, DeleteFoodBankCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new DeleteFoodBankCommand(itemIndex);
            case Command.COMMAND_PREFIX_UPCOMING_EXERCISE:
                if (ParserUtils.hasExtraDelimiters(params, DeleteFutureExerciseCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new DeleteFutureExerciseCommand(itemIndex);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}

