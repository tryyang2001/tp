package seedu.duke.logic;

import seedu.duke.data.DataManager;
import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.logic.commands.ByeCommand;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandResult;
import seedu.duke.logic.parser.ParserManager;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import javax.xml.crypto.Data;

//@@author tlyi
/**
 * Handles the parsing and execution of all commands.
 */
public class LogicManager {
    final ParserManager parserManager;
    final StorageManager storageManager;
    final DataManager dataManager;
    //TODO: replace all this with data class
//    final Profile profile;
//    final ExerciseList exerciseList;
//    final FoodList foodList;
//    final ItemBank exerciseBank;
//    final ItemBank foodBank;
//    final FutureExerciseList futureExerciseList;

    public LogicManager(StorageManager storageManager, DataManager dataManager) {
        this.parserManager = new ParserManager();
        this.storageManager = storageManager;
        this.dataManager = dataManager;
    }

//    public LogicManager(StorageManager storageManager,
//                        Profile profile, ExerciseList exerciseList, FoodList foodList, ItemBank exerciseBank,
//                        ItemBank foodBank, FutureExerciseList futureExerciseList) {
//        this.parserManager = new ParserManager();
//        this.storageManager = storageManager;
//        this.profile = profile;
//        this.exerciseList = exerciseList;
//        this.foodList = foodList;
//        this.exerciseBank = exerciseBank;
//        this.foodBank = foodBank;
//        this.futureExerciseList = futureExerciseList;
//    }

    /**
     * Executes the given Command and (to be implemented) calls for storage operation if required.
     *
     * @param userInput Raw user input
     * @return CommandResult representing result of execution of the command
     */
    public CommandResult execute(String userInput) {
        final Command command = parserManager.parseCommand(userInput);
        command.setData(
                dataManager.getProfile(),
                dataManager.getExerciseItems(),
                dataManager.getFutureExerciseItems(),
                dataManager.getFoodItems(),
                dataManager.getExerciseBank(),
                dataManager.getFoodBank());
        final CommandResult result = command.execute();
        try {
            if (ByeCommand.isBye(command)) {
                storageManager.saveAll(
                        dataManager.getProfile(),
                        dataManager.getExerciseItems(),
                        dataManager.getFoodItems(),
                        dataManager.getFutureExerciseItems(),
                        dataManager.getExerciseBank(),
                        dataManager.getFoodBank());
            }
            if (Command.requiresProfileStorageRewrite(command)) {
                storageManager.saveProfile(dataManager.getProfile());
            }
            if (Command.requiresExerciseListStorageRewrite(command)) {
                storageManager.saveExerciseList(dataManager.getExerciseItems());
            }
            if (Command.requiresFoodListStorageRewrite(command)) {
                storageManager.saveFoodList(dataManager.getFoodItems());
            }
            if (Command.requiresFutureExerciseListStorageRewrite(command)) {
                storageManager.saveFutureExerciseList(dataManager.getFutureExerciseItems());
            }
            if (Command.requiresFoodBankStorageRewrite(command)) {
                storageManager.saveFoodBank(dataManager.getFoodBank());
            }
            if (Command.requiresExerciseBankStorageRewrite(command)) {
                storageManager.saveExerciseBank(dataManager.getExerciseBank());
            }
        } catch (UnableToWriteFileException e) {
            return new CommandResult(e.getMessage());
        }
        return result;
    }


}
