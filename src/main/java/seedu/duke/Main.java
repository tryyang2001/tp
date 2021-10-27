package seedu.duke;

import seedu.duke.data.DataManager;
import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.logic.LogicManager;
import seedu.duke.logic.commands.CommandResult;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.ui.Ui;

import java.time.LocalDate;


/**
 * Main class of Fitbot.
 * Initialises the application and starts interaction with user.
 */
public class Main {


//    private ExerciseList exerciseItems;
//    private FutureExerciseList futureExerciseItems;
//    private FoodList foodItems;
//    private ItemBank exerciseBank;
//    private ItemBank foodBank;
//    private Profile profile;
    private DataManager dataManager;
    private Ui ui;
    private StorageManager storageManager;
    private LogicManager logicManager;


    /**
     * Entry point of the application.
     */
    public static void main(String[] args) throws UnableToWriteFileException {
        new Main().run(args);
    }

    /**
     * Runs the application until command is given to exit it.
     **/
    private void run(String[] args) throws UnableToWriteFileException {
        start();
        checkAndCreateProfile();
        loadsFutureExercisesToList();
        enterTaskModeUntilByeCommand();
        exit();
    }

    //@@author tttyyzzz
    private void checkAndCreateProfile() {
        dataManager.setProfile(new StartState(dataManager.getProfile(), storageManager, ui).checkAndCreateProfile());
    }
    //@@author

    /**
     * Initialises the application by creating the required objects and loading data from the
     * storage file, then showing the welcome message.
     */
    private void start() {
        this.storageManager = new StorageManager();
        this.ui = new Ui();
        try {
            dataManager = storageManager.loadAll();
//            profile = storageManager.loadProfile();
//            exerciseItems = storageManager.loadExerciseList();
//            foodItems = storageManager.loadFoodList();
//            futureExerciseItems = storageManager.loadFutureExerciseList();
//            foodBank = storageManager.loadFoodBank();
//            exerciseBank = storageManager.loadExerciseBank();
        } catch (UnableToReadFileException e) {
            dataManager = new DataManager();
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
        this.logicManager = new LogicManager(storageManager, dataManager);
        ui.printStartMessage(dataManager.getProfile().checkProfileComplete(), dataManager.getProfile().checkProfilePresent());

    }


    /**
     * Reads the user input and executes appropriate command.
     * Runs indefinitely until user inputs the Bye command.
     */
    private void enterTaskModeUntilByeCommand() {
        CommandResult result;
        do {
            String userInput = ui.getUserInput();
            result = logicManager.execute(userInput);
            ui.formatMessageFramedWithDivider(result.toString());
        } while (!result.isBye());
    }


    /**
     * Check whether the dates of the exercises in the future exercise list have passed.
     * If the dates have passed, move the exercises in the exercise list.
     */
    private void loadsFutureExercisesToList() throws UnableToWriteFileException {
        int index = 0;
        LocalDate today = LocalDate.now();
        while (dataManager.getFutureExerciseItems().getSize() != 0
                && (dataManager.getFutureExerciseItems().getItem(index).getDate().isBefore(today)
                || dataManager.getFutureExerciseItems().getItem(index).getDate().isEqual(today))) {
            String name = dataManager.getFutureExerciseItems().getItem(index).getName();
            int calories = dataManager.getFutureExerciseItems().getItem(index).getCalories();
            LocalDate date = dataManager.getFutureExerciseItems().getItem(index).getDate();
            dataManager.getExerciseItems().addItem(new Exercise(name, calories, date));
            dataManager.getFutureExerciseItems().deleteItem(index);
            storageManager.saveExerciseList(dataManager.getExerciseItems());
            storageManager.saveFutureExerciseList(dataManager.getFutureExerciseItems());
        }
    }


    /**
     * Executes the given Command and (to be implemented) calls for storage operation if required.
     *
     * @param command Command to be executed
     * @return CommandResult representing result of execution of the command
     */
    /*
    private CommandResult executeCommand(Command command) {

        command.setData(this.profile, this.exerciseItems, this.futureExerciseItems,
                this.foodItems, this.exerciseBank, this.foodBank);
        CommandResult result = command.execute();
        try {
            if (ByeCommand.isBye(command)) {
                storageManager.saveAll(this.profile, this.exerciseItems, this.foodItems,
                        this.futureExerciseItems, this.foodBank, this.exerciseBank);
            }
            if (Command.requiresProfileStorageRewrite(command)) {
                storageManager.saveProfile(this.profile);
            }
            if (Command.requiresExerciseListStorageRewrite(command)) {
                storageManager.saveExerciseList(this.exerciseItems);
            }
            if (Command.requiresFoodListStorageRewrite(command)) {
                storageManager.saveFoodList(this.foodItems);
            }
            if (Command.requiresFutureExerciseListStorageRewrite(command)) {
                storageManager.saveFutureExerciseList(this.futureExerciseItems);
            }
            if (Command.requiresFoodBankStorageRewrite(command)) {
                storageManager.saveFoodBank(this.foodBank);
            }
            if (Command.requiresExerciseBankStorageRewrite(command)) {
                storageManager.saveExerciseBank(this.exerciseBank);
            }
        } catch (UnableToWriteFileException e) {
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
        return result;
    }
*/

    /**
     * Exits the application.
     */
    private void exit() {
        System.exit(0);
    }

}
