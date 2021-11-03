package seedu.duke.logic.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.logic.commands.AddExerciseBankCommand;
import seedu.duke.logic.commands.AddExerciseCommand;
import seedu.duke.logic.commands.AddFoodBankCommand;
import seedu.duke.logic.commands.AddFoodCommand;
import seedu.duke.logic.commands.ByeCommand;
import seedu.duke.logic.commands.CalculateBmiCommand;
import seedu.duke.logic.commands.CalculateBmiWithProfileCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.HelpCommand;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.commands.OverviewCommand;
import seedu.duke.logic.commands.ProfileUpdateCommand;
import seedu.duke.logic.commands.ViewExerciseBankCommand;
import seedu.duke.logic.commands.ViewExerciseListCommand;
import seedu.duke.logic.commands.ViewFoodBankCommand;
import seedu.duke.logic.commands.ViewFoodListCommand;
import seedu.duke.logic.commands.ViewFutureExerciseListCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ParserManagerTest {

    private ParserManager parser;

    @BeforeEach
    public void setUp() {
        parser = new ParserManager();
    }

    @Test
    void parseCommand_invalidCommandWord_commandDoesNotExistMessage() {
        parseAndAssertIncorrectWithMessage(ParserMessages.MESSAGE_ERROR_COMMAND_DOES_NOT_EXIST,
                "potato", "hi");
    }

    @Test
    void parseCommand_containsTextFileDelimiter_illegalCharacterMessage() {
        parseAndAssertIncorrectWithMessage(ParserMessages.MESSAGE_ERROR_ILLEGAL_CHARACTER,
                "potato | as", "add |", "name h|o");
    }

    @Test
    void parseAddCommand_correctInput_addCommand() {
        parseAndAssertCommandType("add f/potato c/20", AddFoodCommand.class);
        parseAndAssertCommandType("add e/potato c/20", AddExerciseCommand.class);
        parseAndAssertCommandType("add fbank/potato c/20", AddFoodBankCommand.class);
        parseAndAssertCommandType("add ebank/potato c/20", AddExerciseBankCommand.class);
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
        parseAndAssertIncorrectWithMessage(ParserMessages.MESSAGE_ERROR_INVALID_CALORIES_INFO,
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
                String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "height"),
                "BMI w/20 h/potato");
        parseAndAssertIncorrectWithMessage(
                String.format(ParserMessages.MESSAGE_ERROR_NOT_A_NUMBER, "weight"),
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
        parseAndAssertCommandType("profile n/hello w/50 h/80 g/50 a/23 s/F x/2", ProfileUpdateCommand.class);
        parseAndAssertCommandType("profile g/100 w/50 h/80 n/hi potato a/23 s/F x/2", ProfileUpdateCommand.class);
        parseAndAssertCommandType("profile h/50 n/hello potato g/20 w/20 a/23 s/F x/2", ProfileUpdateCommand.class);
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
        parseAndAssertIncorrectWithMessage(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS,"profile n/hi n/hello");
        parseAndAssertIncorrectWithMessage(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS,"profile n/h/i n/hello");
        parseAndAssertIncorrectWithMessage(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS,"profile n/h/i z/hello");
    }

    @Test
    void parseViewCommand_correctInput_viewCommand() {
        parseAndAssertCommandType("view e/", ViewExerciseListCommand.class);
        parseAndAssertCommandType("view f/", ViewFoodListCommand.class);
        parseAndAssertCommandType("view fbank/", ViewFoodBankCommand.class);
        parseAndAssertCommandType("view ebank/", ViewExerciseBankCommand.class);
        parseAndAssertCommandType("view u/", ViewFutureExerciseListCommand.class);
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