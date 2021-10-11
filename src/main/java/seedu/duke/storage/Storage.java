package seedu.duke.storage;

import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles all read and write operations to the storage files.
 */
public class Storage {
    private static final String FILEPATH = "./data/";
    private static final String FILENAME_PROFILE = "profile.txt";
    private static final String FILENAME_FOOD_LIST = "food_list.txt";
    private static final String FILENAME_EXERCISE_LIST = "exercise_list.txt";
    private static final String FILEPATH_PROFILE = FILEPATH + FILENAME_PROFILE;
    private static final String FILEPATH_FOOD_LIST = FILEPATH + FILENAME_FOOD_LIST;
    private static final String FILEPATH_EXERCISE_LIST = FILEPATH + FILENAME_EXERCISE_LIST;

    private static final String MESSAGE_CREATE_PROFILE = "No profile detected!"
            + "A new profile has been created successfully.";
    private static final String MESSAGE_CREATE_FOOD_LIST = "No food items detected!"
            + "A new food list has been created successfully.";
    private static final String MESSAGE_CREATE_EXERCISE_LIST = "No exercises detected!"
            + "A new exercise has been created successfully.";

    private final Encoder ENCODER = new Encoder();
    private final Decoder DECODER = new Decoder();

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
        File profileFile = new File(FILEPATH_PROFILE);
        checkForFile(profileFile, FILEPATH_PROFILE);
        //TODO Decode from profile.txt
        Profile profile;
        return profile;
    }

    /**
     * Load exercises into an ExerciseList object.
     * Used when the selected profile is accessed and its respective ExerciseList is loaded.
     *
     * @return ExerciseList object with the details from the storage file
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    public ExerciseList loadExerciseListFile() throws UnableToReadFileException {
        File profileFile = new File(FILEPATH_PROFILE);
        checkForFile(profileFile, FILEPATH_PROFILE);
        //TODO Decode from exercise_list.txt
        ExerciseList exercises;
        return exercises;
    }

    /**
     * Load food items into a FoodList object.
     * Used when the selected profile is accessed and its respective ExerciseList is loaded.
     *
     * @return ExerciseList object with the details from the storage file
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    public FoodList loadFoodListFile() throws UnableToReadFileException {
        File profileFile = new File(FILEPATH_PROFILE);
        checkForFile(profileFile, FILEPATH_PROFILE);
        //TODO Decode from food_list.txt
        FoodList foodItems;
        return foodItems;
    }

    private void checkForFile(File f, String filePath) throws UnableToReadFileException {
        String[] paths = filePath.split("/");
        String fileName = paths[2];
        try {
            Files.createDirectories(Paths.get(FILEPATH));
            if (f.createNewFile()) {
                printFileCreated(fileName);
            }
        } catch (IOException e) {
            throw new UnableToReadFileException(fileName);
        }
    }

    private void printFileCreated(String fileName) {
        switch (fileName) {
        case FILENAME_PROFILE:
            System.out.println(MESSAGE_CREATE_PROFILE);
            break;
        case FILENAME_EXERCISE_LIST:
            System.out.println(MESSAGE_CREATE_EXERCISE_LIST);
            break;
        case FILENAME_FOOD_LIST:
            System.out.println(MESSAGE_CREATE_FOOD_LIST);
            break;
        default:
            break;
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
    public void saveAll(Profile p, ExerciseList e, FoodList f) {
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
    public void saveProfile(Profile profile) {
        ArrayList<String> profileDetails = ENCODER.encodeProfileDetails(profile);
        try {
            writeToFile(profileDetails, FILEPATH_PROFILE);
        } catch (UnableToWriteFileException e) {
            e.getMessage();
        }
    }

    /**
     * Saves the exercises into storage.
     * Used when there is an update to the list.
     *
     * @param exercises ExerciseList to be saved
     */
    public void saveExercises(ExerciseList exercises) {
        ArrayList<String> exerciseList = ENCODER.encodeExerciseList(exercises);
        try {
            writeToFile(exerciseList, FILEPATH_EXERCISE_LIST);
        } catch (UnableToWriteFileException e) {
            e.getMessage();
        }
    }

    /**
     * Saves the food items into storage.
     * Used when there is an update to the list.
     *
     * @param foodItems FoodList to be saved
     */
    public void saveFoodList(FoodList foodItems) {
        ArrayList<String> foodList = ENCODER.encodeFoodList(foodItems);
        try {
            writeToFile(foodList, FILEPATH_FOOD_LIST);
        } catch (UnableToWriteFileException e) {
            e.getMessage();
        }
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
