package seedu.duke.logic.commands;

import static seedu.duke.logic.commands.CommandMessages.LS;

/**
 * Represents the command that when executed, displays the list of available commands to the user.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    private static final String MESSAGE_HELP_INTRO = "Welcome to the help page." + LS
            + "Below are the commands to get you started." + LS
            + "More details could be found on: " + LS
            + "https://tinyurl.com/fitbotUG";
    private static final String MESSAGE_COMMON_NOTATIONS = "In the formats of the command, identifiers wrapped in "
            + "curly brackets{} means that they are optional.";
    private static final String EMPTY = "";
    public static final String MESSAGE_COMMAND_FORMAT =
            CommandMessages.QUOTATION + COMMAND_WORD + CommandMessages.QUOTATION;
    //TODO: --------------------------------------------deprecated---------------------------------------.

    //    public static final String FORMAT_HEADER = "Format: ";
    //    public static final String FORMAT_PLACEHOLDER = "        ";
    //    private static final String MESSAGE_SUCCESS = "These are the available commands:" + LS + "%s";
    //    private static final String MESSAGE_HELP_ADD = "add -- Adds food or exercise record to the current list."
    //            + LS
    //            + FORMAT_HEADER + AddExerciseBankCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + AddFoodBankCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + AddFoodCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + AddFutureExerciseCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + AddRecurringExerciseCommand.MESSAGE_COMMAND_FORMAT + LS;
    //    private static final String MESSAGE_HELP_BMI = "bmi -- "
    //            + "Calculates the BMI value based on the user's input height and weight." + LS
    //            + FORMAT_HEADER + CalculateBmiCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + CalculateBmiWithProfileCommand.MESSAGE_COMMAND_FORMAT + LS;
    //    private static final String MESSAGE_HELP_BYE = "bye -- "
    //            + "Exits the program and save results from food and exercise items." + LS
    //            + FORMAT_HEADER + ByeCommand.MESSAGE_COMMAND_FORMAT + LS;
    //    private static final String MESSAGE_HELP_DELETE = "delete -- Deletes entry of food or exercise added."
    //            + LS
    //            + FORMAT_HEADER + DeleteExerciseBankCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + DeleteExerciseCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + DeleteFoodBankCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + DeleteFoodCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + DeleteFutureExerciseCommand.MESSAGE_COMMAND_FORMAT + LS;
    //    private static final String MESSAGE_HELP_HELP = "help -- "
    //            + "Shows a list of commands and their usage with some examples." + LS
    //            + FORMAT_HEADER + MESSAGE_COMMAND_FORMAT;
    //    public static final String MESSAGE_HELP_OVERVIEW = "overview -- Shows relevant calories statistics."
    //            + LS
    //            + FORMAT_HEADER + OverviewCommand.MESSAGE_COMMAND_FORMAT + LS;
    //    private static final String MESSAGE_HELP_PROFILE = "profile -- "
    //            + "Edit profile particulars based on the given input. If no inputs is given,\n" + FORMAT_PLACEHOLDER
    //            + "profile is used to view the current profile attributes" + LS
    //            + FORMAT_HEADER + ProfileUpdateCommand.MESSAGE_COMMAND_FORMAT + LS;
    //    private static final String MESSAGE_HELP_VIEW = "view -- Views all the food and/or exercises added."
    //            + LS
    //            + FORMAT_HEADER
    //            + FORMAT_PLACEHOLDER + ViewExerciseBankCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + ViewExerciseListCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + ViewFoodBankCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + ViewFoodListCommand.MESSAGE_COMMAND_FORMAT + LS
    //            + FORMAT_PLACEHOLDER + ViewFutureExerciseListCommand.MESSAGE_COMMAND_FORMAT;
    //    private static final int REVERSE_APPEND = 1;
    //
    //
    //    private String buildHelpString() {//deprecated
    //        StringBuilder helpMessage = new StringBuilder(EMPTY);// need to include the format later
    //        helpMessage.append(MESSAGE_HELP_INTRO).append(LS).append(LS)
    //                .append(MESSAGE_HELP_ADD).append(LS)
    //                .append(MESSAGE_HELP_BMI).append(LS)
    //                .append(MESSAGE_HELP_BYE).append(LS)
    //                .append(MESSAGE_HELP_DELETE).append(LS)
    //                .append(MESSAGE_HELP_HELP).append(LS).append(LS)
    //                .append(MESSAGE_HELP_PROFILE).append(LS)
    //                .append(MESSAGE_HELP_OVERVIEW).append(LS)
    //                .append(MESSAGE_HELP_VIEW).append(LS);
    //
    //
    //        helpMessage.setLength(helpMessage.length() - REVERSE_APPEND);
    //        return helpMessage.toString();
    //    }
    //TODO: --------------------------------------------deprecated---------------------------------------.

    private static String bmi = "bmi -- Calculate the Body-Mass-Index of user\n"
            + "      Format: bmi {h/HEIGHT_IN_CM w/WEIGHT_IN_KG}\n"
            + "      Identifier         Input \n"
            + "\t   h/           Height of user in cm\n"
            + "\t   w/           Weight of user in kg\n"
            + "      If no identifiers are given, bmi will be calculated using the height and weight in the profile.";
    private static String help = "\n"
            + "help -- View help for commands\n"
            + "      Format: help";
    private static String profile = "\n"
            + "profile -- View or modify profile details\n"
            + "      Format: profile n/NAME h/HEIGHT(CM) w/WEIGHT(KG) a/AGE g/CALORIEGOAL s/GENDER(M/F)"
            + " x/ACTIVITYFACTOR(1-5)\n"
            + "      Identifier         Input \n"
            + "\t   n/           Name of user\n"
            + "\t   h/           Height of user in cm\n"
            + "\t   w/           Weight of user in kg\n"
            + "\t   s/           Gender of user, m for male, f for female\n"
            + "\t   a/           Age of user\n"
            + "\t   g/           Net calorie goal of user. (Net calorie is based on calorie of food consumed"
            + " - calories burnt from exercise and bmr\n"
            + "\t   x/           Activity factor of user, range 1 to 5\n"
            + "      If no identifiers are given, user can view the profile particulars.";
    private static String overview = "\n"
            + "overview -- View weekly and daily summary of calories\n"
            + "      Format: overview";
    private static String bye = "\n"
            + "bye -- Exit the program.\n"
            + "      Format: bye";
    private static String add = "      Adding food items\n"
            + "      Format: add f/ITEM {c/CALORIES} {d/DD-MM-YYYY} {t/HHMM}\n"
            + "      Identifier         Input\n"
            + "           f/           Description of the food item\n"
            + "           c/           Calories of the food\n"
            + "           d/           Date of food in DD-MM-YYYY\n"
            + "           t/           Time of food in HHMM\n"
            + "\n"
            + "      Add Exercise Items\n"
            + "      Format: add f/ITEM {c/CALORIES} {d/DD-MM-YYYY} {t/HHMM}\n"
            + "      Identifier        Input\n"
            + "          e/            Description of exercise\n"
            + "          c/            Calories burnt from exercise\n"
            + "          d/            Date of exercise in DD-MM-YYYY\n"
            + "\n"
            + "      Add Recurring Exercise to Upcoming Exercise List\n"
            + "      Format: add r/ITEM c/CALORIES :/START_DATE -/END_DATE @/DAY_OF_THE_WEEK\n"
            + "      Format: delete e/LIST_NO. d/DD-MM-YYYY\n"
            + "      Identifier        Input\n"
            + "          r/            Description of upcoming exercise\n"
            + "          c/            Calories burnt from exercise\n"
            + "          :/            Start date of exercise in DD-MM-YYYY\n"
            + "          -/            End date of exercise in DD-MM-YYYY\n"
            + "          @/            Workout days of the week\n"
            + "\n"
            + "      Add food Item in FoodBank\n"
            + "      Format: add fbank/ITEM c/CALORIES\n"
            + "      Identifier        Input\n"
            + "          fbank/        Description of food\n"
            + "          c/            Calories burnt from exercise\n"
            + "\n"
            + "      Add exercise Item in ExerciseBank\n"
            + "      Format: add fbank/ITEM c/CALORIES\n"
            + "      Identifier        Input\n"
            + "          fbank/        Description of food\n"
            + "          c/            Calories burnt from exercise";
    private static String view = "\n"
            + "      Viewing Food List\n"
            + "      Format: view f/\n"
            + "\n"
            + "      View exercise List\n"
            + "      Format: view e/\n"
            + "\n"
            + "      View Upcoming Exercise List\n"
            + "      Format: view u/\n"
            + "\n"
            + "      View exercise bank\n"
            + "      Format: view e/\n"
            + "\n"
            + "      View food bank\n"
            + "      Format: view f/";
    private static String delete = "      Deleting food items\n"
            + "      Format: delete f/LIST_NO. d/DD-MM-YYYY t/HHMM\n"
            + "      Identifier         Input\n"
            + "           f/           Index of food in food list within the given date\n"
            + "           d/           Date of food in DD-MM-YYYY\n"
            + "           t/           Time of food in HHMM\n"
            + "\n"
            + "      Delete exercise items\n"
            + "      Format: delete e/LIST_NO. d/DD-MM-YYYY\n"
            + "      Identifier        Input\n"
            + "          e/            Description of exercise\n"
            + "          d/            Date of exercise in DD-MM-YYYY\n"
            + "\t\n"
            + "      Delete Upcoming Exercise Item from Upcoming Exercise List\n"
            + "      Format: delete u/LIST_NO.\n"
            + "      Identifier        Input\n"
            + "          u/        The index of the item with in the upcoming exercise list\n"
            + "\n"
            + "      Delete food items from food bank\n"
            + "      Format: delete fbank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}\n"
            + "      Identifier        Input\n"
            + "          fbank/        The index of the item with in the food bank\n"
            + "\n"
            + "      Delete exercise items from exercise bank\n"
            + "      Format: delete ebank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}\n"
            + "      Identifier        Input\n"
            + "          ebank/        The index of the item with in the exercise bank";
    private static String edit = "      Edit FoodBank\n"
            + "      Format: edit fbank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}\n"
            + "      Identifier        Input\n"
            + "          fbank/        The index of the item with in the food bank\n"
            + "          n/            New description of food name\n"
            + "          c/            Calories of food\n"
            + "\t \n"
            + "      Edit ExerciseBank\n"
            + "      Format: edit ebank/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}\n"
            + "      Identifier        Input\n"
            + "          ebank/        The index of the item with in the exercise bank\n"
            + "          n/            New description of exercise name\n"
            + "          c/            Calories burnt from exercise\n"
            + "\n"
            + "     Edit Upcoming Exercise List\n"
            + "     Format: edit u/LIST_NO. {n/NEW_NAME} {c/NEW_CALORIES}\n"
            + "     Identifier        Input\n"
            + "          u/        The index of the item with in the upcoming exercise list\n"
            + "          n/            New description of exercise name\n"
            + "          c/            Calories burnt from exercise   ";

    private String buildHelpString2() {
        StringBuilder helpMessage = new StringBuilder(EMPTY);// need to include the format later
        helpMessage.append(MESSAGE_HELP_INTRO).append(LS).append(LS)
                .append(MESSAGE_COMMON_NOTATIONS).append(LS).append(LS)
                //TODO append the completed add messages format.
                .append("add -- Add food or exercise record to the current list.").append(LS)
                .append(add).append(LS).append(LS)
                //TODO: append bmi
                .append(bmi).append(LS)
                //TODO: append bye
                .append(bye).append(LS).append(LS)
                //TODO append delete
                .append("delete -- Delete entry of food or exercise added from a list.").append(LS)
                .append(delete).append(LS).append(LS)
                //TODO append edit
                .append("edit -- Edit entry of food or exercise added from a list.").append(LS)
                .append(edit).append(LS)
                //TODO append help
                .append(help).append(LS)
                //TODO append profile
                .append(profile).append(LS)
                //TODO append overview
                .append(overview).append(LS).append(LS)
                //TODO append view
                .append("view -- View all the food and/or exercises added.").append(LS)
                .append(view).append(LS);

        return helpMessage.toString().trim();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(buildHelpString2());
    }
}
