package seedu.duke.commands;

/**
 * Represents the command that when executed, sets the net calorie goal for Profile.
 */
public class SetGoalCommand extends Command {
    public static final String COMMAND_WORD = "goal";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + " X" + QUOTATION
            + ", where X is your net calorie goal for the day";
    public static final String MESSAGE_SUCCESS = "Your goal has been set!"
            + LS + "Current net calorie goal per day: %s";

    private final int calorieGoal;

    public SetGoalCommand(int calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    @Override
    public CommandResult execute() {
        super.profile.setCalorieGoal(this.calorieGoal);
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.profile.getCalorieGoal()));
    }
}
