package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.item.ItemBank;
import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.item.exercise.FutureExerciseList;
import seedu.duke.item.food.FoodList;
import seedu.duke.parser.Parser;
import seedu.duke.profile.Profile;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.ui.Statistics;
import seedu.duke.ui.Ui;


/**
 * Main class of Fitbot.
 * Initialises the application and starts interaction with user.
 */
public class Main {

    private ExerciseList exerciseItems;
    private FutureExerciseList futureExerciseItems;
    private FoodList foodItems;
    private ItemBank exerciseBank;
    private ItemBank foodBank;
    private Profile profile;
    private Ui ui;
    private Storage storage;

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        new Main().run(args);
    }

    /**
     * Runs the application until command is given to exit it.
     **/
    private void run(String[] args) {
        start();
        checkAndCreateProfile();
        enterTaskModeUntilByeCommand();
        exit();
    }

    /**
     * Initialises the application by creating the required objects and loading data from the
     * storage file, then showing the welcome message.
     */
    private void start() {
        this.storage = new Storage();
        this.ui = new Ui();
        //TODO: replace these with ones from storage
        this.exerciseBank = new ItemBank();
        this.foodBank = new ItemBank();
        this.futureExerciseItems = new FutureExerciseList();
        try {
            this.profile = storage.loadProfileFile();
            this.foodItems = storage.loadFoodListFile();
            this.exerciseItems = storage.loadExerciseListFile();
        } catch (UnableToReadFileException e) {
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
    }

    private void checkAndCreateProfile() {
        if (profile.checkProfileCreated()) {
            return;
        }
        Command command;
        String userInput;
        do {
            showUserMessage(); // this conditional statement contains messages for user to follow
            userInput = ui.getUserInput();
            if (userInput.equals("complete")) {
                setDefaultProfile();
                break;
            }
            command = new Parser().parseCommand(userInput);
            if (!(command instanceof InvalidCommand)) {
                CommandResult result = executeCommand(command);
                ui.formatMessageFramedWithDivider(result.toString());
            } else {
                ui.formatMessageFramedWithDivider("invalid input");
            }
        } while (!profile.checkProfileCreated());
        setDefaultProfile(); // fills in all missing compulsory particulars.
        ui.formatMessageFramedWithDivider("profile is set up.");
    }

    private void showUserMessage() {
        String userMessage;
        final String nameString = profile.getProfileName().getName() == null
                ? Ui.MESSAGE_NO_INFO : profile.getProfileName().getName();
        final String heightString = profile.getProfileHeight().getHeight() == 0
                ? Ui.MESSAGE_NO_INFO : String.format(Ui.MESSAGE_HEIGHT, profile.getProfileHeight().getHeight());
        final String weightString = profile.getProfileWeight().getWeight() == 0
                ? Ui.MESSAGE_NO_INFO : String.format(Ui.MESSAGE_WEIGHT, profile.getProfileWeight().getWeight());
        final String calorieGoalString = String.format(Ui.MESSAGE_CALORIE_GOAL,
                profile.getProfileCalorieGoal().getCalorieGoal());

        userMessage = Ui.MESSAGE_INTRO + ui.LS
                + Ui.NAME_HEADER + nameString + ui.INDENTED_LS + Ui.MESSAGE_NAME_USAGE + ui.LS
                + Ui.HEIGHT_HOLDER + heightString + ui.INDENTED_LS + Ui.MESSAGE_HEIGHT_USAGE + ui.LS
                + Ui.WEIGHT_HOLDER + weightString + ui.INDENTED_LS + Ui.MESSAGE_WEIGHT_USAGE + ui.LS
                + Ui.CALORIE_HOLDER + calorieGoalString + ui.INDENTED_LS + Ui.MESSAGE_GOAL_USAGE + ui.LS
                + Ui.MESSAGE_COMPLETE;
        ui.formatMessageFramedWithDivider(userMessage);
    }

    /**
     * Set default values for name, weight, height for user.
     */
    private void setDefaultProfile() {
//        if (!profile.checkNameCreated()) {
//            String createDefaultNameCommand = "name User";
//            createProfile(createDefaultNameCommand);
//        }
//        if (!profile.checkHeightCreated()) {
//            String createDefaultHeightCommand = "height 170";
//            createProfile(createDefaultHeightCommand);
//        }
//        if (!profile.checkWeightCreated()) {
//            String createDefaultWeightCommand = "weight 65";
//            createProfile(createDefaultWeightCommand);
//        }
    }

    private void createProfile(String createDefaultNameCommand) {
        Command command;
        command = new Parser().parseCommand(createDefaultNameCommand);
        CommandResult result = executeCommand(command);
        ui.formatMessageFramedWithDivider(result.toString());
    }

    /**
     * Reads the user input and executes appropriate command.
     * Runs indefinitely until user inputs the Bye command.
     */
    private void enterTaskModeUntilByeCommand() {
        Command command;
        do {
            String userInput = ui.getUserInput();
            command = new Parser().parseCommand(userInput);
            CommandResult result = executeCommand(command);
            ui.formatMessageFramedWithDivider(result.toString());
        } while (!ByeCommand.isBye(command));
    }

    /**
     * Executes the given Command and (to be implemented) calls for storage operation if required.
     *
     * @param command Command to be executed
     * @return CommandResult representing result of execution of the command
     */
    private CommandResult executeCommand(Command command) {

        command.setData(this.profile, this.exerciseItems, this.futureExerciseItems,
                this.foodItems, this.exerciseBank, this.foodBank);
        CommandResult result = command.execute();
        try {
            if (ByeCommand.isBye(command)) {
                storage.saveAll(this.profile, this.exerciseItems, this.foodItems);
            }
            if (Command.requiresProfileStorageRewrite(command)) {
                storage.saveProfile(this.profile);
            }
            if (Command.requiresExerciseListStorageRewrite(command)) {
                storage.saveExercises(this.exerciseItems);
            }
            if (Command.requiresFoodListStorageRewrite(command)) {
                storage.saveFoodList(this.foodItems);
            }
            if (Command.requiresFutureExerciseListStorageRewrite(command)) {
                //TODO
                //storage.saveFutureExercises(this.futureExerciseItems);
            }
        } catch (UnableToWriteFileException e) {
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
        return result;
    }

    /**
     * Exits the application.
     */
    private void exit() {
        System.exit(0);
    }

}
