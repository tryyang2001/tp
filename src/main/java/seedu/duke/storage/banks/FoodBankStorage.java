package seedu.duke.storage.banks;

import seedu.duke.item.ItemBank;
import seedu.duke.storage.StorageNew;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;

public class FoodBankStorage extends StorageNew {

    public static final String FILENAME_BANK_FOOD = "food_bank.txt";
    public static final String FILEPATH_BANK_FOOD = FILEPATH + FILENAME_BANK_FOOD;

    private BankEncoder encoder = new BankEncoder();
    private FoodBankDecoder decoder = new FoodBankDecoder();

    public ItemBank loadFoodBankFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_BANK_FOOD);
        return readFromFoodBankFile();
    }

    private ItemBank readFromFoodBankFile() throws UnableToReadFileException {
        try {
            return decoder.getFoodBankFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_BANK_FOOD);
            throw new UnableToReadFileException(FILEPATH_BANK_FOOD);
        }
    }

    public void saveFoodBank(ItemBank foodBank) throws UnableToWriteFileException {
        ArrayList<String> foodBankList = encoder.encodeItemBank(foodBank);
        writeToFile(foodBankList, FILEPATH_BANK_FOOD);
    }

}
