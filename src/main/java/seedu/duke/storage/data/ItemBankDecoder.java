package seedu.duke.storage.data;

import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exceptions.DuplicateItemInBankException;
import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.food.Food;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.data.exercise.exercisebank.ExerciseBankStorage;
import seedu.duke.storage.data.food.foodbank.FoodBankStorage;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Decodes the ItemBanks from their respective data storage files.
 */
public class ItemBankDecoder {

    /**
     * Retrieves data from ItemBank storage.
     *
     * @param filePath path of item bank to be retrieved
     * @param type     type of ItemBank to retrieve to
     * @return the items that have been successfully loaded from the filepath
     * @throws FileNotFoundException If the filepath provided is invalid
     */
    public static ItemBank retrieveDataFromItemBank(String filePath,
                                                    String type) throws FileNotFoundException {
        ItemBank items = new ItemBank();
        File file = new File(filePath);
        Scanner in = new Scanner(file);
        decodeItems(type, items, in);
        return items;
    }

    private static void decodeItems(String type, ItemBank items, Scanner in) {
        while (in.hasNext()) {
            try {
                decodeFoodBankDataFromString(items, in.nextLine(), type);
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void decodeFoodBankDataFromString(ItemBank items, String line,
                                                     String type) throws InvalidDataException {
        try {
            final String[] itemDetails = line.split(StorageManager.FILE_TEXT_DELIMITER);
            final String name = itemDetails[1];
            final int calories = Integer.parseInt(itemDetails[2]);
            addToRespectiveBank(items, type, name, calories);
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException
                | DuplicateItemInBankException e) {
            throw new InvalidDataException(StorageManager.FILENAME_BANK_FOOD, line);
        }
    }

    private static void addToRespectiveBank(ItemBank items, String type,
                                            String name, int calories) throws DuplicateItemInBankException {
        if (isFoodType(type)) {
            items.addItem(new Food(name, calories));
        } else if (isExerciseType(type)) {
            items.addItem(new Exercise(name, calories));
        }
    }

    private static boolean isExerciseType(String type) {
        return type.equals(ExerciseBankStorage.TYPE);
    }

    private static boolean isFoodType(String type) {
        return type.equals(FoodBankStorage.TYPE);
    }
}
