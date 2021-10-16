package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, displays the list of available commands to the user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;

    private static final String MESSAGE_SUCCESS = "These are the available commands:" + Ui.LS;
    private static final String MESSAGE_HELP_INTRO = "Welcome to the help page." + Ui.LS
            + "Below are the commands to get you started." + Ui.LS
            + "More details could be found on: https://tinyurl.com/fitbotUG" + Ui.LS;
    

    public static final String MESSAGE_HELP = "help -- "
            + "Shows a list of commands and their usage with some examples."
            + Ui.INDENTED_LS  + Ui.FORMAT_HEADER + MESSAGE_COMMAND_FORMAT + Ui.LS;
    private static final String helpMessage = MESSAGE_HELP_INTRO
            + Ui.LS + AddExerciseCommand.MESSAGE_HELP                 // add e/
            + Ui.LS + AddFoodCommand.MESSAGE_HELP                     // add f/
            + Ui.LS + CalculateBmiWithProfileCommand.MESSAGE_HELP     // bmi
            + Ui.LS + CalculateBmiCommand.MESSAGE_HELP                // bmi h/ w/
            + Ui.LS + ByeCommand.MESSAGE_HELP                         // bye
            + Ui.LS + DeleteExerciseCommand.MESSAGE_HELP              // delete e/
            + Ui.LS + DeleteFoodCommand.MESSAGE_HELP                  // delete f/
            + Ui.LS + SetGoalCommand.MESSAGE_HELP                     // goal
            + Ui.LS + ChangeHeightCommand.MESSAGE_HELP                // height
            + Ui.LS + MESSAGE_HELP                                    // help
            + Ui.LS + OverviewCommand.MESSAGE_HELP                    // overview
            + Ui.LS + ProfileCommand.MESSAGE_HELP                     // profile
            + Ui.LS + ProfileCreateCommand.MESSAGE_HELP               // profile n/ w/ h/ g/
            + Ui.LS + ViewCommand.MESSAGE_HELP                        // view
            + Ui.LS + ViewExerciseListCommand.MESSAGE_HELP            // view e/
            + Ui.LS + ViewFoodListCommand.MESSAGE_HELP                // view f/
            + Ui.LS + ChangeWeightCommand.MESSAGE_HELP                // weight
            ;


    @Override
    public CommandResult execute() {
        return new CommandResult(helpMessage.stripTrailing());
    }
}
