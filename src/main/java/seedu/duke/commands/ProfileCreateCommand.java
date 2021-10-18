package seedu.duke.commands;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;

/**
 * Represents the command that when executed, changes the value of name, height and weight in the Profile.
 */
public class ProfileCreateCommand extends Command {
    public static int COMMAND_EXPECTED_NUM_DELIMITERS = 4;
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_PROFILE
            + " " + COMMAND_PREFIX_NAME + COMMAND_PREFIX_DELIMITER + "W "
            + COMMAND_PREFIX_HEIGHT + COMMAND_PREFIX_DELIMITER + "X "
            + COMMAND_PREFIX_WEIGHT + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_GOAL + COMMAND_PREFIX_DELIMITER + "Z"
            + QUOTATION + " where W is your name, X is your height in CM,"
            + INDENTED_LS + "Y is your weight in KG and Z is your calorie goal.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to create your profile? Use this format:"
            + INDENTED_LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Hello %1$s! Your profile has been created!"
            + INDENTED_LS + "Your height is %2$scm."
            + INDENTED_LS + "Your weight is %3$skg."
            + INDENTED_LS + "Your calories goal is %4$s cal.";

    private final String name;
    private final double weight;
    private final double height;
    private final int calorieGoal;


    public ProfileCreateCommand(String name, double height, double weight, int calorieGoal) {
        assert name != null : "parser should have ensured name is not null";
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.calorieGoal = calorieGoal;
    }

    @Override
    public CommandResult execute() {
        try {
            super.profile.setProfile(this.name, this.height, this.weight, this.calorieGoal);
            return new CommandResult(String.format(
                    MESSAGE_SUCCESS,
                    this.name,
                    this.height,
                    this.weight,
                    this.calorieGoal));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
