package seedu.duke.storage;

import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.storage.data.exercise.exercisebank.ExerciseBankStorage;
import seedu.duke.storage.data.exercise.exercisebank.ExerciseBankStorageInterface;
import seedu.duke.storage.data.exercise.exerciselist.ExerciseStorageInterface;
import seedu.duke.storage.data.exercise.futurelist.UpcomingStorageInterface;
import seedu.duke.storage.data.food.foodbank.FoodBankStorageInterface;
import seedu.duke.storage.data.food.foodbank.FoodBankStorage;
import seedu.duke.storage.data.food.foodlist.FoodStorageInterface;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.data.exercise.exerciselist.ExerciseListStorage;
import seedu.duke.storage.data.exercise.futurelist.FutureExerciseListStorage;
import seedu.duke.storage.data.food.foodlist.FoodListStorage;
import seedu.duke.storage.data.profile.ProfileStorage;
import seedu.duke.storage.data.profile.ProfileStorageInterface;

public class StorageManager implements ProfileStorageInterface, FoodBankStorageInterface,
        ExerciseBankStorageInterface, ExerciseStorageInterface, UpcomingStorageInterface, FoodStorageInterface {

    public static final String FILE_TEXT_DELIMITER = "\\|";

    public static final String FILEPATH = "./data/";
    public static final String FILENAME_PROFILE = "profile.txt";
    public static final String FILEPATH_PROFILE = FILEPATH + FILENAME_PROFILE;
    public static final String FILENAME_BANK_FOOD = "food_bank.txt";
    public static final String FILEPATH_BANK_FOOD = FILEPATH + FILENAME_BANK_FOOD;
    public static final String FILENAME_LIST_FOOD = "food_list.txt";
    public static final String FILEPATH_LIST_FOOD = FILEPATH + FILENAME_LIST_FOOD;
    public static final String FILENAME_BANK_EXERCISE = "exercise_bank.txt";
    public static final String FILEPATH_BANK_EXERCISE = FILEPATH + FILENAME_BANK_EXERCISE;
    public static final String FILENAME_LIST_EXERCISE = "exercise_list.txt";
    public static final String FILEPATH_LIST_EXERCISE = FILEPATH + FILENAME_LIST_EXERCISE;
    public static final String FILENAME_LIST_FUTURE = "future_list.txt";
    public static final String FILEPATH_LIST_FUTURE = FILEPATH + FILENAME_LIST_FUTURE;

    private final ProfileStorage profileStorage = new ProfileStorage(FILEPATH_PROFILE);
    private final ExerciseListStorage exerciseListStorage = new ExerciseListStorage(FILEPATH_LIST_EXERCISE);
    private final FoodListStorage foodListStorage = new FoodListStorage(FILEPATH_LIST_FOOD);
    private final FutureExerciseListStorage futureExerciseListStorage =
            new FutureExerciseListStorage(FILEPATH_LIST_FUTURE);
    private final FoodBankStorage foodBankStorage = new FoodBankStorage(FILEPATH_BANK_FOOD);
    private final ExerciseBankStorage exerciseBankStorage = new ExerciseBankStorage(FILEPATH_BANK_EXERCISE);

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

    // =================== Profile Methods =======================
    @Override
    public Profile loadProfile() throws UnableToReadFileException {
        return profileStorage.loadProfile();
    }

    @Override
    public void saveProfile(Profile profile) throws UnableToWriteFileException {
        profileStorage.saveProfile(profile);
    }

    // =================== ExerciseList Methods ==================
    @Override
    public ExerciseList loadExerciseList() throws UnableToReadFileException {
        return exerciseListStorage.loadExerciseList();
    }

    @Override
    public void saveExerciseList(ExerciseList exerciseList) throws UnableToWriteFileException {
        exerciseListStorage.saveExerciseList(exerciseList);
    }

    // ================= FutureExerciseList Methods ==============
    @Override
    public FutureExerciseList loadFutureExerciseList() throws UnableToReadFileException {
        return futureExerciseListStorage.loadFutureExerciseList();
    }

    @Override
    public void saveFutureExerciseList(FutureExerciseList futureExerciseList) throws UnableToWriteFileException {
        futureExerciseListStorage.saveFutureExerciseList(futureExerciseList);
    }

    // ===================== FoodList Methods ====================
    @Override
    public FoodList loadFoodList() throws UnableToReadFileException {
        return foodListStorage.loadFoodList();
    }

    @Override
    public void saveFoodList(FoodList foodList) throws UnableToWriteFileException {
        foodListStorage.saveFoodList(foodList);
    }

    // ================= ExerciseBank Methods ====================
    public ItemBank loadExerciseBank() throws UnableToReadFileException {
        return exerciseBankStorage.loadExerciseBank();
    }

    public void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException {
        exerciseBankStorage.saveExerciseBank(exerciseBank);
    }

    // ===================== FoodBank Methods ====================
    @Override
    public ItemBank loadFoodBank() throws UnableToReadFileException {
        return foodBankStorage.loadFoodBank();
    }

    @Override
    public void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException {
        foodBankStorage.saveFoodBank(foodBank);
    }

}
