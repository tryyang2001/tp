package seedu.duke.storage;

import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.storage.data.exercise.ExerciseBankStorage;
import seedu.duke.storage.data.food.FoodBankInterface;
import seedu.duke.storage.data.food.FoodBankStorage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.data.exercise.ExerciseListStorage;
import seedu.duke.storage.data.exercise.FutureExerciseListStorage;
import seedu.duke.storage.data.food.FoodListStorage;
import seedu.duke.storage.data.profile.ProfileStorage;
import seedu.duke.storage.data.profile.ProfileStorageInterface;

public class StorageManager implements ProfileStorageInterface, FoodBankInterface {

    public static final String FILE_TEXT_DELIMITER = "\\|";

    public static final String FILEPATH = "./data/";
    public static final String FILENAME_PROFILE = "profile.txt";
    public static final String FILEPATH_PROFILE = FILEPATH + FILENAME_PROFILE;

    public static final String FILENAME_BANK_FOOD = "food_bank.txt";
    public static final String FILEPATH_BANK_FOOD = FILEPATH + FILENAME_BANK_FOOD;

    private final ProfileStorage profileStorage = new ProfileStorage(FILEPATH_PROFILE);
    private final ExerciseListStorage exerciseListStorage = new ExerciseListStorage();
    private final FoodListStorage foodListStorage = new FoodListStorage();
    private final FutureExerciseListStorage futureExerciseListStorage = new FutureExerciseListStorage();
    private final FoodBankStorage foodBankStorage = new FoodBankStorage(FILEPATH_BANK_FOOD);
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

    @Override
    public ItemBank loadFoodBank() throws UnableToReadFileException {
        return foodBankStorage.loadFoodBank();
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

    @Override
    public void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException {
        foodBankStorage.saveFoodBank(foodBank);
    }

    public void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException {
        exerciseBankStorage.saveExerciseBank(exerciseBank);
    }

}
