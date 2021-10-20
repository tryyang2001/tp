package seedu.duke.storage.banks;

import seedu.duke.item.ItemBank;
import seedu.duke.item.food.Food;
import seedu.duke.storage.Decoder;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;

public class ExerciseBankDecoder extends Decoder {

    public ItemBank getExerciseBankFromData() throws FileNotFoundException {
        ItemBank items = new ItemBank();
        File file = new File(ExerciseBankStorage.FILEPATH_BANK_EXERCISE);
        Scanner in = new Scanner(file);
        logger.log(Level.FINE, "Decoding item bank data from file...");
        while (in.hasNext()) {
            try {
                decodeExerciseBankDataFromString(items, in.nextLine());
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        logger.log(Level.FINE, "Retrieved item bank data from file.");
        return items;
    }

    private void decodeExerciseBankDataFromString(ItemBank items, String line) throws InvalidDataException {
        try {
            final String[] itemDetails = line.split(FILE_TEXT_DELIMITER);
            final String name = itemDetails[1];
            final int calories = Integer.parseInt(itemDetails[2]);
            items.addItem(new Food(name, calories));
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            logger.log(Level.WARNING, "A line in item bank is not valid.", line);
            throw new InvalidDataException(ExerciseBankStorage.FILENAME_BANK_EXERCISE, line);
        }
    }
}
