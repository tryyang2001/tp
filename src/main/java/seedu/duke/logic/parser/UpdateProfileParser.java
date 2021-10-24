package seedu.duke.logic.parser;

import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.commands.ProfileCommand;
import seedu.duke.logic.commands.ProfileUpdateCommand;
import seedu.duke.logic.parser.exceptions.ParamInvalidException;

/**
 * Parses input arguments for Update Profile command.
 */
public class UpdateProfileParser implements Parser {
    @Override
    public Command parse(String params) {
        if (params.isEmpty()) { //no additional parameters, assumed to be view profile command
            return new ProfileCommand();
        }

        //TODO: Test profile again after storage has been updated

        if (ParserUtils.getNumberOfCorrectParamsDetected(params, ProfileUpdateCommand.EXPECTED_PREFIXES) == 0) {
            return new InvalidCommand(ProfileUpdateCommand.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        if (ParserUtils.hasExtraDelimiters(
                params, ProfileUpdateCommand.EXPECTED_PREFIXES)) {
            return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
        }

        try {
            final String name = ParserUtils.extractName(params);
            final double height = ParserUtils.extractHeight(params);
            final double weight = ParserUtils.extractWeight(params);
            final int calorieGoal = ParserUtils.extractCalorieGoal(params);
            final int age = ParserUtils.extractAge(params);
            final int activityFactor = ParserUtils.extractActivityFactor(params);
            final char gender = ParserUtils.extractGender(params);
            return new ProfileUpdateCommand(name, height, weight, calorieGoal, age, activityFactor, gender);
        } catch (ParamInvalidException e) {
            return new InvalidCommand(e.getMessage());
        }
    }
}
