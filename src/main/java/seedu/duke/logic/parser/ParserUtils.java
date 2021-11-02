package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.parser.exceptions.ExtraParamException;
import seedu.duke.logic.parser.exceptions.InvalidParamException;
import seedu.duke.logic.parser.exceptions.ItemNotSpecifiedException;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains utility methods used by Parser classes to extract relevant parameters.
 */
public class ParserUtils {
    protected static final Logger logger = Logger.getLogger(ParserUtils.class.getName());

    protected static boolean isSevenDaysBeforeToday(LocalDate date) {
        return date.isBefore(LocalDate.now().minusDays(7));
    }

    protected static boolean hasRequiredParams(String params, String... prefixes) {
        for (String prefix : prefixes) {
            if (!params.toLowerCase().contains(prefix + Command.COMMAND_PREFIX_DELIMITER)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Extracts the item type prefix for command words that are common across item types (add, delete, view).
     * //TODO: Item type must be the first word after the command word. E.g "add f/" is valid, "add potato f/" is not
     *
     * @param params String containing all parameters
     * @return String that is one of the item type prefixes
     * @throws ItemNotSpecifiedException If parameter does not contain any of the expected prefixes,
     *                                   or contains more than one.
     */
    protected static String extractItemTypePrefix(String params) throws ItemNotSpecifiedException {
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

        boolean isItemSpecified = ParserUtils.checkIsItemSpecified(isExercise, isFood, isUpcomingExercise,
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

    protected static boolean checkIsItemSpecified(boolean... paramBool) {
        int numberOfParams = 0;
        for (boolean isParam : paramBool) {
            if (isParam) {
                numberOfParams += 1;
            }
        }
        return numberOfParams == 1 ? true : false; // item must be exactly 1
    }



    /*================== General methods to extract different data types ==================*/

    /**
     * Extracts only the parameter that is specified by the prefix so that any additional parameter
     * specified behind this string (if any) is removed.
     * E.g. "John Doe w/20" is returned as "John Doe".
     * NOTE: This is why users are not allowed to include the character "/" in their inputs
     * other than to specify a parameter.
     *
     * @throws MissingParamException When expected parameter is missing
     */
    protected static String extractRelevantParameter(String params, String prefix)
            throws MissingParamException {
        try {
            String stringAfterPrefix = params.split(prefix + Command.COMMAND_PREFIX_DELIMITER, 2)[1];
            if (stringAfterPrefix.contains(Command.COMMAND_PREFIX_DELIMITER)) {
                return stringAfterPrefix.substring(0,
                        stringAfterPrefix.indexOf(Command.COMMAND_PREFIX_DELIMITER) - 1).trim();
            }
            return stringAfterPrefix.trim();
        } catch (IndexOutOfBoundsException e) {
            throw new MissingParamException();
        }
    }


    /**
     * Extracts only the parameter required so that any additional parameter
     * specified behind this string (if any) is removed.
     * Specific to parameters where only one word is expected, and any word separated by a whitespace in this
     * string is considered to be extra (and unnecessary).
     * E.g. "f/1" returns "1", and "f/1 hello" throws ExtraParamException
     *
     * @throws MissingParamException When expected parameter is missing
     * @throws ExtraParamException   When extra words are detected for the parameter
     */
    protected static String extractRelevantParameterWithoutWhitespace(String params, String prefix)
            throws MissingParamException, ExtraParamException {
        String[] paramsSplitByWhitespace = extractRelevantParameter(params, prefix).split(" ", 2);
        if (paramsSplitByWhitespace.length == 2) {
            throw new ExtraParamException(
                    String.format(ParserMessages.MESSAGE_ERROR_EXTRA_PARAMETERS, paramsSplitByWhitespace[1]));
        }
        return paramsSplitByWhitespace[0];

    }

    protected static LocalDate extractGeneralDate(String params, String prefix)
            throws ParserException {
        try {
            String dateString = extractRelevantParameterWithoutWhitespace(params, prefix);
            logger.log(Level.WARNING, String.format("date string detected is: %s", dateString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParserMessages.DATE_FORMAT);
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_DATE_FORMAT);
        } catch (MissingParamException e) {
            return null;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }

    protected static LocalTime extractGeneralTime(String params, String prefix)
            throws ParserException {
        try {
            String timeString = extractRelevantParameterWithoutWhitespace(params, Command.COMMAND_PREFIX_TIME);
            logger.log(Level.WARNING, String.format("time string detected is: %s", timeString));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParserMessages.TIME_FORMAT);
            return LocalTime.parse(timeString, formatter);
        } catch (DateTimeParseException e) {
            throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_TIME_FORMAT);
        } catch (MissingParamException e) {
            return null;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }


    protected static Integer extractGeneralInteger(String params, String prefix)
            throws InvalidParamException, ParserException {
        try {
            String intString = extractRelevantParameterWithoutWhitespace(params, prefix);
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            throw new InvalidParamException();
        } catch (MissingParamException e) {
            logger.log(Level.WARNING, "No integer given, return null");
            return null;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }

    protected static Double extractGeneralDouble(String params, String prefix)
            throws InvalidParamException, ParserException {
        try {
            String doubleString = extractRelevantParameterWithoutWhitespace(params, prefix);
            return Double.parseDouble(doubleString);
        } catch (NumberFormatException e) {
            throw new InvalidParamException();
        } catch (MissingParamException e) {
            logger.log(Level.WARNING, "No double given but not required, return null");
            return null;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }

    protected static ArrayList<Integer> extractListOfIntegers(String params, String prefix)
            throws MissingParamException, InvalidParamException, ParserException {
        try {
            String numberString = extractRelevantParameter(params, prefix);
            String[] numberStringArray = numberString.split(Command.COMMAND_INDEX_DELIMITER);
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < numberStringArray.length; i++) {
                Integer number = Integer.parseInt(numberStringArray[i].trim());
                if (integers.contains(number)) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_DUPLICATE_NUMBERS);
                }
                integers.add(number);
            }
            return integers;
        } catch (NumberFormatException e) {
            throw new InvalidParamException();
        }
    }


    /*===================== Methods to extract specific parameters ==============================*/
    /*=== All methods here should throw general ParserException, with specific error messages ===*/

    protected static String extractItemDescription(String params, String prefix)
            throws ParserException {
        try {
            String description = extractRelevantParameter(params, prefix);
            logger.log(Level.WARNING, String.format("Item name:%s", description));
            if (description.equals(ParserMessages.EMPTY)) {
                logger.log(Level.WARNING, "Detected empty description");
                throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_DESCRIPTION);
            }
            return description;
        } catch (MissingParamException e) {
            logger.log(Level.WARNING, String.format("Detected missing command prefix (%s)", prefix));
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_DESCRIPTION);
        }
    }


    protected static Integer extractItemCalories(String params, boolean isRequired)
            throws ParserException {
        try {
            Integer calories = extractGeneralInteger(params, Command.COMMAND_PREFIX_CALORIES);
            if (calories == null && isRequired) {
                throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_CALORIES_INFO);
            }
            return calories;
        } catch (InvalidParamException e) {
            logger.log(Level.WARNING, "Detected non-digit calories input");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_CALORIES_INFO);
        }
    }

