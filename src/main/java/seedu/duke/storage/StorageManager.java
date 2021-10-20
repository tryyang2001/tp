package seedu.duke.storage;

import seedu.duke.item.Item;
import seedu.duke.item.ItemBank;
import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.item.exercise.FutureExerciseList;
import seedu.duke.item.food.FoodList;
import seedu.duke.profile.Profile;
import seedu.duke.storage.banks.ExerciseBankDecoder;
import seedu.duke.storage.banks.ExerciseBankStorage;
import seedu.duke.storage.banks.FoodBankStorage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.lists.exerciselist.ExerciseListStorage;
import seedu.duke.storage.lists.exerciselist.FutureExerciseListStorage;
import seedu.duke.storage.lists.foodlist.FoodListStorage;
import seedu.duke.storage.profile.ProfileStorage;

public class StorageManager {

    private ProfileStorage profileStorage = new ProfileStorage();
    private ExerciseListStorage exerciseListStorage = new ExerciseListStorage();
    private FoodListStorage foodListStorage = new FoodListStorage();
    private FutureExerciseListStorage futureExerciseListStorage = new FutureExerciseListStorage();
    private FoodBankStorage foodBankStorage = new FoodBankStorage();
    private ExerciseBankStorage exerciseBankStorage = new ExerciseBankStorage();

    public void loadAll(Profile profile, ExerciseList exerciseList, FoodList foodList,
                        FutureExerciseList futureExerciseList, ItemBank foodBank,
                        ItemBank exerciseBank) throws UnableToReadFileException {
        loadProfile(profile); //Not sure if should keep this inside.
        loadExerciseList(exerciseList);
        loadFoodList(foodList);
        loadFutureExerciseList(futureExerciseList);
        loadFoodBank(foodBank);
        loadExerciseBank(exerciseBank);
    }

    public void loadProfile(Profile profile) throws UnableToReadFileException {
        profile = profileStorage.loadProfileFile();
    }

    public void loadExerciseList(ExerciseList exerciseList) throws UnableToReadFileException {
        exerciseList = exerciseListStorage.loadExerciseListFile();
    }

    public void loadFoodList(FoodList foodList) throws UnableToReadFileException {
        foodList = foodListStorage.loadFoodListFile();
    }

    public void loadFutureExerciseList(FutureExerciseList futureExerciseList) throws UnableToReadFileException {
        futureExerciseList = futureExerciseListStorage.loadFutureExerciseListFile();
    }

    public void loadFoodBank(ItemBank foodBank) throws UnableToReadFileException {
        foodBank = foodBankStorage.loadFoodBankFile();
    }

    public void loadExerciseBank(ItemBank exerciseBank) throws UnableToReadFileException {
        exerciseBank = exerciseBankStorage.loadExerciseBankFile();
    }

    public void saveAll(Profile profile, ExerciseList exerciseList, FoodList foodList,
                        FutureExerciseList futureExerciseList, ItemBank foodBank,
                        ItemBank exerciseBank) throws UnableToWriteFileException {

    }

    public void saveProfile(Profile profile) throws UnableToWriteFileException {
        profileStorage.saveProfile(profile);
    }

    public void saveExerciseList(ExerciseList exerciseList) throws UnableToWriteFileException {
        exerciseListStorage.saveExercises(exerciseList);
    }

    public void saveFoodList(FoodList foodList) throws UnableToWriteFileException {
        foodListStorage.saveFoodList(foodList);
    }

    public void saveFutureExerciseList(FutureExerciseList futureExerciseList) throws UnableToWriteFileException {
        futureExerciseListStorage.saveFutureList(futureExerciseList);
    }

    public void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException {
        foodBankStorage.saveFoodBank(foodBank);
    }

    public void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException {
        exerciseBankStorage.saveExerciseBank(exerciseBank);
    }

}
