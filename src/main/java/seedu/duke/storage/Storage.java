package seedu.duke.storage;

import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;
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
    private static final String FILEPATH = "./data/";
    public static final String FILENAME_PROFILE = "profile.txt";
    public static final String FILENAME_FOOD_LIST = "food_list.txt";
    public static final String FILENAME_EXERCISE_LIST = "exercise_list.txt";
    public static final String FILEPATH_PROFILE = FILEPATH + FILENAME_PROFILE;
    public static final String FILEPATH_FOOD_LIST = FILEPATH + FILENAME_FOOD_LIST;
    public static final String FILEPATH_EXERCISE_LIST = FILEPATH + FILENAME_EXERCISE_LIST;

    private static final String MESSAGE_CREATE_PROFILE = "No profile detected!"
            + "A new profile has been created successfully.";
    private static final String MESSAGE_CREATE_FOOD_LIST = "No food items detected!"
            + "A new food list has been created successfully.";
    private static final String MESSAGE_CREATE_EXERCISE_LIST = "No exercises detected!"
            + "A new exercise has been created successfully.";

    private final Encoder encoder = new Encoder();
    private final Decoder decoder = new Decoder();
    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    public Storage() {

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
        checkForFile(FILEPATH_EXERCISE_LIST);
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
        checkForFile(FILEPATH_FOOD_LIST);
        return readFromFoodListFile();
    }

    private void checkForFile(String filePath) throws UnableToReadFileException {
        File f = new File(filePath);
        String[] paths = filePath.split("/");
        String fileName = paths[2];
        try {
            Files.createDirectories(Paths.get("./data"));
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "There is an error accessing the file ", fileName);
            throw new UnableToReadFileException(fileName);
        }
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
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_EXERCISE_LIST);
            throw new UnableToReadFileException(FILEPATH_EXERCISE_LIST);
        }
    }

    private FoodList readFromFoodListFile() throws UnableToReadFileException {
        try {
            return decoder.getFoodListFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_FOOD_LIST);
            throw new UnableToReadFileException(FILEPATH_FOOD_LIST);
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
        writeToFile(exerciseList, FILEPATH_EXERCISE_LIST);
    }

    /**
     * Saves the food items into storage.
     * Used when there is an update to the list.
     *
     * @param foodItems FoodList to be saved
     */
    public void saveFoodList(FoodList foodItems) throws UnableToWriteFileException {
        ArrayList<String> foodList = encoder.encodeFoodList(foodItems);
        writeToFile(foodList, FILEPATH_FOOD_LIST);
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
