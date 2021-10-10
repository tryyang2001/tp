package seedu.duke.storage;

import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

//TODO Add JavaDocs to public methods and class

public class Storage {
    private static final String FILEPATH = "./data";
    private static final String FILEPATH_PROFILE = FILEPATH + "/profile.txt";
    private static final String FILEPATH_FOOD_LIST = FILEPATH + "/food_list.txt";
    private static final String FILEPATH_EXERCISE_LIST = FILEPATH + "/exercise_list.txt";

    private static final String MESSAGE_CREATE_PROFILE = "No profile detected!"
            + "A new profile has been created successfully.";
    private static final String MESSAGE_CREATE_FOOD_LIST = "No food items detected!"
            + "A new food list has been created successfully.";
    private static final String MESSAGE_CREATE_EXERCISE_LIST = "No exercises detected!"
            + "A new exercise has been created successfully.";

    private final Encoder ENCODER = new Encoder();

    public Storage() {

    }

    public void loadFiles() {
        try {
            loadProfileFile();
            loadFoodListFile();
            loadExerciseListFile();
        } catch (UnableToReadFileException e) {
            e.getMessage();
            return;
        }
    }

    public Profile loadProfileFile() throws UnableToReadFileException {
        File profileFile = new File(FILEPATH_PROFILE);
        checkForFile(profileFile, FILEPATH_PROFILE);
        //TODO Decode from profile.txt
        Profile profile;
        return profile;
    }

    public ExerciseList loadExerciseListFile() throws UnableToReadFileException {
        File profileFile = new File(FILEPATH_PROFILE);
        checkForFile(profileFile, FILEPATH_PROFILE);
        //TODO Decode from exercise_list.txt
        ExerciseList exercises;
        return exercises;
    }

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
        case "profile.txt":
            System.out.println(MESSAGE_CREATE_PROFILE);
            break;
        case "exercise_list.txt":
            System.out.println(MESSAGE_CREATE_EXERCISE_LIST);
            break;
        case "food_list.txt":
            System.out.println(MESSAGE_CREATE_FOOD_LIST);
            break;
        default:
            break;
        }
    }

    //TODO Import class Profile, ExerciseList, FoodList
    public void saveAll(Profile p, ExerciseList e, FoodList f) {
        saveProfile(p);
        saveExercises(e);
        saveFoodList(f);
    }

    public void saveProfile(Profile profile) {
        ArrayList<String> profileDetails = ENCODER.encodeProfileDetails(profile);
        try {
            writeToFile(profileDetails, FILEPATH_PROFILE);
        } catch (UnableToWriteFileException e) {
            e.getMessage();
        }
    }

    public void saveExercises(ExerciseList exercises) {
        ArrayList<String> exerciseList = ENCODER.encodeExerciseList(exercises);
        try {
            writeToFile(exerciseList, FILEPATH_EXERCISE_LIST);
        } catch (UnableToWriteFileException e) {
            e.getMessage();
        }
    }

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
