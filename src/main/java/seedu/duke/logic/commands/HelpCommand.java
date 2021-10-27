package seedu.duke.logic.commands;

/**
 * Represents the command that when executed, displays the list of available commands to the user.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String FORMAT_HEADER = "Format: ";
    public static final String FORMAT_PLACEHOLDER = "        ";
    public static final String MESSAGE_COMMAND_FORMAT = CommandMessages.QUOTATION + COMMAND_WORD + CommandMessages.QUOTATION;
    private static final String MESSAGE_SUCCESS = "These are the available commands:" + CommandMessages.LS + "%s";
    private static final String MESSAGE_HELP_INTRO = "Welcome to the help page." + CommandMessages.LS
            + "Below are the commands to get you started." + CommandMessages.LS
            + "More detaiCommandMessages.LS could be found on: " + CommandMessages.LS
            + "https://tinyurl.com/fitbotUG";
    private static final String MESSAGE_HELP_ADD = "add -- Adds food or exercise record to the current list." + CommandMessages.LS
            + FORMAT_HEADER + AddExerciseBankCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + AddFoodBankCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + AddFoodCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + AddFutureExerciseCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + AddRecurringExerciseCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS;
    private static final String MESSAGE_HELP_BMI = "bmi -- "
            + "Calculates the BMI value based on the user's input height and weight." + CommandMessages.LS
            + FORMAT_HEADER + CalculateBmiCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + CalculateBmiWithProfileCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS;
    private static final String MESSAGE_HELP_BYE = "bye -- "
            + "Exits the program and save results from food and exercise items." + CommandMessages.LS
            + FORMAT_HEADER + ByeCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS;
    private static final String MESSAGE_HELP_DELETE = "delete -- Deletes entry of food or exercise added." + CommandMessages.LS
            + FORMAT_HEADER + DeleteExerciseBankCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + DeleteExerciseCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + DeleteFoodBankCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + DeleteFoodCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + DeleteFutureExerciseCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS;
    private static final String MESSAGE_HELP_HELP = "help -- "
            + "Shows a list of commands and their usage with some examples." + CommandMessages.LS
            + FORMAT_HEADER + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_HELP_OVERVIEW = "overview -- Shows relevant calories statistics." + CommandMessages.LS
            + FORMAT_HEADER + OverviewCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS;
    private static final String MESSAGE_HELP_PROFILE = "profile -- "
            + "Edit profile particulars based on the given input. If no inputs is given,\n" + FORMAT_PLACEHOLDER
            + "profile is used to view the current profile attributes" + CommandMessages.LS
            + FORMAT_HEADER + ProfileUpdateCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS;
    private static final String MESSAGE_HELP_VIEW = "view -- Views all the food and/or exercises added." + CommandMessages.LS
            + FORMAT_HEADER
            + FORMAT_PLACEHOLDER + ViewExerciseBankCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + ViewExerciseListCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + ViewFoodBankCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + ViewFoodListCommand.MESSAGE_COMMAND_FORMAT + CommandMessages.LS
            + FORMAT_PLACEHOLDER + ViewFutureExerciseListCommand.MESSAGE_COMMAND_FORMAT;
    private static final String EMPTY = "";
    private static final int REVERSE_APPEND = 1;


    private String buildHelpString() {
        StringBuilder helpMessage = new StringBuilder(EMPTY);// need to include the format later
        helpMessage.append(MESSAGE_HELP_INTRO).append(CommandMessages.LS).append(CommandMessages.LS)
                .append(MESSAGE_HELP_ADD).append(CommandMessages.LS)
                .append(MESSAGE_HELP_BMI).append(CommandMessages.LS)
                .append(MESSAGE_HELP_BYE).append(CommandMessages.LS)
                .append(MESSAGE_HELP_DELETE).append(CommandMessages.LS)
                .append(MESSAGE_HELP_HELP).append(CommandMessages.LS).append(CommandMessages.LS)
                .append(MESSAGE_HELP_PROFILE).append(CommandMessages.LS)
                .append(MESSAGE_HELP_OVERVIEW).append(CommandMessages.LS)
                .append(MESSAGE_HELP_VIEW).append(CommandMessages.LS);


        helpMessage.setLength(helpMessage.length() - REVERSE_APPEND);
        return helpMessage.toString();
    }


    @Override
    public CommandResult execute() {
        return new CommandResult(buildHelpString().stripTrailing());
    }
}
