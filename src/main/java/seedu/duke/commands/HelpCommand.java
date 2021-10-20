package seedu.duke.commands;

/**
 * Represents the command that when executed, displays the list of available commands to the user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
    private static final String MESSAGE_SUCCESS = "These are the available commands:" + LS + "%s";
    private static final String MESSAGE_HELP_INTRO = "Welcome to the help page." + LS
            + "Below are the commands to get you started." + LS
            + "More details could be found on: " + LS
            + "https://tinyurl.com/fitbotUG";
    private static final String MESSAGE_HELP_HELP = "help -- "
            + "Shows a list of commands and their usage with some examples.";
    private static final String MESSAGE_HELP_PROFILE = "profile -- "
            + "Inputs height of user in centimetres, weight of user in kg" + LS
            + "\t      and name of user in test.";
    private static final String MESSAGE_HELP_GOAL = "goal -- Inputs net calorie goal of user in calorie.";
    private static final String MESSAGE_HELP_BMI = "bmi -- "
            + "Calculates the BMI value based on the user's input height and weight.";
    private static final String MESSAGE_HELP_ADD = "add -- Adds food or exercise record to the current list.";
    private static final String MESSAGE_HELP_VIEW = "view -- Views all the food and/or exercises added.";
    private static final String MESSAGE_HELP_DELETE = "delete -- Deletes entry of food or exercise added.";
    private static final String MESSAGE_HELP_BYE = "bye -- "
            + "Exits the program and save results from food and exercise items.";
    private static final String EMPTY = "";
    private static final int REVERSE_APPEND = 1;

    private String buildHelpString() {
        StringBuilder helpMessage = new StringBuilder(EMPTY);// need to include the format later
        helpMessage.append(MESSAGE_HELP_INTRO).append(LS).append(LS) // complete by tonight
                .append(MESSAGE_HELP_HELP).append(LS).append(LS)
                .append(MESSAGE_HELP_PROFILE).append(LS)
                .append(MESSAGE_HELP_GOAL).append(LS)
                .append(MESSAGE_HELP_BMI).append(LS)
                .append(MESSAGE_HELP_ADD).append(LS)
                .append(MESSAGE_HELP_VIEW).append(LS)
                .append(MESSAGE_HELP_DELETE).append(LS)
                .append(MESSAGE_HELP_BYE).append(LS);
        helpMessage.setLength(helpMessage.length() - REVERSE_APPEND);
        return helpMessage.toString();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, buildHelpString()));
    }
}
