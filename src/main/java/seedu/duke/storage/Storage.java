package seedu.duke.storage;

import seedu.duke.item.ItemBank;
import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.item.exercise.FutureExerciseList;
import seedu.duke.item.food.FoodList;
import seedu.duke.profile.Profile;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles all read and write operations to the storage files.
 */
public class Storage {
    public static final String FILENAME_PROFILE = "profile.txt";
    public static final String FILENAME_LIST_FOOD = "food_list.txt";
    public static final String FILENAME_LIST_EXERCISE = "exercise_list.txt";
    public static final String FILENAME_LIST_FUTURE = "future_list.txt";
    public static final String FILENAME_BANK_FOOD = "food_bank.txt";
    public static final String FILENAME_BANK_EXERCISE = "exercise_bank.txt";

    public static final String FILEPATH = "./data/";
    public static final String FILEPATH_PROFILE = FILEPATH + FILENAME_PROFILE;
    public static final String FILEPATH_LIST_FOOD = FILEPATH + FILENAME_LIST_FOOD;
    public static final String FILEPATH_LIST_EXERCISE = FILEPATH + FILENAME_LIST_EXERCISE;
    public static final String FILEPATH_LIST_FUTURE = FILEPATH + FILENAME_LIST_FUTURE;
    public static final String FILEPATH_BANK_FOOD = FILEPATH + FILENAME_BANK_FOOD;
    public static final String FILEPATH_BANK_EXERCISE = FILEPATH + FILENAME_BANK_EXERCISE;

    private final Encoder encoder = new Encoder();
    private final Decoder decoder = new Decoder();
    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    public Storage() {

    }

    private void checkForFile(String filePath) throws UnableToReadFileException {
        File f = new File(filePath);
        String[] paths = filePath.split("/");
        String fileName = paths[2];
        try {
            Files.createDirectories(Paths.get(FILEPATH));
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "There is an error accessing the file ", fileName);
            throw new UnableToReadFileException(fileName);
        }
    }

    /**
     * Load profile data into a profile object.
     * Used when the selected profile is accessed.
     *
     * @return Profile object with the details from the storage file
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    public Profile loadProfileFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_PROFILE);
        return readFromProfileFile();
    }

    /**
     * Load exercises into an ExerciseList object.
     * Used when the selected profile is accessed and its respective ExerciseList is loaded.
     *
     * @return ExerciseList object with the details from the storage file
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    public ExerciseList loadExerciseListFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_LIST_EXERCISE);
        return readFromExerciseListFile();
    }

    /**
     * Load food items into a FoodList object.
     * Used when the selected profile is accessed and its respective ExerciseList is loaded.
     *
     * @return FoodList object with the details from the storage file
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    public FoodList loadFoodListFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_LIST_FOOD);
        return readFromFoodListFile();
    }

    public FutureExerciseList loadFutureExerciseListFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_LIST_FUTURE);
        return readFromFutureListFile();
    }

    public ItemBank loadFoodBankFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_BANK_FOOD);
        return readFromFoodBankFile();
    }

    public ItemBank loadExerciseBankFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_BANK_EXERCISE);
        return readFromExerciseBankFile();
    }

    private Profile readFromProfileFile() throws UnableToReadFileException {
        try {
            return decoder.getProfileFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_PROFILE);
            throw new UnableToReadFileException(FILENAME_PROFILE);
        } catch (InvalidCharacteristicException e) {
            logger.log(Level.WARNING, "The profile has a invalid characteristic.");
            throw new UnableToReadFileException(FILENAME_PROFILE);
        }
    }

    private ExerciseList readFromExerciseListFile() throws UnableToReadFileException {
        try {
            return decoder.getExerciseListFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_LIST_EXERCISE);
            throw new UnableToReadFileException(FILEPATH_LIST_EXERCISE);
        }
    }

    private FoodList readFromFoodListFile() throws UnableToReadFileException {
        try {
            return decoder.getFoodListFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_LIST_FOOD);
            throw new UnableToReadFileException(FILEPATH_LIST_FOOD);
        }
    }

    private FutureExerciseList readFromFutureListFile() throws UnableToReadFileException {
        try {
            return decoder.getFutureListFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_LIST_FUTURE);
            throw new UnableToReadFileException(FILEPATH_LIST_FUTURE);
        }
    }

    private ItemBank readFromFoodBankFile() throws UnableToReadFileException {
        try {
            return decoder.getFoodBankFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_LIST_FOOD);
            throw new UnableToReadFileException(FILEPATH_LIST_FOOD);
        }
    }

    private ItemBank readFromExerciseBankFile() throws UnableToReadFileException {
        try {
            return decoder.getExerciseBankFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_LIST_FOOD);
            throw new UnableToReadFileException(FILEPATH_LIST_FOOD);
        }
    }

    /**
     * Saves all the files into storage.
     * Used when the program exits.
     *
     * @param p Profile of the current user
     * @param e ExerciseList of the respective profile
     * @param f FoodList of the respective profile
     */
    public void saveAll(Profile p, ExerciseList e, FoodList f) throws UnableToWriteFileException {
        saveProfile(p);
        saveExercises(e);
        saveFoodList(f);
    }

    /**
     * Saves the profile details into storage.
     * Used when there is an update to a profile attribute.
     *
     * @param profile Profile of the current user
     */
    public void saveProfile(Profile profile) throws UnableToWriteFileException {
        ArrayList<String> profileDetails = encoder.encodeProfileDetails(profile);
        writeToFile(profileDetails, FILEPATH_PROFILE);
    }

    /**
     * Saves the exercises into storage.
     * Used when there is an update to the list.
     *
     * @param exercises ExerciseList to be saved
     */
    public void saveExercises(ExerciseList exercises) throws UnableToWriteFileException {
        ArrayList<String> exerciseList = encoder.encodeExerciseList(exercises);
        writeToFile(exerciseList, FILEPATH_LIST_EXERCISE);
    }

    /**
     * Saves the food items into storage.
     * Used when there is an update to the list.
     *
     * @param foodItems FoodList to be saved
     */
    public void saveFoodList(FoodList foodItems) throws UnableToWriteFileException {
        ArrayList<String> foodList = encoder.encodeFoodList(foodItems);
        writeToFile(foodList, FILEPATH_LIST_FOOD);
    }

    public void saveFutureList(FutureExerciseList futureExercises) throws UnableToWriteFileException {
        ArrayList<String> futureExerciseList = encoder.encodeExerciseList(futureExercises);
        writeToFile(futureExerciseList, FILENAME_LIST_FUTURE);
    }

    public void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException {
        ArrayList<String> foodBankList = encoder.encodeItemBank(foodBank);
        writeToFile(foodBankList, FILEPATH_BANK_FOOD);
    }

    public void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException {
        ArrayList<String> exerciseBankList = encoder.encodeItemBank(exerciseBank);
        writeToFile(exerciseBankList, FILEPATH_BANK_EXERCISE);
    }

    private void writeToFile(ArrayList<String> itemList, String filePath) throws UnableToWriteFileException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (String item : itemList) {
                fw.write(item + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new UnableToWriteFileException();
        }
    }
}
