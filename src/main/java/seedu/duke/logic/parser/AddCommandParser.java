package seedu.duke.logic.parser;

import seedu.duke.logic.commands.AddExerciseBankCommand;
import seedu.duke.logic.commands.AddExerciseCommand;
import seedu.duke.logic.commands.AddFoodBankCommand;
import seedu.duke.logic.commands.AddFoodCommand;
import seedu.duke.logic.commands.AddFutureExerciseCommand;
import seedu.duke.logic.commands.AddRecurringExerciseCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandMessages;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.logic.parser.exceptions.ParamInvalidException;
import seedu.duke.logic.parser.exceptions.ParamMissingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses input arguments for Add commands.
 */
public class AddCommandParser implements Parser {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected static final Logger logger = Logger.getLogger(AddCommandParser.class.getName());

    @Override
    public Command parse(String params) {
        try {
            final String itemTypePrefix = ParserUtils.extractItemTypePrefix(params);
            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE:
                //Fallthrough
            case Command.COMMAND_PREFIX_FOOD:
                //Fallthrough
            case Command.COMMAND_PREFIX_RECURRING:
                return parseAddToItems(params, itemTypePrefix);
            case Command.COMMAND_PREFIX_EXERCISE_BANK:
                //Fallthrough
            case Command.COMMAND_PREFIX_FOOD_BANK:
                return parseAddToBank(params, itemTypePrefix);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(CommandMessages.MESSAGE_ADD_COMMAND_INVALID_FORMAT);
        }
    }

    protected Command parseAddToItems(String params, String itemTypePrefix) throws ItemNotSpecifiedException {
        try {
            final String description = ParserUtils.extractItemDescription(params, itemTypePrefix);
            Integer calories = null;
            calories = ParserUtils.extractItemCalories(params, false);
            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE:
                final LocalDate date = ParserUtils.extractDate(params, false);
                logger.log(Level.WARNING, String.format("date detected is: %s", date));
                if (ParserUtils.hasExtraDelimiters(params, AddExerciseCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                } else if (ParserUtils.isSevenDaysBeforeToday(date)) {
                    return new InvalidCommand(String.format(ParserMessages.MESSAGE_ERROR_ITEM_DATE_TOO_OLD,
                            LocalDate.now().minusDays(7).format(DATE_FORMAT),
                            LocalDate.now().format(DATE_FORMAT)));
                }
                if (ParserUtils.isFutureDate(date)) {
                    logger.log(Level.WARNING, String.format("adding to future list"));
                    return new AddFutureExerciseCommand(description, calories, date);
                }
                return new AddExerciseCommand(description, calories, date);
            case Command.COMMAND_PREFIX_FOOD:
                final LocalDateTime dateTime = ParserUtils.extractDateTime(params);
                logger.log(Level.WARNING, String.format("dateTime detected is: %s", dateTime));
                if (ParserUtils.hasExtraDelimiters(params, AddFoodCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                } else if (ParserUtils.isWithinSevenDaysFromToday(dateTime.toLocalDate())) {
                    return new InvalidCommand(String.format(ParserMessages.MESSAGE_ERROR_ITEM_DATE_TOO_OLD,
                            LocalDate.now().minusDays(7).format(DATE_FORMAT),
                            LocalDate.now().format(DATE_FORMAT)));
                }
                return new AddFoodCommand(description, calories, dateTime);
            case Command.COMMAND_PREFIX_RECURRING:
                final LocalDate startDate = ParserUtils.extractStartDate(params);
                final LocalDate endDate = ParserUtils.extractEndDate(params);
                final ArrayList<Integer> dayOfTheWeek = ParserUtils.extractDayOfTheWeek(params);
                if (ParserUtils.hasExtraDelimiters(params, AddRecurringExerciseCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new AddRecurringExerciseCommand(description, calories,
                        startDate, endDate, dayOfTheWeek);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected Command parseAddToBank(String params, String itemTypePrefix) throws ItemNotSpecifiedException {
        try {
            final String description = ParserUtils.extractItemDescription(params, itemTypePrefix);
            final int calories = ParserUtils.extractItemCalories(params, true);

            switch (itemTypePrefix) {
            case Command.COMMAND_PREFIX_EXERCISE_BANK:
                if (ParserUtils.hasExtraDelimiters(params, AddExerciseBankCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new AddExerciseBankCommand(description, calories);
            case Command.COMMAND_PREFIX_FOOD_BANK:
                if (ParserUtils.hasExtraDelimiters(params, AddFoodBankCommand.EXPECTED_PREFIXES)) {
                    return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new AddFoodBankCommand(description, calories);
            default:
                throw new ItemNotSpecifiedException();
            }
        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }


}
