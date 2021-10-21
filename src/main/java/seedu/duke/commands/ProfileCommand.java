package seedu.duke.commands;

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

    public ProfileCommand() {
    }

    @Override
    public CommandResult execute() {
        //TODO: Change the format of this. 1. Profile attributes should not be empty. 2. Missing attributes in message
        final String nameString = super.profile.getProfileName().getName() == null
                ? String.format(MESSAGE_NO_INFO, "name", ChangeNameCommand.MESSAGE_COMMAND_FORMAT)
                : String.format(MESSAGE_NAME, super.profile.getProfileName().getName());
        final String heightString = super.profile.getProfileHeight().getHeight() == 0
                ? String.format(MESSAGE_NO_INFO, "height", ChangeHeightCommand.MESSAGE_COMMAND_FORMAT)
                : String.format(MESSAGE_HEIGHT, super.profile.getProfileHeight().getHeight());
        final String weightString = super.profile.getProfileWeight().getWeight() == 0
                ? String.format(MESSAGE_NO_INFO, "weight", ChangeWeightCommand.MESSAGE_COMMAND_FORMAT)
                : String.format(MESSAGE_WEIGHT, super.profile.getProfileWeight().getWeight());
        final String calorieGoalString = super.profile.getProfileCalorieGoal().getCalorieGoal() == 0
                ? String.format(MESSAGE_NO_INFO, "calorie goal", SetGoalCommand.MESSAGE_COMMAND_FORMAT)
                : String.format(MESSAGE_CALORIE_GOAL, super.profile.getProfileCalorieGoal().getCalorieGoal());
        return new CommandResult(MESSAGE_SUCCESS
                + INDENTED_LS + nameString
                + INDENTED_LS + heightString
                + INDENTED_LS + weightString
                + INDENTED_LS + calorieGoalString);
    }
}
