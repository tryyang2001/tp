package seedu.duke.storage.data.food.foodlist;

import seedu.duke.data.item.food.Food;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.data.ListDecoder;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Decodes the food list from the storage file.
 */
public class FoodListDecoder extends ListDecoder {

    /**
     * Retrieves food list from food_list.txt.
     *
     * @return The food list with data loaded from file
     * @throws FileNotFoundException If file is misplaced/missing
     */
    public static FoodList retrieveFoodListFromData(String filePath) throws FileNotFoundException {
        FoodList foodItems = new FoodList();
        File file = new File(filePath);
        Scanner in = new Scanner(file);
        decodeFoodItems(foodItems, in);
        return foodItems;
    }

    private static void decodeFoodItems(FoodList foodItems, Scanner in) {
        while (in.hasNext()) {
            try {
                decodeFoodDataFromString(foodItems, in.nextLine());
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void decodeFoodDataFromString(FoodList foodItems, String line) throws InvalidDataException {
        try {
            final String[] foodDetails = line.split(StorageManager.FILE_TEXT_DELIMITER);
            final String name = foodDetails[1];
            final int calories = Integer.parseInt(foodDetails[2]);
            final LocalDateTime dateTimeOfFood = parseDateTime(foodDetails[3]);
            final Food food = new Food(name, calories, dateTimeOfFood);
            if (!food.isValid()) {
                throw new InvalidCharacteristicException(line);
            }
            foodItems.addItem(new Food(name, calories, dateTimeOfFood));
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException
                | DateTimeException | InvalidCharacteristicException e) {
            throw new InvalidDataException(StorageManager.FILENAME_LIST_FOOD, line);
        }
    }
}
