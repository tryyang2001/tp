package seedu.duke.storage;

import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.storage.banks.ExerciseBankStorage;
import seedu.duke.storage.banks.FoodBankStorage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.lists.exerciselist.ExerciseListStorage;
import seedu.duke.storage.lists.exerciselist.FutureExerciseListStorage;
import seedu.duke.storage.lists.foodlist.FoodListStorage;
import seedu.duke.storage.profile.ProfileStorage;
import seedu.duke.storage.profile.ProfileStorageInterface;

public class StorageManager implements ProfileStorageInterface {

    public static final String FILEPATH = "./data/";
    public static final String FILENAME_PROFILE = "profile.txt";
    public static final String FILEPATH_PROFILE = FILEPATH + FILENAME_PROFILE;

    private final ProfileStorage profileStorage = new ProfileStorage(FILEPATH_PROFILE);
    private final ExerciseListStorage exerciseListStorage = new ExerciseListStorage();
    private final FoodListStorage foodListStorage = new FoodListStorage();
    private final FutureExerciseListStorage futureExerciseListStorage = new FutureExerciseListStorage();
    private final FoodBankStorage foodBankStorage = new FoodBankStorage();
    private final ExerciseBankStorage exerciseBankStorage = new ExerciseBankStorage();

    @Override
    public Profile loadProfile() throws UnableToReadFileException {
        return profileStorage.loadProfile();
    }

    public ExerciseList loadExerciseList() throws UnableToReadFileException {
        return exerciseListStorage.loadExerciseListFile();
    }

    public FoodList loadFoodList() throws UnableToReadFileException {
        return foodListStorage.loadFoodListFile();
    }

    public FutureExerciseList loadFutureExerciseList() throws UnableToReadFileException {
        return futureExerciseListStorage.loadFutureExerciseListFile();
    }

    public ItemBank loadFoodBank() throws UnableToReadFileException {
        return foodBankStorage.loadFoodBankFile();
    }

    public ItemBank loadExerciseBank() throws UnableToReadFileException {
        return exerciseBankStorage.loadExerciseBankFile();
    }

    public void saveAll(Profile profile, ExerciseList exerciseList, FoodList foodList,
                        FutureExerciseList futureExerciseList, ItemBank foodBank,
                        ItemBank exerciseBank) throws UnableToWriteFileException {
        saveProfile(profile);
        saveExerciseList(exerciseList);
        saveFoodList(foodList);
        saveFutureExerciseList(futureExerciseList);
        saveFoodBank(foodBank);
        saveExerciseBank(exerciseBank);
    }

    @Override
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
