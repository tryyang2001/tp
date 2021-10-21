package seedu.duke.storage.lists.foodlist;

import seedu.duke.item.food.FoodList;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;

public class FoodListStorage extends Storage {

    public static final String FILENAME_LIST_FOOD = "food_list.txt";
    public static final String FILEPATH_LIST_FOOD = FILEPATH + FILENAME_LIST_FOOD;

    private FoodListDecoder decoder = new FoodListDecoder();
    private FoodListEncoder encoder = new FoodListEncoder();

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

    private FoodList readFromFoodListFile() throws UnableToReadFileException {
        try {
            return decoder.getFoodListFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_LIST_FOOD);
            throw new UnableToReadFileException(FILEPATH_LIST_FOOD);
        }
    }

    public void saveFoodList(FoodList foodItems) throws UnableToWriteFileException {
        ArrayList<String> foodList = encoder.encodeFoodList(foodItems);
        writeToFile(foodList, FILEPATH_LIST_FOOD);
    }

}
