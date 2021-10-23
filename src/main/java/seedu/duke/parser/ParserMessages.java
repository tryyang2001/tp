package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.HelpCommand;

/**
 * Contains all message constants that Parser classes uses.
 */
public class ParserMessages {
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
    protected static final String MESSAGE_ERROR_INVALID_ITEM_NUM = "Please input the item number as a number "
            + "greater than 0! E.g 1";
    protected static final String MESSAGE_ERROR_TOO_MANY_DELIMITERS = "Please do not use the character "
            + QUOTATION + Command.COMMAND_PREFIX_DELIMITER + QUOTATION
            + " in your input other than to specify parameters!";
    protected static final String FILE_TEXT_DELIMITER = "|";
    protected static final String MESSAGE_ERROR_ILLEGAL_CHARACTER = "Please do not use the character "
            + QUOTATION + FILE_TEXT_DELIMITER + QUOTATION
            + " in your input!";
    protected static final String MESSAGE_ERROR_INVALID_FORMAT = "Invalid format for this command! "
            + "Please follow one of the formats:";
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
    protected static final String MESSAGE_ERROR_NO_TIME = "Please input the time for this item!";
    protected static final String MESSAGE_ERROR_NO_DAY_OF_THE_WEEK = "Please input the day of reoccurrence "
            + "for this item!";
    protected static final String MESSAGE_ERROR_REPEATED_DAY_OF_THE_WEEK = "Please check your input of the day "
            + "of reoccurrence and make sure that there is no repeated day";
    protected static final int MONDAY = 1;
    protected static final int SUNDAY = 7;
    protected static final String MESSAGE_ERROR_EDIT_NO_PARAMETERS = "Please input at least one detail to change "
            + "about this item!";
}
