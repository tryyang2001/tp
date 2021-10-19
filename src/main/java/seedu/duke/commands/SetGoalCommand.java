package seedu.duke.commands;

import seedu.duke.profile.Profile;
import seedu.duke.profile.attributes.CalorieGoal;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;

/**
 * Represents the command that when executed, sets the net calorie goal for Profile.
 */
public class SetGoalCommand extends Command {
    public static final String COMMAND_WORD = "goal";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + " X" + QUOTATION
            + ", where X is your net calorie goal for the day";
    public static final String MESSAGE_SUCCESS = "Your goal has been set!"
            + LS + "Current net calorie goal per day: %s";

    private CalorieGoal calorieGoal = new CalorieGoal();

    public SetGoalCommand(int calorieGoal) {
        this.calorieGoal.setCalorieGoal(calorieGoal);
    }

    private void checkIfCommandShouldExecute() throws InvalidCharacteristicException {
        if (!calorieGoal.isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_CALORIE_GOAL);
        }
    }

    @Override
    public CommandResult execute() {
        try {
            checkIfCommandShouldExecute();
            super.profile.setProfileCalorieGoal(this.calorieGoal);
            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    super.profile.getProfileCalorieGoal().getCalorieGoal()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
