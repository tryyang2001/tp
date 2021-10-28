package seedu.duke.logic.commands;

import static seedu.duke.logic.commands.CommandMessages.LS;

/**
 * Represents the command that when executed, displays the list of available commands to the user.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_ADD_HELP = "add -- Add food or exercise record to the current list.";
    private static final String MESSAGE_HELP_INTRO = "Welcome to the help page." + LS
            + "Below are the commands to get you started." + LS
            + "More details could be found on: " + LS
            + "https://tinyurl.com/fitbot-user-guide";
    private static final String MESSAGE_COMMON_NOTATIONS = "In the formats of the command, identifiers wrapped in "
            + "curly brackets{} means that they are optional.";
    private static final String EMPTY = "";
    public static final String MESSAGE_COMMAND_FORMAT =
            CommandMessages.QUOTATION + COMMAND_WORD + CommandMessages.QUOTATION;
    public static final String MESSAGE_DELETE_HELP = "delete -- Delete entry of food or exercise added from a list.";
    public static final String MESSAGE_EDIT_HELP = "edit -- Edit entry of food or exercise added from a list.";
    public static final String MESSAGE_VIEW_HELP = "view -- View all the food and/or exercises added.";

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
        StringBuilder helpMessage = new StringBuilder(EMPTY);
        helpMessage.append(MESSAGE_HELP_INTRO).append(LS).append(LS)
                .append(MESSAGE_COMMON_NOTATIONS).append(LS).append(LS)
                .append(MESSAGE_ADD_HELP).append(LS)
                .append(add).append(LS).append(LS)
                .append(bmi).append(LS)
                .append(bye).append(LS).append(LS)
                .append(MESSAGE_DELETE_HELP).append(LS)
                .append(delete).append(LS).append(LS)
                .append(MESSAGE_EDIT_HELP).append(LS)
                .append(edit).append(LS)
                .append(help).append(LS)
                .append(profile).append(LS)
                .append(overview).append(LS).append(LS)
                .append(MESSAGE_VIEW_HELP).append(LS)
                .append(view).append(LS);

        return helpMessage.toString().trim();
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(buildHelpString2());
    }
}
