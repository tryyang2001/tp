package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandMessages;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.commands.ProfileCommand;
import seedu.duke.logic.commands.ProfileUpdateCommand;
import seedu.duke.logic.parser.exceptions.ExtraParamException;
import seedu.duke.logic.parser.exceptions.InvalidParamException;
import seedu.duke.logic.parser.exceptions.ParserException;

/**
 * Parses input arguments for Update Profile command.
 */
public class UpdateProfileParser implements Parser {
    @Override
    public Command parse(String params) {
        if (params.isEmpty()) { //no additional parameters, assumed to be view profile command
            return new ProfileCommand();
        }

        if (ParserUtils.getNumberOfCorrectParamsDetected(params, ProfileUpdateCommand.EXPECTED_PREFIXES) == 0) {
            return new InvalidCommand(CommandMessages.MESSAGE_PROFILE_COMMAND_INVALID_FORMAT);
        }

        if (ParserUtils.hasExtraDelimiters(
                params, ProfileUpdateCommand.EXPECTED_PREFIXES)) {
            return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
        }

        try {
            final String name = ParserUtils.extractName(params);
            final Double height = ParserUtils.extractHeight(params);
            final Double weight = ParserUtils.extractWeight(params);
            final Integer calorieGoal = ParserUtils.extractCalorieGoal(params);
            final Integer age = ParserUtils.extractAge(params);
            final Integer activityFactor = ParserUtils.extractActivityFactor(params);
            final Character gender = ParserUtils.extractGender(params);
            return new ProfileUpdateCommand(name, height, weight, calorieGoal, age, activityFactor, gender);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
