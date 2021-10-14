package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, displays the list of available commands to the user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    private static final String MESSAGE_SUCCESS = "These are the available commands:" + Ui.LS;
    private static final String MESSAGE_HELP_INTRO = "Welcome to the help page.\n"
            + "Below are the commands to get you started.\n"
            + "More details could be found on: \n"
            + "https://frost-action-9f0.notion.site/FitBot-User-Guide-ddffabf5e4d546acb200bffab02b9ecb \n"
            + MESSAGE_SUCCESS
            + Ui.LS;
    public static final String MESSAGE_HELP = "help -- "
            + "Shows a list of commands and their usage with some examples."
            + Ui.INDENTED_LS  + Ui.FORMAT_HEADER + MESSAGE_COMMAND_FORMAT + Ui.LS + Ui.LS;
    private static final String EMPTY = "";
    private static final int REVERSE_APPEND = 1;

    /**
     * Combines help messages from different command class and combine them into a string.
     *
     * @return String of help message for console
     */
    private String buildHelpString() {
        StringBuilder helpMessage = new StringBuilder(EMPTY);// format to be added later
        helpMessage.append(MESSAGE_HELP_INTRO)
                .append(AddExerciseCommand.MESSAGE_HELP)
                .append(CalculateBmiCommand.MESSAGE_HELP)
                .append(ByeCommand.MESSAGE_HELP)
                .append(DeleteExerciseCommand.MESSAGE_HELP)
                .append(SetGoalCommand.MESSAGE_HELP)
                .append(ChangeHeightCommand.MESSAGE_HELP)
                .append(MESSAGE_HELP)
                .append(OverviewCommand.MESSAGE_HELP)
                .append(ProfileCreateCommand.MESSAGE_HELP)
                .append(ViewCommand.MESSAGE_HELP)
                .append(ChangeWeightCommand.MESSAGE_HELP);
        helpMessage.setLength(helpMessage.length() - REVERSE_APPEND);
        return helpMessage.toString();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(buildHelpString());
    }
}
