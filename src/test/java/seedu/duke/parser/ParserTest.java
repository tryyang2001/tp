package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.AddExerciseCommand;
import seedu.duke.commands.AddFoodCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.parser.Parser.MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST;
import static seedu.duke.parser.Parser.MESSAGE_ERROR_INVALID_CALORIES_INFO;


class ParserTest {

    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    void parseCommand_invalidCommandWord_commandDoesNotExistMessage() {
        parseAndAssertIncorrectWithMessage(MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST, "potato", "hi");
    }

    @Test
    void parseAddCommand_correctInput_addCommand() {
        parseAndAssertCommandType("add f/potato c/20", AddFoodCommand.class);
        parseAndAssertCommandType("aDD c/20 f/potato", AddFoodCommand.class);
        parseAndAssertCommandType("add e/potato c/20", AddExerciseCommand.class);
        parseAndAssertCommandType("aDD c/20 e/potato", AddExerciseCommand.class);
    }

    @Test
    void parseAddCommand_itemTypeNotSpecified_itemTypeNotSpecifiedMessage() {
        final String expectedMessage = String.format(Command.MESSAGE_ERROR_ITEM_NOT_SPECIFIED,
                AddFoodCommand.MESSAGE_COMMAND_FORMAT,
                AddExerciseCommand.MESSAGE_COMMAND_FORMAT);
        parseAndAssertIncorrectWithMessage(expectedMessage, "add", "add e", "add c/");
    }

    @Test
    void parseAddCommand_nameNotGiven_invalidCommand() {
        parseAndAssertCommandType("add f/ c/120", InvalidCommand.class);
        parseAndAssertCommandType("add e/ c/120", InvalidCommand.class);
    }

    @Test
    void parseAddCommand_caloriesNotGiven_invalidCommand() {
        parseAndAssertCommandType("add f/potato", InvalidCommand.class);
        parseAndAssertCommandType("add f/potato c/", InvalidCommand.class);
        parseAndAssertCommandType("add e/hiit", InvalidCommand.class);
        parseAndAssertCommandType("add e/hiit c/", InvalidCommand.class);
    }

    @Test
    void parseAddCommand_caloriesNotANumber_caloriesNotNumberMessage() {
        parseAndAssertIncorrectWithMessage(MESSAGE_ERROR_INVALID_CALORIES_INFO,
                "add f/potato c/potato", "add e/hiit c/potato");
    }


    /*
     * Utility methods ====================================================================================
     * Adapted from AddressBook-Level2
     * https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/parser/ParserTest.java
     */

    /**
     * Asserts that parsing the given inputs will return IncorrectCommand with the given feedback message.
     */
    private void parseAndAssertIncorrectWithMessage(String errorMessage, String... inputs) {
        for (String input : inputs) {
            final InvalidCommand result = parseAndAssertCommandType(input, InvalidCommand.class);
            assertEquals(result.errorMessage, errorMessage);
        }
    }

    /**
     * Parses input and asserts the class/type of the returned command object.
     *
     * @param input                to be parsed
     * @param expectedCommandClass expected class of returned command
     * @return the parsed command object
     */
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass) {
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }
}