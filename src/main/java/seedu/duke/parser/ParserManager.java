package seedu.duke.parser;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.OverviewCommand;

import java.util.logging.Logger;

/**
 * Parses user input to determine which command to execute.
 */
public class ParserManager {

    protected static final Logger logger = Logger.getLogger(ParserManager.class.getName());
    
    /**
     * Returns the correct command to be executed depending on user input.
     * Command words are case-insensitive.
     *
     * @param input Raw user input string
     * @return Command class representing the correct command to be executed
     */
    public Command parseCommand(String input) {
        ParserManager parser;

        if (ParserUtils.hasTextFileDelimiter(input)) {
            return new InvalidCommand(ParserMessages.MESSAGE_ERROR_ILLEGAL_CHARACTER);
        }

        final String[] commandAndParams = ParserUtils.splitInputIntoCommandAndParams(input);
        final String commandWord = commandAndParams[0].toLowerCase(); //case-insensitive (all lower case)
        final String params = commandAndParams[1];

        switch (commandWord) {
        case Command.COMMAND_WORD_ADD:
            return new AddCommandParser().parse(params);
        case Command.COMMAND_WORD_DELETE:
            return new DeleteCommandParser().parse(params);
        case Command.COMMAND_WORD_VIEW:
            return new ViewCommandParser().parse(params);
        case Command.COMMAND_WORD_BMI:
            return new BmiParser().parse(params);
        case Command.COMMAND_WORD_PROFILE:
            return new UpdateProfileParser().parse(params);
        case OverviewCommand.COMMAND_WORD:
            return new OverviewCommand();
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            return new InvalidCommand(ParserMessages.MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST);
        }
        
    }
}
