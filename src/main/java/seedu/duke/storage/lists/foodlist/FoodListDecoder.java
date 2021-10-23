package seedu.duke.storage.lists.foodlist;

import seedu.duke.item.food.Food;
import seedu.duke.item.food.FoodList;
import seedu.duke.storage.Decoder;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Level;

public class FoodListDecoder extends Decoder {

    /**
     * Retrieves food list from food_list.txt.
     *
     * @return The food list with data loaded from file
     * @throws FileNotFoundException If file is misplaced/missing
     */

    public FoodList getFoodListFromData() throws FileNotFoundException {
        FoodList foodItems = new FoodList();
        File file = new File(FoodListStorage.FILEPATH_LIST_FOOD);
        Scanner in = new Scanner(file);
        logger.log(Level.FINE, "Decoding food list data from file...");
        while (in.hasNext()) {
            try {
                decodeFoodDataFromString(foodItems, in.nextLine());
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        logger.log(Level.FINE, "Retrieved food list data from file.");
        return foodItems;
    }

    private void decodeFoodDataFromString(FoodList foodItems, String line) throws InvalidDataException {
        try {
            final String[] foodDetails = line.split(FILE_TEXT_DELIMITER);
            final String name = foodDetails[1];
            final int calories = Integer.parseInt(foodDetails[2]);
            final LocalDateTime dateTimeOfFood = parseDateTime(foodDetails[3]);
            final LocalDate currentDate = LocalDate.now();
            final LocalDate dateOfFood = dateTimeOfFood.toLocalDate();
            if (isWithinPastSevenDays(currentDate, dateOfFood)) {
                foodItems.addItem(new Food(name, calories, dateTimeOfFood));
            }
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException | DateTimeException e) {
            logger.log(Level.WARNING, "A line in food list is not valid.", line);
            throw new InvalidDataException(FoodListStorage.FILENAME_LIST_FOOD, line);
        }
    }

    private boolean isWithinPastSevenDays(LocalDate currentDate, LocalDate dateOfFood) {
        boolean isEqualOrBeforeTodayDate = dateOfFood.isEqual(currentDate) || dateOfFood.isBefore(currentDate);
        boolean isAfterSevenDaysAgo = dateOfFood.isAfter(currentDate.minusDays(8));
        return isEqualOrBeforeTodayDate && isAfterSevenDaysAgo;
    }
}
