package seedu.duke.parser;

import seedu.duke.commands.AddExerciseCommand;
import seedu.duke.commands.AddFoodCommand;
import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.CalculateBmiCommand;
import seedu.duke.commands.CalculateBmiWithProfileCommand;
import seedu.duke.commands.ChangeHeightCommand;
import seedu.duke.commands.ChangeNameCommand;
import seedu.duke.commands.ChangeWeightCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ProfileCommand;
import seedu.duke.commands.ProfileCreateCommand;
import seedu.duke.commands.DeleteExerciseCommand;
import seedu.duke.commands.DeleteFoodCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.OverviewCommand;
import seedu.duke.commands.SetGoalCommand;
import seedu.duke.commands.ViewCommand;
import seedu.duke.commands.ViewExerciseListCommand;
import seedu.duke.commands.ViewFoodListCommand;
import seedu.duke.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.parser.exceptions.ParamInvalidException;
import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses user input to determine which command to execute.
 */
public class Parser {
    protected static final String EMPTY = "";
    protected static final String MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST = "Fitbot is unable to understand this command! "
            + Ui.LS + "Lost? Try typing " + HelpCommand.MESSAGE_COMMAND_FORMAT + " to see the list of commands!";
    protected static final String MESSAGE_ERROR_NO_DESCRIPTION = "Please input a description for this item!";
    protected static final String MESSAGE_ERROR_NO_NAME = "Please input your name!";
    protected static final String MESSAGE_ERROR_NO_HEIGHT = "Please input height as a number!";
    protected static final String MESSAGE_ERROR_NO_WEIGHT = "Please input weight as a number!";
    protected static final String MESSAGE_ERROR_NO_CALORIES_INFO = "Please input the number of calories!";
    protected static final String MESSAGE_ERROR_INVALID_CALORIES_INFO = "Please input calories as a number! E.g 123";
    protected static final String MESSAGE_ERROR_NO_ITEM_NUM = "Please input the item number!";
    protected static final String MESSAGE_ERROR_INVALID_ITEM_NUM = "Please input the item number as a number! E.g 1";
    protected static final String MESSAGE_ERROR_NOT_A_NUMBER = "Please input a number!";
    protected static final String MESSAGE_ERROR_TOO_MANY_DELIMITERS = "Please do not use the character "
            + Ui.QUOTATION + Command.COMMAND_PREFIX_DELIMITER + Ui.QUOTATION
            + " in your input other than to specify parameters!";
    protected static final String FILE_TEXT_DELIMITER = "|";
    protected static final String MESSAGE_ERROR_ILLEGAL_CHARACTER = "Please do not use the character "
            + Ui.QUOTATION + FILE_TEXT_DELIMITER + Ui.QUOTATION
            + " in your input!";
    public static final int PARAMS_ALL_INDICES = 0;

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
            return parseAddItems(params);
        case Command.COMMAND_WORD_DELETE:
            return parseDeleteItems(params);
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

