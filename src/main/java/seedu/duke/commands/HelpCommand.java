package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, displays the list of available commands to the user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    private static final String MESSAGE_SUCCESS = "These are the available commands:" + Ui.LS + "%s";
    private static final String MESSAGE_HELP_INTRO = "Welcome to the help page.\n"
            + "Below are the commands to get you started.\n"
            + "More details could be found on: \n"
            + "https://frost-action-9f0.notion.site/FitBot-User-Guide-ddffabf5e4d546acb200bffab02b9ecb";
    private static final String MESSAGE_HELP_HELP = "help -- "
            + "Shows a list of commands and their usage with some examples.";
    private static final String MESSAGE_HELP_PROFILE = "profile -- "
            + "Inputs height of user in centimetres, weight of user in kg\n"
            + "\t      and name of user in test.";
    private static final String MESSAGE_HELP_NAME = "name -- change the name in the profile.";
    private static final String MESSAGE_HELP_OVERVIEW = "overview -- shows relevant calories statistics.";
    private static final String MESSAGE_HELP_HEIGHT = "height -- changes height in profile.";
    private static final String MESSAGE_HELP_WEIGHT = "weight -- changes weight in profile.";
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
    private static final String FORMAT_HEADER = "Format: ";

    private String buildHelpString() {
        StringBuilder helpMessage = new StringBuilder(EMPTY);// format to be added later
        helpMessage.append(MESSAGE_HELP_INTRO).append(Ui.LS).append(Ui.LS)
                .append(MESSAGE_HELP_ADD).append(Ui.LS)
                .append(MESSAGE_HELP_BMI).append(Ui.LS)
                .append(MESSAGE_HELP_BYE).append(Ui.LS)
                .append(MESSAGE_HELP_DELETE).append(Ui.LS)
                .append(MESSAGE_HELP_GOAL).append(Ui.LS)
                .append(MESSAGE_HELP_HEIGHT).append(Ui.LS)
                .append(MESSAGE_HELP_HELP).append(Ui.LS)
                .append(MESSAGE_HELP_NAME).append(Ui.LS)
                .append(MESSAGE_HELP_OVERVIEW).append(Ui.LS)
                .append(MESSAGE_HELP_PROFILE).append(Ui.LS)
                .append(MESSAGE_HELP_VIEW).append(Ui.LS)
                .append(MESSAGE_HELP_WEIGHT).append(Ui.LS);
        helpMessage.setLength(helpMessage.length() - REVERSE_APPEND);
        return helpMessage.toString();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, buildHelpString()));
    }
}
