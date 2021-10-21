package seedu.duke.storage.banks;

import seedu.duke.item.bank.ItemBank;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;

public class ExerciseBankStorage extends Storage {

    public static final String FILENAME_BANK_EXERCISE = "exercise_bank.txt";
    public static final String FILEPATH_BANK_EXERCISE = FILEPATH + FILENAME_BANK_EXERCISE;

    private ExerciseBankDecoder decoder = new ExerciseBankDecoder();
    private BankEncoder encoder = new BankEncoder();

    public ItemBank loadExerciseBankFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_BANK_EXERCISE);
        return readFromExerciseBankFile();
    }

    private ItemBank readFromExerciseBankFile() throws UnableToReadFileException {
        try {
            return new ExerciseBankDecoder().getExerciseBankFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_BANK_EXERCISE);
            throw new UnableToReadFileException(FILEPATH_BANK_EXERCISE);
        }
    }

    public void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException {
        ArrayList<String> exerciseBankList = new BankEncoder().encodeItemBank(exerciseBank);
        writeToFile(exerciseBankList, FILEPATH_BANK_EXERCISE);
    }
}
