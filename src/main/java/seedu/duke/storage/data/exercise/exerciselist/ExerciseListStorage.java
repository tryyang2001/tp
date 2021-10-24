package seedu.duke.storage.data.exercise.exerciselist;

import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.storage.Storage;
import seedu.duke.storage.data.ItemEncoder;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.utilities.FileChecker;
import seedu.duke.storage.utilities.FileSaver;

import java.io.FileNotFoundException;
import java.util.logging.Level;

public class ExerciseListStorage extends Storage implements ExerciseStorageInterface {

    public ExerciseListStorage(String filePath) {
        this.filePath = filePath;
        this.fileName = getFileName(filePath);
    }

    /**
     * Load exercises into an ExerciseList object.
     * Used when the selected profile is accessed and its respective ExerciseList is loaded.
     *
     * @return ExerciseList object with the details from the storage file
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    public ExerciseList loadExerciseList() throws UnableToReadFileException {
        FileChecker.createFileIfMissing(filePath);
        return readFromExerciseListFile();
    }

    private ExerciseList readFromExerciseListFile() throws UnableToReadFileException {
        try {
            return ExerciseListDecoder.retrieveExerciseListFromData(filePath);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", filePath);
            throw new UnableToReadFileException(filePath);
        }
    }

    /**
     * Saves the exercises into storage.
     * Used when there is an update to the list.
     *
     * @param exercises ExerciseList to be saved
     */
    public void saveExerciseList(ExerciseList exercises) throws UnableToWriteFileException {
        FileSaver.saveToFile(filePath, ItemEncoder.encode(exercises));
    }
}
