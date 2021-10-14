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
    private static final String helpMessage = MESSAGE_HELP_INTRO
            + AddExerciseCommand.MESSAGE_HELP
            + CalculateBmiCommand.MESSAGE_HELP
            + ByeCommand.MESSAGE_HELP
            + DeleteExerciseCommand.MESSAGE_HELP
            + SetGoalCommand.MESSAGE_HELP
            + ChangeHeightCommand.MESSAGE_HELP
            + MESSAGE_HELP
            + OverviewCommand.MESSAGE_HELP
            + ProfileCreateCommand.MESSAGE_HELP
            + ViewCommand.MESSAGE_HELP
            + ChangeWeightCommand.MESSAGE_HELP;


    @Override
    public CommandResult execute() {
        return new CommandResult(helpMessage.stripTrailing());
    }
}
