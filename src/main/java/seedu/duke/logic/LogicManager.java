package seedu.duke.logic;

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

/**
 * Handles the parsing and execution of all commands.
 */
public class LogicManager {
    final ParserManager parserManager;
    final StorageManager storageManager;
    //TODO: replace all this with data class
    final Profile profile;
    final ExerciseList exerciseList;
    final FoodList foodList;
    final ItemBank exerciseBank;
    final ItemBank foodBank;
    final FutureExerciseList futureExerciseList;


    public LogicManager(StorageManager storageManager, Profile profile, ExerciseList exerciseList, FoodList foodList, ItemBank exerciseBank,
                        ItemBank foodBank, FutureExerciseList futureExerciseList) {
        this.parserManager = new ParserManager();
        this.storageManager = storageManager;
        this.profile = profile;
        this.exerciseList = exerciseList;
        this.foodList = foodList;
        this.exerciseBank = exerciseBank;
        this.foodBank = foodBank;
        this.futureExerciseList = futureExerciseList;
    }

    /**
     * Executes the given Command and (to be implemented) calls for storage operation if required.
     *
     * @param userInput Raw user input
     * @return CommandResult representing result of execution of the command
     */
    public CommandResult execute(String userInput) {
        final Command command = parserManager.parseCommand(userInput);
        command.setData(profile, exerciseList, futureExerciseList, foodList, exerciseBank, foodBank);
        final CommandResult result = command.execute();
        try {
            if (ByeCommand.isBye(command)) {
                storageManager.saveAll(this.profile, this.exerciseList, this.foodList,
                        this.futureExerciseList, this.foodBank, this.exerciseBank);
            }
            if (Command.requiresProfileStorageRewrite(command)) {
                storageManager.saveProfile(this.profile);
            }
            if (Command.requiresExerciseListStorageRewrite(command)) {
                storageManager.saveExerciseList(this.exerciseList);
            }
            if (Command.requiresFoodListStorageRewrite(command)) {
                storageManager.saveFoodList(this.foodList);
            }
            if (Command.requiresFutureExerciseListStorageRewrite(command)) {
                storageManager.saveFutureExerciseList(this.futureExerciseList);
            }
            if (Command.requiresFoodBankStorageRewrite(command)) {
                storageManager.saveFoodBank(this.foodBank);
            }
            if (Command.requiresExerciseBankStorageRewrite(command)) {
                storageManager.saveExerciseBank(this.exerciseBank);
            }
        } catch (UnableToWriteFileException e) {
            return new CommandResult(e.getMessage());
        }
        return result;
    }


}
