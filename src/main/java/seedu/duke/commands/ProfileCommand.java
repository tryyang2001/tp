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

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
