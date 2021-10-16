package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, shows the value of name, height and weight in the Profile.
 */
public class ProfileCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Hello! This is your current profile:";
    private static final String MESSAGE_NAME = "Your name is %s.";
    private static final String MESSAGE_HEIGHT = "Your height is %scm.";
    private static final String MESSAGE_WEIGHT = "Your weight is %skg.";
    private static final String MESSAGE_CALORIE_GOAL = "Your calorie goal is %s cal.";
    private static final String MESSAGE_NO_INFO = "I do not know your %1$s yet, tell me using the command %2$s!";
    public static final String MESSAGE_HELP = "profile -- Shows all particulars in profile." + Ui.LS;

    public ProfileCommand() {
    }

    @Override
    public CommandResult execute() {
        final String nameString = super.profile.getName() == null
                ? String.format(MESSAGE_NO_INFO, "name", ChangeNameCommand.MESSAGE_COMMAND_FORMAT)
                : String.format(MESSAGE_NAME, super.profile.getName());
        final String heightString = super.profile.getHeight() == 0
                ? String.format(MESSAGE_NO_INFO, "height", ChangeHeightCommand.MESSAGE_COMMAND_FORMAT)
                : String.format(MESSAGE_HEIGHT, super.profile.getHeight());
        final String weightString = super.profile.getWeight() == 0
                ? String.format(MESSAGE_NO_INFO, "weight", ChangeWeightCommand.MESSAGE_COMMAND_FORMAT)
                : String.format(MESSAGE_WEIGHT, super.profile.getWeight());
        final String calorieGoalString = super.profile.getCalorieGoal() == 0
                ? String.format(MESSAGE_NO_INFO, "calorie goal", SetGoalCommand.MESSAGE_COMMAND_FORMAT)
                : String.format(MESSAGE_CALORIE_GOAL, super.profile.getCalorieGoal());
        return new CommandResult(MESSAGE_SUCCESS
                + Ui.INDENTED_LS + nameString
                + Ui.INDENTED_LS + heightString
                + Ui.INDENTED_LS + weightString
                + Ui.INDENTED_LS + calorieGoalString);
    }
}
