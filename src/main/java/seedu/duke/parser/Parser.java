package seedu.duke.parser;

import seedu.duke.commands.AddExerciseBankCommand;
import seedu.duke.commands.AddExerciseCommand;
import seedu.duke.commands.AddFoodBankCommand;
import seedu.duke.commands.AddFoodCommand;
import seedu.duke.commands.AddFutureExerciseCommand;
import seedu.duke.commands.AddRecurringExerciseCommand;
import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.CalculateBmiCommand;
import seedu.duke.commands.CalculateBmiWithProfileCommand;
import seedu.duke.commands.ChangeHeightCommand;
import seedu.duke.commands.ChangeNameCommand;
import seedu.duke.commands.ChangeWeightCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteExerciseBankCommand;
import seedu.duke.commands.DeleteExerciseCommand;
import seedu.duke.commands.DeleteFoodBankCommand;
import seedu.duke.commands.DeleteFoodCommand;
import seedu.duke.commands.DeleteFutureExerciseCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.OverviewCommand;
import seedu.duke.commands.ProfileCommand;
import seedu.duke.commands.ProfileUpdateCommand;
import seedu.duke.commands.SetGoalCommand;
import seedu.duke.commands.ViewCommand;
import seedu.duke.commands.ViewExerciseBankCommand;
import seedu.duke.commands.ViewExerciseListCommand;
import seedu.duke.commands.ViewFoodBankCommand;
import seedu.duke.commands.ViewFoodListCommand;
import seedu.duke.commands.ViewFutureExerciseListCommand;
import seedu.duke.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.parser.exceptions.ParamInvalidException;
import seedu.duke.parser.exceptions.ParamMissingException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses user input to determine which command to execute.
 */
