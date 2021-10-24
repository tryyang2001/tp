package seedu.duke.storage.data.exercise.exercisebank;

import seedu.duke.data.item.ItemBank;
import seedu.duke.storage.Storage;
import seedu.duke.storage.data.ItemBankDecoder;
import seedu.duke.storage.data.ItemEncoder;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.utilities.FileChecker;
import seedu.duke.storage.utilities.FileSaver;

import java.io.FileNotFoundException;
import java.util.logging.Level;

public class ExerciseBankStorage extends Storage implements ExerciseBankStorageInterface {

    public static final String TYPE = "Exercise";

    public ExerciseBankStorage(String filePath) {
        this.filePath = filePath;
        fileName = getFileName(filePath);
    }

    public ItemBank loadExerciseBank() throws UnableToReadFileException {
        FileChecker.createFileIfMissing(filePath);
        return readFromExerciseBankFile();
    }

    private ItemBank readFromExerciseBankFile() throws UnableToReadFileException {
        try {
            return ItemBankDecoder.retrieveDataFromItemBank(filePath, TYPE);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", filePath);
            throw new UnableToReadFileException(filePath);
        }
    }

    public void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException {
        FileSaver.saveToFile(filePath, ItemEncoder.encode(exerciseBank));
    }
}
