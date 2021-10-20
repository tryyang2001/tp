package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.ViewCommand;
import seedu.duke.commands.ViewExerciseBankCommand;
import seedu.duke.commands.ViewExerciseListCommand;
import seedu.duke.commands.ViewFoodBankCommand;
import seedu.duke.commands.ViewFoodListCommand;
import seedu.duke.commands.ViewFutureExerciseListCommand;
import seedu.duke.parser.exceptions.ItemNotSpecifiedException;

public class ViewCommandParser implements Parser {

    @Override
    public Command parse(String params) {
        if (params.equals(ParserMessages.EMPTY)) {
            return new ViewCommand();
        }
        try {
            final String itemTypePrefix = ParserUtils.extractItemTypePrefix(params);
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
                    ParserUtils.correctCommandFormatSuggestions(
                            ViewExerciseListCommand.MESSAGE_COMMAND_FORMAT,
                            ViewFoodListCommand.MESSAGE_COMMAND_FORMAT,
                            ViewExerciseBankCommand.MESSAGE_COMMAND_FORMAT,
                            ViewFoodBankCommand.MESSAGE_COMMAND_FORMAT));
        }
    }
}
