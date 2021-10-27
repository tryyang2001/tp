package seedu.duke;


<<<<<<< HEAD
import seedu.duke.data.item.Item;
import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.Food;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;

import seedu.duke.data.DataManager;
import seedu.duke.data.item.exercise.Exercise;

=======
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.DataManager;
>>>>>>> c1dd15e993dd4ac4489537a365abeedb82b7e92e
import seedu.duke.logic.LogicManager;
import seedu.duke.logic.commands.ByeCommand;
import seedu.duke.logic.commands.Command;
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
<<<<<<< HEAD
<<<<<<< HEAD
    private ExerciseList filteredExerciseItems;
=======

    /*
>>>>>>> 45c3afa0b6c68e68d34192efc20910d59b67c688
    private ExerciseList exerciseItems;
    private FutureExerciseList futureExerciseItems;
    private FoodList filteredFoodItems;
    private FoodList foodItems;
    private ItemBank exerciseBank;
    private ItemBank foodBank;
    private Profile profile;*/
=======

    private ExerciseList filteredExerciseItems;
>>>>>>> c1dd15e993dd4ac4489537a365abeedb82b7e92e
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
        new StartState(profile, storageManager, ui).checkAndCreateProfile();
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
        this.filteredFoodItems = new FoodList();
        this.filteredExerciseItems = new ExerciseList();
        try {
            dataManager = storageManager.loadAll();
         //     filterExerciseListWithPastSevenDaysRecordOnly();  
          //  filterFoodListWithPastSevenDaysRecordOnly();
        } catch (UnableToReadFileException e) {
            dataManager = new DataManager();
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
        this.logicManager = new LogicManager(storageManager, dataManager);
        ui.printStartMessage(
                dataManager.getProfile().checkProfileComplete(),
                dataManager.getProfile().checkProfilePresent());
    }

    /**
     * Filters food list and add food items that are within 7 days before today.
     */
    private void filterFoodListWithPastSevenDaysRecordOnly() {
        LocalDate today = LocalDate.now();
        for (int i = foodItems.getSize() - 1; i >= 0; i--) {
            Food food = (Food) foodItems.getItem(i);
            if (food.getDate().isBefore(today.minusDays(7))) {
                break;
            }
            if (isWithinPastSevenDays(food, today)) {
                filteredFoodItems.addItem(food);
            }
        }
    }

    /**
     * Checks if the item is within 7 days of today.
     *
     * @param item The item from the item list
     * @return True if the item date is not before 7 days from today, and is not after today
     */
    private boolean isWithinPastSevenDays(Item item, LocalDate today) {
        boolean isBeforeOrEqualToday = item.getDate().isEqual(today) || item.getDate().isBefore(today);
        boolean isWithinOneWeek = item.getDate().isAfter(today.minusDays(8));
        return isBeforeOrEqualToday && isWithinOneWeek;
    }

    /**
     * Filters exercise list and add exercises that are within 7 days before today.
     */
    private void filterExerciseListWithPastSevenDaysRecordOnly() {
        LocalDate today = LocalDate.now();
        for (int i = exerciseItems.getSize() - 1; i >= 0; i--) {
            Exercise exercise = (Exercise) exerciseItems.getItem(i);
            if (exercise.getDate().isBefore(today.minusDays(7))) {
                break;
            }
            if (isWithinPastSevenDays(exercise, today)) {
                filteredExerciseItems.addItem(exercise);
            }
        }
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

    //@@author xingjie99
    /**
     * Check whether the dates of the exercises in the future exercise list have passed.
     * If the dates have passed, move the exercises in the exercise list.
     */
    private void loadsFutureExercisesToList() throws UnableToWriteFileException {
        int index = 0;
        LocalDate today = LocalDate.now();
<<<<<<< HEAD
<<<<<<< HEAD
=======
/*
>>>>>>> c1dd15e993dd4ac4489537a365abeedb82b7e92e
        while (futureExerciseItems.getSize() != 0 && (futureExerciseItems.getItem(index).getDate().isBefore(today)
                || futureExerciseItems.getItem(index).getDate().isEqual(today))) {
            System.out.println(today);
            String name = futureExerciseItems.getItem(index).getName();
            int calories = futureExerciseItems.getItem(index).getCalories();
            LocalDate date = futureExerciseItems.getItem(index).getDate();
            filteredExerciseItems.addItem(new Exercise(name, calories, date));
            futureExerciseItems.deleteItem(index);
            storageManager.saveExerciseList(filteredExerciseItems);
            storageManager.saveFutureExerciseList(futureExerciseItems);
<<<<<<< HEAD
=======
=======
*/
>>>>>>> c1dd15e993dd4ac4489537a365abeedb82b7e92e
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
<<<<<<< HEAD
>>>>>>> 45c3afa0b6c68e68d34192efc20910d59b67c688
=======
>>>>>>> c1dd15e993dd4ac4489537a365abeedb82b7e92e
        }
    }
    //@@author

    /**
     * Executes the given Command and (to be implemented) calls for storage operation if required.
     *
     * @param command Command to be executed
     * @return CommandResult representing result of execution of the command
     */
    private CommandResult executeCommand(Command command) {
        command.setData(this.profile, this.filteredExerciseItems, this.futureExerciseItems,
                this.filteredFoodItems, this.exerciseBank, this.foodBank);
        System.out.println(profile.getProfileCalorieGoal().getCalorieGoal());
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
                exerciseItems.addAll(filteredExerciseItems);
                storageManager.saveExerciseList(this.exerciseItems);
            }
            if (Command.requiresFoodListStorageRewrite(command)) {
                foodItems.addAll(filteredFoodItems);
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

    /**
     * Exits the application.
     */
    private void exit() {
        System.exit(0);
    }

}