public class Parser {
    protected static final String EMPTY = "";
    protected static final String LS = System.lineSeparator();
    protected static final String QUOTATION = "\"";
    protected static final String MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST = "Fitbot is unable to understand this command! "
            + LS + "Lost? Try typing " + HelpCommand.MESSAGE_COMMAND_FORMAT + " to see the list of commands!";
    protected static final String MESSAGE_ERROR_NO_DESCRIPTION = "Please input a description for this item!";
    protected static final String MESSAGE_ERROR_NO_NAME = "Please input your name!";
    protected static final String MESSAGE_ERROR_NOT_A_NUMBER = "Please input %s as a number!";
    protected static final String MESSAGE_ERROR_NO_CALORIES_INFO = "Please input the number of calories!";
    protected static final String MESSAGE_ERROR_INVALID_CALORIES_INFO = "Please input calories as a number! E.g 123";
    protected static final String MESSAGE_ERROR_NO_ITEM_NUM = "Please input the item number!";
    protected static final String MESSAGE_ERROR_INVALID_ITEM_NUM = "Please input the item number as a number! E.g 1";
    protected static final String MESSAGE_ERROR_TOO_MANY_DELIMITERS = "Please do not use the character "
            + QUOTATION + Command.COMMAND_PREFIX_DELIMITER + QUOTATION
            + " in your input other than to specify parameters!";
    protected static final String FILE_TEXT_DELIMITER = "|";
    protected static final String MESSAGE_ERROR_ILLEGAL_CHARACTER = "Please do not use the character "
            + QUOTATION + FILE_TEXT_DELIMITER + QUOTATION
            + " in your input!";
    protected static final String MESSAGE_ERROR_INVALID_FORMAT = "Invalid format for this command! "
            + "Please follow one of the formats:";
    protected static final String DATE_TIME_FORMAT = "dd-MM-yyyy HHmm";
    protected static final String DATE_FORMAT = "dd-MM-yyyy";
    protected static final String TIME_FORMAT = "HHmm";
    protected static final String MESSAGE_ERROR_INVALID_DATE_FORMAT = "Invalid date format! Please input date as "
            + DATE_FORMAT;
    protected static final String MESSAGE_ERROR_INVALID_TIME_FORMAT = "Invalid time format! Please input time as "
            + TIME_FORMAT;
    protected static final String MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK = "Invalid day format! Please input day(s) "
            + "between 1 and 7."
            + LS + "1 : Monday"
            + LS + "2 : Tuesday"
            + LS + "3 : Wednesday"
            + LS + "4 : Thursday"
            + LS + "5 : Friday"
            + LS + "6 : Saturday"
            + LS + "7 : Sunday";
    protected static final String MESSAGE_ERROR_NO_DATE = "Please input the date for this item!";
    protected static final String MESSAGE_ERROR_NO_DAY_OF_THE_WEEK = "Please input the day of reoccurrence "
            + "for this item!";
    protected static final String MESSAGE_ERROR_REPEATED_DAY_OF_THE_WEEK = "Please check your input of the day "
            + "of reoccurrence and make sure that there is no repeated day";
    protected static final int MONDAY = 1;
    protected static final int SUNDAY = 7;

    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Returns the correct command to be executed depending on user input.
     * Command words are case-insensitive.
     *
     * @param input Raw user input string
     * @return Command class representing the correct command to be executed
     */
    public Command parseCommand(String input) {

        if (hasTextFileDelimiter(input)) {
            return new InvalidCommand(MESSAGE_ERROR_ILLEGAL_CHARACTER);
        }

        final String[] commandAndParams = splitInputIntoCommandAndParams(input);
        final String commandWord = commandAndParams[0].toLowerCase(); //case-insensitive (all lower case)
        final String params = commandAndParams[1];

        switch (commandWord) {
        case Command.COMMAND_WORD_ADD:
            return parseAdd(params);
        case Command.COMMAND_WORD_DELETE:
            return parseDelete(params);
        case Command.COMMAND_WORD_VIEW:
            return parseViewItems(params);
        case Command.COMMAND_WORD_BMI:
            return parseBmi(params);
        case Command.COMMAND_WORD_PROFILE:
            return parseProfile(params);
        case ChangeNameCommand.COMMAND_WORD:
            return new ChangeNameCommand(params);
        case ChangeHeightCommand.COMMAND_WORD:
            return parseChangeHeight(params);
        case ChangeWeightCommand.COMMAND_WORD:
            return parseChangeWeight(params);
        case SetGoalCommand.COMMAND_WORD:
            return parseSetGoal(params);
        case OverviewCommand.COMMAND_WORD:
            return new OverviewCommand();
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            return new InvalidCommand(MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST);
        }
    }

