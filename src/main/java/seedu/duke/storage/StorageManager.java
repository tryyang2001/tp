package seedu.duke.storage;

import seedu.duke.data.DataManager;
import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.storage.data.exercise.exercisebank.ExerciseBankStorage;
import seedu.duke.storage.data.exercise.exercisebank.ExerciseBankStorageInterface;
import seedu.duke.storage.data.exercise.exerciselist.ExerciseListStorage;
import seedu.duke.storage.data.exercise.exerciselist.ExerciseStorageInterface;
import seedu.duke.storage.data.exercise.futurelist.FutureExerciseListStorage;
import seedu.duke.storage.data.exercise.futurelist.UpcomingStorageInterface;
import seedu.duke.storage.data.food.foodbank.FoodBankStorage;
import seedu.duke.storage.data.food.foodbank.FoodBankStorageInterface;
import seedu.duke.storage.data.food.foodlist.FoodListStorage;
import seedu.duke.storage.data.food.foodlist.FoodStorageInterface;
import seedu.duke.storage.data.profile.ProfileStorage;
import seedu.duke.storage.data.profile.ProfileStorageInterface;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

/**
 * Manages the loading and saving from various storage subclasses.
 */
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

    private final ProfileStorage profileStorage;
    private final ExerciseListStorage exerciseListStorage;
    private final FoodListStorage foodListStorage;
    private final FutureExerciseListStorage futureExerciseListStorage;
    private final FoodBankStorage foodBankStorage;
    private final ExerciseBankStorage exerciseBankStorage;

    public StorageManager() {
        this.profileStorage = new ProfileStorage(FILEPATH_PROFILE);
        this.exerciseListStorage = new ExerciseListStorage(FILEPATH_LIST_EXERCISE);
        this.foodListStorage = new FoodListStorage(FILEPATH_LIST_FOOD);
        this.futureExerciseListStorage = new FutureExerciseListStorage(FILEPATH_LIST_FUTURE);
        this.foodBankStorage = new FoodBankStorage(FILEPATH_BANK_FOOD);
        this.exerciseBankStorage = new ExerciseBankStorage(FILEPATH_BANK_EXERCISE);
    }

    /**
     * Loads all the data into a DataManager object.
     *
     * @return DataManager containing items loaded from storage
     */
    public DataManager loadAll() {
        return new DataManager(
                loadExerciseList(),
                loadFutureExerciseList(),
                loadFoodList(),
                loadExerciseBank(),
                loadFoodBank(),
                loadProfile());
    }

    public void saveAll(DataManager dataManager) throws UnableToWriteFileException {
        saveProfile(dataManager.getProfile());
        saveExerciseList(dataManager.getExerciseItems());
        saveFoodList(dataManager.getFoodItems());
        saveFutureExerciseList(dataManager.getFutureExerciseItems());
        saveFoodBank(dataManager.getFoodBank());
        saveExerciseBank(dataManager.getExerciseBank());
    }


    //=================== Profile Methods =======================

    @Override
    public Profile loadProfile() {
        try {
            return profileStorage.loadProfile();
        } catch (UnableToReadFileException e) {
            return new Profile();
        }
    }

    @Override
    public void saveProfile(Profile profile) throws UnableToWriteFileException {
        profileStorage.saveProfile(profile);
    }

    //=================== ExerciseList Methods ==================

    @Override
    public ExerciseList loadExerciseList() {
        try {
            return exerciseListStorage.loadExerciseList();
        } catch (UnableToReadFileException e) {
            return new ExerciseList();
        }
    }

    @Override
    public void saveExerciseList(ExerciseList exerciseList) throws UnableToWriteFileException {
        exerciseListStorage.saveExerciseList(exerciseList);
    }

    //================= FutureExerciseList Methods ==============

    @Override
    public FutureExerciseList loadFutureExerciseList() {
        try {
            return futureExerciseListStorage.loadFutureExerciseList();
        } catch (UnableToReadFileException e) {
            return new FutureExerciseList();
        }
    }

    @Override
    public void saveFutureExerciseList(FutureExerciseList futureExerciseList) throws UnableToWriteFileException {
        futureExerciseListStorage.saveFutureExerciseList(futureExerciseList);
    }

    //===================== FoodList Methods ====================

    @Override
    public FoodList loadFoodList() {
        try {
            return foodListStorage.loadFoodList();
        } catch (UnableToReadFileException e) {
            return new FoodList();
        }
    }

    @Override
    public void saveFoodList(FoodList foodList) throws UnableToWriteFileException {
        foodListStorage.saveFoodList(foodList);
    }

    //================= ExerciseBank Methods ====================

    @Override
    public ItemBank loadExerciseBank() {
        try {
            return exerciseBankStorage.loadExerciseBank();
        } catch (UnableToReadFileException e) {
            return new ItemBank();
        }
    }

    @Override
    public void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException {
        exerciseBankStorage.saveExerciseBank(exerciseBank);
    }

    //===================== FoodBank Methods ====================

    @Override
    public ItemBank loadFoodBank() {
        try {
            return foodBankStorage.loadFoodBank();
        } catch (UnableToReadFileException e) {
            return new ItemBank();
        }
    }

    @Override
    public void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException {
        foodBankStorage.saveFoodBank(foodBank);
    }

}