    private Command parseAddItems(String params) {
        if (hasExtraDelimiters(params, Command.COMMAND_ADD_EXPECTED_NUM_DELIMITERS)) {
            return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
        }

        try {
            final String itemTypePrefix = extractItemTypePrefix(params);
            final String description = extractItemDescription(params, itemTypePrefix);
            final int calories = extractItemCalories(params);
            if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE)) {
                return new AddExerciseCommand(description, calories);
            } else {
                return new AddFoodCommand(description, calories);
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(String.format(Command.MESSAGE_ERROR_ITEM_NOT_SPECIFIED,
                    AddFoodCommand.MESSAGE_COMMAND_FORMAT,
                    AddExerciseCommand.MESSAGE_COMMAND_FORMAT));
        } catch (ParamInvalidException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command parseDeleteItems(String params) {
        try {
            final String itemTypePrefix = extractItemTypePrefix(params);
            final String description = extractItemDescription(params, itemTypePrefix).split(" ")[0];
            final int itemIndex;
            boolean isClear = description.trim().equalsIgnoreCase(Command.COMMAND_WORD_DELETE_ALL);
            if (itemTypePrefix.equals(Command.COMMAND_PREFIX_EXERCISE)) {
                if (isClear) {
                    return new DeleteExerciseCommand(PARAMS_ALL_INDICES);
                }
                itemIndex = convertItemNumToItemIndex(Integer.parseInt(description.trim()));
                return new DeleteExerciseCommand(itemIndex);
            } else {
                if (isClear) {
                    return new DeleteFoodCommand(PARAMS_ALL_INDICES);
                }
                itemIndex = convertItemNumToItemIndex(Integer.parseInt(description.trim()));
                return new DeleteFoodCommand(itemIndex);
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(String.format(Command.MESSAGE_ERROR_ITEM_NOT_SPECIFIED,
                    DeleteFoodCommand.MESSAGE_COMMAND_FORMAT,
                    DeleteExerciseCommand.MESSAGE_COMMAND_FORMAT));
        } catch (ParamInvalidException e) {
            return new InvalidCommand(MESSAGE_ERROR_NO_ITEM_NUM);
        } catch (NumberFormatException e) {
            return new InvalidCommand(MESSAGE_ERROR_INVALID_ITEM_NUM);
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
            } else {
                return new ViewFoodListCommand();
            }
        } catch (ItemNotSpecifiedException e) {
            return new InvalidCommand(String.format(Command.MESSAGE_ERROR_ITEM_NOT_SPECIFIED,
                    ViewFoodListCommand.MESSAGE_COMMAND_FORMAT,
                    ViewExerciseListCommand.MESSAGE_COMMAND_FORMAT));
        }
    }

    private Command parseBmi(String params) {
        if (params.equals(EMPTY)) { //no additional parameters, assumed to be bmi from current profile
            return new CalculateBmiWithProfileCommand();
        }
        if (hasExtraDelimiters(params, Command.COMMAND_BMI_EXPECTED_NUM_DELIMITERS)) {
            return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
        }
        if (!hasRequiredParams(params,
                Command.COMMAND_PREFIX_HEIGHT,
                Command.COMMAND_PREFIX_WEIGHT)) {
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
        if (hasExtraDelimiters(params, ProfileCreateCommand.COMMAND_EXPECTED_NUM_DELIMITERS)) {
            return new InvalidCommand(MESSAGE_ERROR_TOO_MANY_DELIMITERS);
        }
        if (!hasRequiredParams(params,
                Command.COMMAND_PREFIX_NAME,
                Command.COMMAND_PREFIX_HEIGHT,
                Command.COMMAND_PREFIX_WEIGHT)) {
            return new InvalidCommand(ProfileCreateCommand.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        try {
            final String name = extractProfileName(params);
            final double height = extractHeight(params);
            final double weight = extractWeight(params);
            return new ProfileCreateCommand(name, height, weight);
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
            return new InvalidCommand(MESSAGE_ERROR_NOT_A_NUMBER);
        }
    }

    private Command parseChangeWeight(String params) {
        try {
            final double weight = Double.parseDouble(params);
            return new ChangeWeightCommand(weight);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-digit weight input.");
            return new InvalidCommand(MESSAGE_ERROR_NOT_A_NUMBER);
        }
    }

    private Command parseCreateProfile(String params) {
        if (!hasRequiredParams(params,
                Command.COMMAND_PREFIX_NAME,
                Command.COMMAND_PREFIX_HEIGHT,
                Command.COMMAND_PREFIX_WEIGHT)) {
            logger.log(Level.WARNING, "Detected insufficient prefix for creating profile.");
            return new InvalidCommand(ProfileCreateCommand.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        try {
            final String name = extractProfileName(params);
            final double height = extractHeight(params);
            final double weight = extractWeight(params);
            return new ProfileCreateCommand(name, height, weight);
        } catch (ParamInvalidException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    private Command parseSetGoal(String params) {
        try {
            final int goal = Integer.parseInt(params);
            return new SetGoalCommand(goal);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Detected non-integer calorie goal input.");
            return new InvalidCommand(MESSAGE_ERROR_NOT_A_NUMBER);
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

        if (isExercise && isFood || !isExercise && !isFood) {
            logger.log(Level.WARNING, "Detected both food and exercise prefix.");
            throw new ItemNotSpecifiedException(); //cannot be both and cannot be neither
        } else if (isExercise) {
            return Command.COMMAND_PREFIX_EXERCISE;
        } else {
            return Command.COMMAND_PREFIX_FOOD;
        }
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

    private String extractItemDescription(String params, String prefix) throws ParamInvalidException {
        try {
            String stringAfterPrefix = params.split(prefix + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            String description = extractRelevantParameter(stringAfterPrefix);
            if (description.equals(EMPTY)) {
                throw new ParamInvalidException(MESSAGE_ERROR_NO_DESCRIPTION);
            }
            return description;
        } catch (IndexOutOfBoundsException e) {
            throw new ParamInvalidException(MESSAGE_ERROR_NO_DESCRIPTION);
        }
    }

    private int extractItemCalories(String params) throws ParamInvalidException {
        try {
            if (params.contains(Command.COMMAND_PREFIX_CALORIES + Command.COMMAND_PREFIX_DELIMITER)) {
                String stringAfterPrefix =
                        params.split(Command.COMMAND_PREFIX_CALORIES
                                + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
                String caloriesString = stringAfterPrefix.split(" ", 2)[0];
                return Integer.parseInt(caloriesString);
            } else {
                throw new ParamInvalidException(MESSAGE_ERROR_NO_CALORIES_INFO);
            }
        } catch (NumberFormatException e) {
            throw new ParamInvalidException(MESSAGE_ERROR_INVALID_CALORIES_INFO);
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
            throw new ParamInvalidException(MESSAGE_ERROR_NO_HEIGHT);
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
            throw new ParamInvalidException(MESSAGE_ERROR_NO_WEIGHT);
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
            logger.log(Level.WARNING, "Detected empty name input after prefix.");
            throw new ParamInvalidException(MESSAGE_ERROR_NO_NAME);
        }
    }

    private int convertItemNumToItemIndex(int itemNum) {
        return itemNum - 1;
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

}
