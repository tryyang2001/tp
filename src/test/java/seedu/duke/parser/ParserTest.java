package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.AddExerciseBankCommand;
import seedu.duke.commands.AddExerciseCommand;
import seedu.duke.commands.AddFoodBankCommand;
import seedu.duke.commands.AddFoodCommand;
import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.CalculateBmiCommand;
import seedu.duke.commands.CalculateBmiWithProfileCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.OverviewCommand;
import seedu.duke.commands.ProfileUpdateCommand;
import seedu.duke.commands.ViewCommand;
import seedu.duke.commands.ViewExerciseBankCommand;
import seedu.duke.commands.ViewExerciseListCommand;
import seedu.duke.commands.ViewFoodBankCommand;
import seedu.duke.commands.ViewFoodListCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.parser.Parser.MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST;
import static seedu.duke.parser.Parser.MESSAGE_ERROR_ILLEGAL_CHARACTER;
import static seedu.duke.parser.Parser.MESSAGE_ERROR_INVALID_CALORIES_INFO;
import static seedu.duke.parser.Parser.MESSAGE_ERROR_NOT_A_NUMBER;
import static seedu.duke.parser.Parser.MESSAGE_ERROR_TOO_MANY_DELIMITERS;



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
    void parseCommand_containsTextFileDelimiter_illegalCharacterMessage() {
        parseAndAssertIncorrectWithMessage(MESSAGE_ERROR_ILLEGAL_CHARACTER, "potato | as", "add |", "name h|o");
    }

    @Test
    void parseAddCommand_correctInput_addCommand() {
        parseAndAssertCommandType("add f/potato c/20", AddFoodCommand.class);
        parseAndAssertCommandType("aDD c/20 f/potato", AddFoodCommand.class);
        parseAndAssertCommandType("add e/potato c/20", AddExerciseCommand.class);
        parseAndAssertCommandType("aDD c/20 e/potato", AddExerciseCommand.class);
        parseAndAssertCommandType("add fbank/potato c/20", AddFoodBankCommand.class);
        parseAndAssertCommandType("add ebank/potato c/20", AddExerciseBankCommand.class);
        parseAndAssertCommandType("add c/20 fbank/potato", AddFoodBankCommand.class);
        parseAndAssertCommandType("add c/20 ebank/potato", AddExerciseBankCommand.class);
    }

    @Test
    void parseAddCommand_itemTypeNotSpecified_invalidCommand() {
        parseAndAssertCommandType("add", InvalidCommand.class);
        parseAndAssertCommandType("add a/", InvalidCommand.class);
        parseAndAssertCommandType("add a", InvalidCommand.class);
    }

    @Test
    void parseAddCommand_nameNotGiven_invalidCommand() {
        parseAndAssertCommandType("add f/ c/120", InvalidCommand.class);
        parseAndAssertCommandType("add e/ c/120", InvalidCommand.class);
    }

    @Test
    void parseAddCommand_caloriesNotANumber_caloriesNotNumberMessage() {
        parseAndAssertIncorrectWithMessage(MESSAGE_ERROR_INVALID_CALORIES_INFO,
                "add f/potato c/potato", "add e/hiit c/potato");
    }

    @Test
    void parseByeCommand_correctInput_byeCommand() {
        parseAndAssertCommandType("bye", ByeCommand.class);
        parseAndAssertCommandType("ByE", ByeCommand.class);
    }

    @Test
    void parseCalculateBmiWithProfileCommand_correctInput_calculateBmiWithProfileCommand() {
        parseAndAssertCommandType("bmi", CalculateBmiWithProfileCommand.class);
        parseAndAssertCommandType("BMI", CalculateBmiWithProfileCommand.class);
    }

    @Test
    void parseCalculateBmiCommand_correctInput_calculateBmiCommand() {
        parseAndAssertCommandType("bmi h/50 w/20", CalculateBmiCommand.class);
        parseAndAssertCommandType("BMI w/20 h/50", CalculateBmiCommand.class);
    }

    @Test
    void parseCalculateBmiCommand_parametersNotGiven_invalidCommand() {
        parseAndAssertCommandType("bmi w/20", InvalidCommand.class);
        parseAndAssertCommandType("bmi h/20", InvalidCommand.class);
    }

    @Test
    void parseCalculateBmiCommand_parametersInvalid_errorMessage() {
        parseAndAssertIncorrectWithMessage(
                String.format(MESSAGE_ERROR_NOT_A_NUMBER, "height"),
                "BMI w/20 h/potato");
        parseAndAssertIncorrectWithMessage(
                String.format(MESSAGE_ERROR_NOT_A_NUMBER, "weight"),
                "BMI w/potato h/20");
    }

    @Test
    void parseDeleteCommand_itemTypeNotSpecified_invalidCommand() {
        parseAndAssertCommandType("delete", InvalidCommand.class);
        parseAndAssertCommandType("delete a/", InvalidCommand.class);
    }

    @Test
    void parseDeleteCommand_itemNumInvalid_invalidCommand() {
        parseAndAssertCommandType("delete e/ ", InvalidCommand.class);
        parseAndAssertCommandType("delete f/", InvalidCommand.class);
        parseAndAssertCommandType("delete f/potato", InvalidCommand.class);
        parseAndAssertCommandType("delete e/potato", InvalidCommand.class);
        parseAndAssertCommandType("delete fbank/ ", InvalidCommand.class);
        parseAndAssertCommandType("delete ebank/", InvalidCommand.class);
        parseAndAssertCommandType("delete fbank/potato", InvalidCommand.class);
        parseAndAssertCommandType("delete ebank/potato", InvalidCommand.class);
    }

    @Test
    void parseHelpCommand_correctInput_helpCommand() {
        parseAndAssertCommandType("help", HelpCommand.class);
    }

    @Test
    void parseOverviewCommand_correctInput_overviewCommand() {
        parseAndAssertCommandType("overview", OverviewCommand.class);
    }

    @Test
    void parseProfileUpdateCommand_correctInput_ProfileCreateCommand() {
        parseAndAssertCommandType("profile n/hello w/50 h/80 g/50 ", ProfileUpdateCommand.class);
        parseAndAssertCommandType("profile g/100 w/50 h/80 n/hi potato", ProfileUpdateCommand.class);
        parseAndAssertCommandType("profile h/50 n/hello potato g/20 w/20", ProfileUpdateCommand.class);
    }

    @Test
    void parseProfileCreateCommand_parametersNotGiven_invalidCommand() {
        parseAndAssertCommandType("profile n/ ", InvalidCommand.class);
        parseAndAssertCommandType("profile h/", InvalidCommand.class);
        parseAndAssertCommandType("profile w/", InvalidCommand.class);
        parseAndAssertCommandType("profile n/ h/", InvalidCommand.class);
    }

    @Test
    void parseProfileCreateCommand_parametersInvalid_tooManyDelimitersMessage() {
        parseAndAssertIncorrectWithMessage(MESSAGE_ERROR_TOO_MANY_DELIMITERS,"profile n/hi n/hello");
        parseAndAssertIncorrectWithMessage(MESSAGE_ERROR_TOO_MANY_DELIMITERS,"profile n/h/i n/hello");
        parseAndAssertIncorrectWithMessage(MESSAGE_ERROR_TOO_MANY_DELIMITERS,"profile n/h/i z/hello");
    }

    @Test
    void parseSetGoalCommand_parametersNotGivenOrInvalid_notANumberMessage() {
        parseAndAssertIncorrectWithMessage(String.format(MESSAGE_ERROR_NOT_A_NUMBER, "goal"),
                "goal", "goal nope");
    }

    @Test
    void parseViewCommand_correctInput_viewCommand() {
        parseAndAssertCommandType("view", ViewCommand.class);
        parseAndAssertCommandType("view e/", ViewExerciseListCommand.class);
        parseAndAssertCommandType("view f/", ViewFoodListCommand.class);
        parseAndAssertCommandType("view fbank/", ViewFoodBankCommand.class);
        parseAndAssertCommandType("view ebank/", ViewExerciseBankCommand.class);
    }

    @Test
    void parseViewCommand_itemTypeNotSpecified_invalidCommand() {
        parseAndAssertCommandType("view a/", InvalidCommand.class);
        parseAndAssertCommandType("view a", InvalidCommand.class);
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