    protected static String extractName(String params) throws ParserException {
        try {
            String name = extractRelevantParameter(params, Command.COMMAND_PREFIX_NAME);
            if (name.equals(ParserMessages.EMPTY)) {
                logger.log(Level.WARNING, "Detected empty name input.");
                throw new ParserException(ParserMessages.MESSAGE_ERROR_NAME_EMPTY_STRING);
            }
            return name;
        } catch (MissingParamException e) {
            logger.log(Level.WARNING, "Detected missing name prefix, returning null string.");
            return null;
        }
    }

    protected static Double extractHeight(String params) throws ParserException {
        try {
            return extractGeneralDouble(params, Command.COMMAND_PREFIX_HEIGHT);
        } catch (InvalidParamException e) {
            logger.log(Level.WARNING, "Detected non-digit height input.");
            throw new ParserException(String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "height"));
        }
    }

    protected static Double extractWeight(String params) throws ParserException {
        try {
            return extractGeneralDouble(params, Command.COMMAND_PREFIX_WEIGHT);
        } catch (InvalidParamException e) {
            logger.log(Level.WARNING, "Detected non-digit weight input.");
            throw new ParserException(String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "weight"));
        }
    }

    protected static Integer extractCalorieGoal(String params) throws ParserException {
        try {
            return extractGeneralInteger(params, Command.COMMAND_PREFIX_GOAL);
        } catch (InvalidParamException e) {
            logger.log(Level.WARNING, "Detected non-digit goal input.");
            throw new ParserException(String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "goal"));
        }
    }

    protected static Integer extractAge(String params) throws ParserException {
        try {
            return extractGeneralInteger(params, Command.COMMAND_PREFIX_AGE);
        } catch (InvalidParamException e) {
            logger.log(Level.WARNING, "Detected non-digit age input.");
            throw new ParserException(String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "age"));
        }
    }

    protected static Integer extractActivityFactor(String params) throws ParserException {
        try {
            return extractGeneralInteger(params, Command.COMMAND_PREFIX_ACTIVITY_FACTOR);
        } catch (InvalidParamException e) {
            logger.log(Level.WARNING, "Detected non-digit activity factor input.");
            throw new ParserException(String.format(
                    ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "activity factor"));
        }
    }

    protected static Character extractGender(String params) throws ParserException {
        try {
            String stringAfterPrefix = extractRelevantParameterWithoutWhitespace(params, Command.COMMAND_PREFIX_GENDER);
            if (stringAfterPrefix.length() > 1) {
                throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_GENDER);
            }
            return stringAfterPrefix.charAt(0);
        } catch (MissingParamException e) {
            logger.log(Level.WARNING, "Detected missing gender prefix, return null eqv gender");
            return Command.NULL_CHAR;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }


    //@@author xingjie99
    protected static LocalDate extractStartDate(String params)
            throws ParserException {
        LocalDate startDate = extractGeneralDate(params, Command.COMMAND_PREFIX_START_DATE);
        if (startDate == null) {
            logger.log(Level.WARNING, "Detected empty start date input after prefix but date is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_START_DATE);
        }
        return startDate;
    }

    //@@author xingjie99
    protected static LocalDate extractEndDate(String params)
            throws ParserException {
        LocalDate endDate = extractGeneralDate(params, Command.COMMAND_PREFIX_END_DATE);
        if (endDate == null) {
            logger.log(Level.WARNING, "Detected empty end date input after prefix but date is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_END_DATE);
        }
        return endDate;
    }
    //@@author

    protected static LocalDate extractDate(String params, boolean isRequired)
            throws ParserException {
        LocalDate localDate = extractGeneralDate(params, Command.COMMAND_PREFIX_DATE);
        if (localDate == null && isRequired) {
            logger.log(Level.WARNING, "Detected empty date input after prefix but date is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_DATE);
        }
        if (localDate == null && !isRequired) {
            logger.log(Level.WARNING, "Detected empty date input after prefix, assuming date to be now");
            return LocalDate.now();
        }
        return localDate;
    }

    protected static LocalTime extractTime(String params, boolean isRequired)
            throws ParserException {
        LocalTime localTime = extractGeneralTime(params, Command.COMMAND_PREFIX_TIME);
        if (localTime == null && isRequired) {
            logger.log(Level.WARNING, "Detected empty time input after prefix but time is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_TIME);
        }
        if (localTime == null && !isRequired) {
            logger.log(Level.WARNING, "Detected empty time input after prefix, assuming time to be now");
            return LocalTime.now();
        }
        return localTime;
    }

    protected static LocalDateTime extractDateTime(String params) throws ParserException {
        final LocalTime time = extractTime(params, false);
        final LocalDate date = extractDate(params, false);
        return date.atTime(time);
    }


    protected static ArrayList<Integer> extractDayOfTheWeek(String params)
            throws ParserException {
        try {
            String numberString = extractRelevantParameter(params, Command.COMMAND_PREFIX_DAY_OF_THE_WEEK);
            String[] numberStringArray = numberString.split(Command.COMMAND_INDEX_DELIMITER);
            ArrayList<Integer> daysOfTheWeek = new ArrayList<>();
            for (int i = 0; i < numberStringArray.length; i++) {
                String dayString = numberStringArray[i].trim();
                if (dayString.split(" ").length > 1) {
                    throw new ParserException(String.format(
                            ParserMessages.MESSAGE_ERROR_EXTRA_PARAMETERS, dayString.split(" ")[1]));
                }
                Integer day = Integer.parseInt(numberStringArray[i].trim());
                if (day < ParserMessages.MONDAY || day > ParserMessages.SUNDAY) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK);
                }
                if (daysOfTheWeek.contains(day)) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_REPEATED_DAY_OF_THE_WEEK);
                }
                daysOfTheWeek.add(day);
            }
            logger.log(Level.WARNING, String.format("Days of the week %s", daysOfTheWeek.toString()));
            return daysOfTheWeek;
        } catch (NumberFormatException e) {
            throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_DAY_OF_THE_WEEK);
        } catch (MissingParamException e) {
            logger.log(Level.WARNING, "Detected empty day input after prefix but day is required!");
            throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_DAY_OF_THE_WEEK);
        }
    }


        protected static ArrayList<Integer> extractItemIndexes(String params, String prefix)
                throws ParserException {
            try {
                String numberString = extractRelevantParameter(params, prefix);
                String[] numberStringArray = numberString.split(Command.COMMAND_INDEX_DELIMITER);
                ArrayList<Integer> indexes = new ArrayList<>();
                for (int i = 0; i < numberStringArray.length; i++) {
                    String indexString = numberStringArray[i].trim();
                    if (indexString.split(" ").length > 1) {
                        throw new ParserException(String.format(
                                ParserMessages.MESSAGE_ERROR_EXTRA_PARAMETERS, indexString.split(" ")[1]));
                    }
                    Integer index = convertItemNumToItemIndex(Integer.parseInt(indexString));
                    if (indexes.contains(index)) {
                        throw new ParserException(ParserMessages.MESSAGE_ERROR_DUPLICATE_NUMBERS);
                    }
                    indexes.add(index);
                }
                logger.log(Level.WARNING, String.format("Indexes are %s", indexes.toString()));
                return indexes;
            } catch (NumberFormatException e) {
                throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_ITEM_NUM);
            } catch (MissingParamException e) {
                logger.log(Level.WARNING, "Detected empty item num input after prefix but item num is required!");
                throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_ITEM_NUM);
            }

        }
        //@author

        protected static boolean isFutureDate (LocalDate date){
            return date.isAfter(LocalDate.now());
        }

        protected static int extractItemIndex (String params, String prefix)
            throws ParserException {
            try {
                final Integer itemNum = extractGeneralInteger(params, prefix);
                if (itemNum == null) {
                    throw new ParserException(ParserMessages.MESSAGE_ERROR_NO_ITEM_NUM);
                }
                final int itemIndex = convertItemNumToItemIndex(itemNum);
                if (itemIndex < 0) {
                    throw new InvalidParamException();
                }
                return itemIndex;
            } catch (InvalidParamException e) {
                throw new ParserException(ParserMessages.MESSAGE_ERROR_INVALID_ITEM_NUM);
            }
        }

        protected static int convertItemNumToItemIndex ( int itemNum){
            return itemNum - 1;
        }


        /**
         * Returns the number of parameters detected that is valid for the specific command.
         * This is required as some parameters are optional, therefore an absolute number cannot be expected.
         *
         * @param params   User input string containing all parameters
         * @param prefixes Variable number of prefixes that is valid for the specific command
         * @return Number of parameters detected that is valid for the specific command
         */
        protected static int getNumberOfCorrectParamsDetected (String params, String...prefixes){
            int count = 0;
            for (String prefix : prefixes) {
                if (params.toLowerCase().contains(prefix + Command.COMMAND_PREFIX_DELIMITER)) {
                    count++;
                }
            }
            logger.log(Level.WARNING, String.format("no. of corrected params detected: %s", count));
            return count;
        }

        /**
         * Returns true if there are too many '/' characters in the parameter string.
         *
         * @param params   User input string containing all parameters
         * @param prefixes Variable number of prefixes that is valid for the specific command
         */
        protected static boolean hasExtraDelimiters (String params, String...prefixes){
            final int expectedNum = getNumberOfCorrectParamsDetected(params, prefixes);
            int numOfDelimiters = 0;
            for (int i = 0; i < params.length(); i++) {
                if (params.charAt(i) == Command.COMMAND_PREFIX_DELIMITER.charAt(0)) {
                    numOfDelimiters++;
                }
            }
            logger.log(Level.WARNING, String.format("no. of delimiters detected: %s", numOfDelimiters));
            return numOfDelimiters > expectedNum;
        }
    }