    private Command parseAdd(String params) {
        try {
            final String itemTypePrefix = extractItemTypePrefix(params);
            if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE)
                    || itemTypePrefix.equals(Command.COMMAND_PREFIX_UPCOMING_EXERCISE)) {
                return parseAddToItems(params, itemTypePrefix);
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_RECURRING)) {
                return parseAddToItems(params, itemTypePrefix);
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD)) {
                return parseAddToItems(params, itemTypePrefix);
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE_BANK)) {
                return parseAddToBank(params, itemTypePrefix);
            } else {
                assert itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD_BANK) :
                        "at this point, it must be food bank";
                return parseAddToBank(params, itemTypePrefix);
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(
                    correctCommandFormatSuggestions(
                            AddExerciseCommand.MESSAGE_COMMAND_FORMAT,
                            AddFoodCommand.MESSAGE_COMMAND_FORMAT,
                            AddExerciseBankCommand.MESSAGE_COMMAND_FORMAT,
                            AddFoodBankCommand.MESSAGE_COMMAND_FORMAT));
        }
    }

    private Command parseAddToItems(String params, String itemTypePrefix) {
        try {
            final String description = extractItemDescription(params, itemTypePrefix);
            int calories = Command.NULL_CALORIES;
            boolean isCaloriesFromBank = false;
            try {
                calories = extractItemCalories(params);
            } catch (ParamMissingException e) {
                // If calories is not specified, set checkFromBank boolean to true so that the
                // Command object can try to retrieve from item bank later.
                isCaloriesFromBank = true;
                logger.log(Level.INFO, "No calories detected, look at item bank");
            }

            if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE)
                    || itemTypePrefix.equals(Command.COMMAND_PREFIX_UPCOMING_EXERCISE)) {
                final LocalDate date = extractDate(params, false);
                logger.log(Level.INFO, String.format("date detected is: %s", date));
                if (hasExtraDelimiters(params,
                        getNumberOfCorrectParamsDetected(params,
                                AddExerciseCommand.EXPECTED_PREFIXES))) {
                    return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                if (isFutureDate(date)) {
                    logger.log(Level.INFO, String.format("adding to future list"));
                    return new AddFutureExerciseCommand(description, calories, date);
                } else {
                    return new AddExerciseCommand(description, calories, date, isCaloriesFromBank);
                }
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_RECURRING)) {
                final LocalDate startDate = extractStartDate(params);
                final LocalDate endDate = extractEndDate(params);
                final ArrayList<Integer> dayOfTheWeek = extractDayOfTheWeek(params);
                return new AddRecurringExerciseCommand(description, calories, startDate, endDate, dayOfTheWeek);
            } else {
                final LocalDateTime dateTime = extractDateTime(params);
                logger.log(Level.INFO, String.format("dateTime detected is: %s", dateTime));
                assert itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD) :
                        "at this point, it must be food";
                if (hasExtraDelimiters(params,
                        getNumberOfCorrectParamsDetected(params,
                                AddFoodCommand.EXPECTED_PREFIXES))) {
                    return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new AddFoodCommand(description, calories, dateTime, isCaloriesFromBank);
            }
        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command parseAddToBank(String params, String itemTypePrefix) {
        try {
            final String description = extractItemDescription(params, itemTypePrefix);
            final int calories = extractItemCalories(params);
            if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE_BANK)) {
                if (hasExtraDelimiters(params,
                        getNumberOfCorrectParamsDetected(params,
                                AddExerciseBankCommand.EXPECTED_PREFIXES))) {
                    return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new AddExerciseBankCommand(description, calories);
            } else {
                assert itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD_BANK) :
                        "at this point, it must be food bank";
                if (hasExtraDelimiters(params,
                        getNumberOfCorrectParamsDetected(params,
                                AddFoodBankCommand.EXPECTED_PREFIXES))) {
                    return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new AddFoodBankCommand(description, calories);
            }
        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }


    private Command parseDelete(String params) {
        try {
            final String itemTypePrefix = extractItemTypePrefix(params);
            final String description = extractItemDescription(params, itemTypePrefix).split(" ")[0].trim();
            boolean isClear = description.equalsIgnoreCase(Command.COMMAND_WORD_DELETE_ALL);

            if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE)) {
                if (isClear) {
                    return new DeleteExerciseCommand(true);
                } else {
                    return parseDeleteFromItems(params, itemTypePrefix);
                }
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD)) {
                if (isClear) {
                    return new DeleteFoodCommand(true);
                } else {
                    return parseDeleteFromItems(params, itemTypePrefix);
                }
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_UPCOMING_EXERCISE)) {
                if (isClear) {
                    return new DeleteFutureExerciseCommand(true);
                } else {
                    return parseDeleteFromFutureOrBank(params, itemTypePrefix);
                }
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE_BANK)) {
                if (isClear) {
                    return new DeleteExerciseBankCommand(true);
                } else {
                    return parseDeleteFromFutureOrBank(params, itemTypePrefix);
                }
            } else {
                assert itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD_BANK) :
                        "at this point, it must be food bank";
                if (isClear) {
                    return new DeleteFoodBankCommand(true);
                } else {
                    return parseDeleteFromFutureOrBank(params, itemTypePrefix);
                }
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(
                    correctCommandFormatSuggestions(
                            DeleteExerciseCommand.MESSAGE_COMMAND_FORMAT,
                            DeleteFoodCommand.MESSAGE_COMMAND_FORMAT,
                            DeleteExerciseBankCommand.MESSAGE_COMMAND_FORMAT,
                            DeleteFoodBankCommand.MESSAGE_COMMAND_FORMAT));
        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command parseDeleteFromItems(String params, String itemTypePrefix) {
        try {
            final int itemIndex = extractItemIndex(params, itemTypePrefix);
            final LocalDate date = extractDate(params, true);
            logger.log(Level.INFO, String.format("date detected is: %s", date));
            if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE)) {
                if (hasExtraDelimiters(params,
                        getNumberOfCorrectParamsDetected(params,
                                DeleteExerciseCommand.EXPECTED_PREFIXES))) {
                    return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                logger.log(Level.INFO, String.format("deleting exercise item %s from %s", itemIndex, date));
                return new DeleteExerciseCommand(itemIndex, date);
            } else {
                assert itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD) :
                        "at this point, it must be food";
                if (hasExtraDelimiters(params,
                        getNumberOfCorrectParamsDetected(params,
                                AddFoodCommand.EXPECTED_PREFIXES))) {
                    return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                logger.log(Level.INFO, String.format("deleting food item %s from %s", itemIndex, date));
                return new DeleteFoodCommand(itemIndex, date);
            }

        } catch (ParamInvalidException | ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command parseDeleteFromFutureOrBank(String params, String itemTypePrefix) {
        try {
            final int itemIndex = extractItemIndex(params, itemTypePrefix);
            if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE_BANK)) {
                if (hasExtraDelimiters(params,
                        getNumberOfCorrectParamsDetected(params,
                                DeleteExerciseBankCommand.EXPECTED_PREFIXES))) {
                    return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new DeleteExerciseBankCommand(itemIndex);
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_UPCOMING_EXERCISE)) {
                if (hasExtraDelimiters(params,
                        getNumberOfCorrectParamsDetected(params,
                                DeleteFutureExerciseCommand.EXPECTED_PREFIXES))) {
                    return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new DeleteFutureExerciseCommand(itemIndex);
            } else {
                assert itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD_BANK) :
                        "at this point, it must be food bank";
                if (hasExtraDelimiters(params,
                        getNumberOfCorrectParamsDetected(params,
                                DeleteFoodBankCommand.EXPECTED_PREFIXES))) {
                    return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
                }
                return new DeleteFoodBankCommand(itemIndex);
            }
        } catch (ParamInvalidException e) {
            return new InvalidCommand(e.getMessage());
        } catch (ParamMissingException e) {
            return new InvalidCommand(e.getMessage());
        }

    }

    private Command parseViewItems(String params) {
        if (params.equals(EMPTY)) {
            return new ViewCommand();
        }
        try {
            final String itemTypePrefix = extractItemTypePrefix(params);
            if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE)) {
                return new ViewExerciseListCommand();
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD)) {
                return new ViewFoodListCommand();
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_UPCOMING_EXERCISE)) {
                return new ViewFutureExerciseListCommand();
            } else if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE_BANK)) {
                return new ViewExerciseBankCommand();
            } else {
                assert itemTypePrefix.equals(Command.COMMAND_PREFIX_FOOD_BANK) :
                        "at this point, it must be food bank";
                return new ViewFoodBankCommand();
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(
                    correctCommandFormatSuggestions(
                            ViewExerciseListCommand.MESSAGE_COMMAND_FORMAT,
                            ViewFoodListCommand.MESSAGE_COMMAND_FORMAT,
                            ViewExerciseBankCommand.MESSAGE_COMMAND_FORMAT,
                            ViewFoodBankCommand.MESSAGE_COMMAND_FORMAT));
        }
    }

    private Command parseBmi(String params) {
        if (params.equals(EMPTY)) { //no additional parameters, assumed to be bmi from current profile
            return new CalculateBmiWithProfileCommand();
        }
        if (hasExtraDelimiters(params, Command.COMMAND_BMI_EXPECTED_NUM_DELIMITERS)) {
            return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
        }
        if (hasRequiredParams(params, CalculateBmiCommand.EXPECTED_PREFIXES)) {
            try {
                final double height = extractHeight(params);
                final double weight = extractWeight(params);
                return new CalculateBmiCommand(height, weight);
            } catch (ParamInvalidException e) {
                return new InvalidCommand(e.getMessage());
            }
        } else {
            logger.log(Level.WARNING, "Detected invalid input parameters for BMI calculation.");
            return new InvalidCommand(CalculateBmiCommand.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    private Command parseProfile(String params) {
        if (params.isEmpty()) { //no additional parameters, assumed to be view profile command
            return new ProfileCommand();
        }

        //TODO: Test profile again after storage has been updated

        if (hasExtraDelimiters(
                params,
                getNumberOfCorrectParamsDetected(params,
                        ProfileUpdateCommand.EXPECTED_PREFIXES))) {
            return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
        }

        try {
            final String name = extractProfileName(params);
            final double height = extractHeight(params);
            final double weight = extractWeight(params);
            final int calorieGoal = extractCalorieGoal(params);
            final int age = extractAge(params);
            final int activityFactor = extractActivityFactor(params);
            final char gender = extractGender(params);
            return new ProfileUpdateCommand(name, height, weight, calorieGoal, age, activityFactor, gender);
        } catch (ParamInvalidException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command parseChangeHeight(String params) {
        try {
            final double height = Double.parseDouble(params);
            return new ChangeHeightCommand(height);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit height input.");
            return new InvalidCommand(String.format(MESSAGE_ERROR_NOT_A_NUMBER, "height"));
        }
    }

    private Command parseChangeWeight(String params) {
        try {
            final double weight = Double.parseDouble(params);
            return new ChangeWeightCommand(weight);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit weight input.");
            return new InvalidCommand(String.format(MESSAGE_ERROR_NOT_A_NUMBER, "weight"));
        }
    }

    private Command parseSetGoal(String params) {
        try {
            final int goal = Integer.parseInt(params);
            return new SetGoalCommand(goal);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-integer calorie goal input.");
            return new InvalidCommand(String.format(MESSAGE_ERROR_NOT_A_NUMBER, "goal"));
        }
    }

    /**
     * Returns a String array where 0th index is command string and 1st index is the remaining parameters.
     * Command string and parameter string is assumed to be separated by the first " " in input.
     * If no parameters are provided in the input, 1st index will be set to EMPTY.
     *
     * @param input Raw user input string
     * @return String array [command, parameters]
     */
    private String[] splitInputIntoCommandAndParams(String input) {
        String[] commandAndParams = new String[2];
        final String[] inputSplit = input.trim().split(" ", 2);
        //command string
        commandAndParams[0] = inputSplit[0];
        //param string; if not given, set to EMPTY for error handling
        commandAndParams[1] = (inputSplit.length == 2) ? inputSplit[1].trim() : EMPTY;
        return commandAndParams;
    }

    private boolean hasRequiredParams(String params, String... prefixes) {
        for (String prefix : prefixes) {
            if (!params.toLowerCase().contains(prefix + Command.COMMAND_PREFIX_DELIMITER)) {
                return false;
            }
        }
        return true;
    }

    private String extractItemTypePrefix(String params) throws ItemNotSpecifiedException {
        boolean isExercise =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_EXERCISE
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isFood =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_FOOD
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isUpcomingExercise =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_UPCOMING_EXERCISE
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isExerciseBank =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_EXERCISE_BANK
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isFoodBank =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_FOOD_BANK
                        + Command.COMMAND_PREFIX_DELIMITER);
        boolean isRecurringExercise =
                params.toLowerCase().contains(Command.COMMAND_PREFIX_RECURRING
                        + Command.COMMAND_PREFIX_DELIMITER);

        boolean isItemSpecified = checkIsItemSpecified(isExercise, isFood, isUpcomingExercise,
                isExerciseBank, isFoodBank, isRecurringExercise);
        if (!isItemSpecified) {
            logger.log(Level.WARNING, "Detected none or more than one item");
            throw new ItemNotSpecifiedException();
        } else if (isExercise) {
            return Command.COMMAND_PREFIX_EXERCISE;
        } else if (isFood) {
            return Command.COMMAND_PREFIX_FOOD;
        } else if (isUpcomingExercise) {
            return Command.COMMAND_PREFIX_UPCOMING_EXERCISE;
        } else if (isExerciseBank) {
            return Command.COMMAND_PREFIX_EXERCISE_BANK;
        } else if (isRecurringExercise) {
            return Command.COMMAND_PREFIX_RECURRING;
        } else {
            assert isFoodBank : "Must be food bank if all above conditions are not satisfied";
            return Command.COMMAND_PREFIX_FOOD_BANK;
        }
    }

    private boolean checkIsItemSpecified(boolean... paramBool) {
        int numberOfParams = 0;
        for (boolean isParam : paramBool) {
            if (isParam) {
                numberOfParams += 1;
            }
        }
        return numberOfParams == 1 ? true : false; //item must be exactly 1
    }

    /**
     * Extracts only the parameter required so that any additional parameter
     * specified behind this string (if any) is removed.
     * E.g. "John Doe w/20" is returned as "John Doe".
     * NOTE: This is why users are not allowed to include the character "/" in their inputs
     * other than to specify a parameter.
     */
    private String extractRelevantParameter(String params) {
        if (params.contains(Command.COMMAND_PREFIX_DELIMITER)) {
            return params.substring(0, params.indexOf(Command.COMMAND_PREFIX_DELIMITER) - 1).trim();
        } else {
            return params;
        }
    }

    private String extractItemDescription(String params, String prefix)
            throws ParamInvalidException, ParamMissingException {
        try {
            String stringAfterPrefix = params.split(prefix + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String description = extractRelevantParameter(stringAfterPrefix);
            if (description.equals(EMPTY)) {
                logger.log(Level.WARNING, "Detected empty description");
                throw new ParamInvalidException(MESSAGE_ERROR_NO_DESCRIPTION);
            }
            return description;
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, String.format("Detected missing command prefix (%s)", prefix));
            throw new ParamMissingException(MESSAGE_ERROR_NO_DESCRIPTION);
        }
    }

    private int extractItemIndex(String params, String prefix) throws ParamInvalidException, ParamMissingException {
        try {
            final String itemNumString = extractItemDescription(params, prefix).split(" ")[0].trim();
            return convertItemNumToItemIndex(Integer.parseInt(itemNumString));
        } catch (ParamInvalidException | ParamMissingException e) {
            throw new ParamMissingException(MESSAGE_ERROR_NO_ITEM_NUM);
        } catch (NumberFormatException e) {
            throw new ParamInvalidException(MESSAGE_ERROR_INVALID_ITEM_NUM);
        }
    }

    private int extractItemCalories(String params)
            throws ParamInvalidException, ParamMissingException {
        try {
            if (params.contains(Command.COMMAND_PREFIX_CALORIES + Command.COMMAND_PREFIX_DELIMITER)) {
                String stringAfterPrefix =
                        params.split(Command.COMMAND_PREFIX_CALORIES
                                + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
                String caloriesString = stringAfterPrefix.split(" ", 2)[0];
                return Integer.parseInt(caloriesString);
            } else {
                logger.log(Level.WARNING, "Detected missing calories prefix");
                throw new ParamMissingException(MESSAGE_ERROR_NO_CALORIES_INFO);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit calories input");
            throw new ParamInvalidException(MESSAGE_ERROR_INVALID_CALORIES_INFO);
        }
    }

    private String extractProfileName(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_NAME
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String name = extractRelevantParameter(stringAfterPrefix).trim();
            if (name.equals(EMPTY)) {
                logger.log(Level.WARNING, "Detected empty name input.");
                throw new ParamInvalidException(MESSAGE_ERROR_NO_NAME);
            }
            return name;
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected missing name prefix, returning empty string.");
            return Command.NULL_STRING;
        }
    }

    private double extractHeight(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_HEIGHT
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String doubleString = stringAfterPrefix.split(" ", 2)[0];
            return Double.parseDouble(doubleString);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit height input.");
            throw new ParamInvalidException(String.format(MESSAGE_ERROR_NOT_A_NUMBER, "height"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected missing height prefix, return 0 height");
            return Command.NULL_DOUBLE;
        }
    }

    private double extractWeight(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_WEIGHT
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String doubleString = stringAfterPrefix.split(" ", 2)[0];
            return Double.parseDouble(doubleString);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit weight input.");
            throw new ParamInvalidException(String.format(MESSAGE_ERROR_NOT_A_NUMBER, "weight"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected missing weight prefix, return 0 weight");
            return Command.NULL_DOUBLE;
        }
    }

    private int extractCalorieGoal(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_GOAL
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String intString = stringAfterPrefix.split(" ", 2)[0];
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit goal input.");
            throw new ParamInvalidException(String.format(MESSAGE_ERROR_NOT_A_NUMBER, "goal"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected missing goal prefix, return 0 goal");
            return Command.NULL_INT;
        }
    }

    private int extractAge(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_AGE
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String intString = stringAfterPrefix.split(" ", 2)[0];
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit age input.");
            throw new ParamInvalidException(String.format(MESSAGE_ERROR_NOT_A_NUMBER, "age"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected missing age prefix, return 0 age");
            return Command.NULL_INT;
        }
    }

    private int extractActivityFactor(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_ACTIVITY_FACTOR
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String intString = stringAfterPrefix.split(" ", 2)[0];
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit activity factor input.");
            throw new ParamInvalidException(String.format(MESSAGE_ERROR_NOT_A_NUMBER, "activity factor"));
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected missing activity factor prefix, return 0 activity factor");
            return Command.NULL_INT;
        }
    }

    private char extractGender(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_GENDER
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            return stringAfterPrefix.charAt(0);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected missing gender prefix, return null gender");
            return Command.NULL_CHAR;
        }
    }


    private LocalDate extractDate(String params, boolean isRequired)
            throws ParamInvalidException, ParamMissingException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_DATE
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String dateString = extractRelevantParameter(stringAfterPrefix);
            logger.log(Level.INFO, String.format("date string detected is: %s", dateString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(dateString, formatter);
        } catch (IndexOutOfBoundsException e) {
            if (isRequired) {
                logger.log(Level.WARNING, "Detected empty date input after prefix but date is required!");
                throw new ParamMissingException(MESSAGE_ERROR_NO_DATE);
            } else {
                logger.log(Level.INFO, "Detected empty date input after prefix, assuming date to be now");
                return LocalDate.now();
            }
        } catch (DateTimeParseException e) {
            throw new ParamInvalidException(MESSAGE_ERROR_INVALID_DATE_FORMAT);
        }
    }

    private LocalDate extractStartDate(String params)
            throws ParamInvalidException, ParamMissingException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_START_DATE
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String dateString = extractRelevantParameter(stringAfterPrefix);
            logger.log(Level.INFO, String.format("date string detected is: %s", dateString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(dateString, formatter);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected empty start date input after prefix but date is required!");
            throw new ParamMissingException(MESSAGE_ERROR_NO_DATE);
        } catch (DateTimeParseException e) {
            throw new ParamInvalidException(MESSAGE_ERROR_INVALID_DATE_FORMAT);
        }
    }

    private LocalDate extractEndDate(String params)
            throws ParamInvalidException, ParamMissingException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_END_DATE
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String dateString = extractRelevantParameter(stringAfterPrefix);
            logger.log(Level.INFO, String.format("date string detected is: %s", dateString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(dateString, formatter);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected empty end date input after prefix but date is required!");
            throw new ParamMissingException(MESSAGE_ERROR_NO_DATE);
        } catch (DateTimeParseException e) {
            throw new ParamInvalidException(MESSAGE_ERROR_INVALID_DATE_FORMAT);
        }
    }

    private LocalTime extractTime(String params) throws ParamInvalidException {
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_TIME
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String timeString = extractRelevantParameter(stringAfterPrefix);
            logger.log(Level.INFO, String.format("time string detected is: %s", timeString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
            return LocalTime.parse(timeString, formatter);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.INFO, "Detected empty time input after prefix, assuming time to be now");
            return LocalTime.now();
        } catch (DateTimeParseException e) {
            throw new ParamInvalidException(MESSAGE_ERROR_INVALID_TIME_FORMAT);
        }
    }

    private LocalDateTime extractDateTime(String params) throws ParamInvalidException, ParamMissingException {
        final LocalTime time = extractTime(params);
        final LocalDate date = extractDate(params, false);
        return date.atTime(time);
    }

    private ArrayList<Integer> extractDayOfTheWeek(String params)
            throws ParamMissingException, ParamInvalidException {
        ArrayList<Integer> dayOfTheWeek = new ArrayList<>();
        try {
            String stringAfterPrefix =
                    params.split(Command.COMMAND_PREFIX_DAY_OF_THE_WEEK
                            + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            for (int i = 0; i < stringAfterPrefix.length(); i++) {
                int day = Integer.parseInt(String.valueOf(stringAfterPrefix.charAt(i)));
                if (day > MONDAY && day < SUNDAY) { //between monday and sunday
                    if (dayOfTheWeek.contains(day)) {
                        throw new ParamInvalidException(MESSAGE_ERROR_REPEATED_DAY_OF_THE_WEEK);
                    }
                    dayOfTheWeek.add(day);
                } else {
                    throw new ParamInvalidException(MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Detected empty day input after prefix but day is required!");
            throw new ParamMissingException(MESSAGE_ERROR_NO_DAY_OF_THE_WEEK);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit calories input");
            throw new ParamInvalidException(MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK);
        }
        return dayOfTheWeek;
    }

    private boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    private int convertItemNumToItemIndex(int itemNum) {
        return itemNum - 1;
    }


    private int getNumberOfCorrectParamsDetected(String params, String... prefixes) {
        int count = 0;
        for (String prefix : prefixes) {
            if (params.toLowerCase().contains(prefix + Command.COMMAND_PREFIX_DELIMITER)) {
                count++;
            }
        }
        logger.log(Level.INFO, String.format("no. of corrected params detected: %s", count));
        return count;
    }

    private boolean hasExtraDelimiters(String params, int expectedNum) {
        int numOfDelimiters = 0;
        for (int i = 0; i < params.length(); i++) {
            if (params.charAt(i) == Command.COMMAND_PREFIX_DELIMITER.charAt(0)) {
                numOfDelimiters++;
            }
        }
        return numOfDelimiters > expectedNum;
    }

    private boolean hasTextFileDelimiter(String input) {
        return input.contains(FILE_TEXT_DELIMITER);
    }

    private String correctCommandFormatSuggestions(String... suggestions) {
        String formattedSuggestions = MESSAGE_ERROR_INVALID_FORMAT + LS;
        int i = 1;
        for (String suggestion : suggestions) {
            formattedSuggestions += i + ". " + suggestion + LS;
            i += 1;
        }
        return formattedSuggestions.stripTrailing();
    }
}
