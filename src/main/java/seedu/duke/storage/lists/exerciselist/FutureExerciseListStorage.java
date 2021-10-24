package seedu.duke.storage.lists.exerciselist;

import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;

public class FutureExerciseListStorage extends Storage {

    public static final String FILENAME_LIST_FUTURE = "future_list.txt";
    public static final String FILEPATH_LIST_FUTURE = FILEPATH + FILENAME_LIST_FUTURE;

    public FutureExerciseList loadFutureExerciseListFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_LIST_FUTURE);
        return readFromFutureListFile();
    }

    private FutureExerciseList readFromFutureListFile() throws UnableToReadFileException {
        try {
            return new FutureExerciseListDecoder().getFutureListFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_LIST_FUTURE);
            throw new UnableToReadFileException(FILEPATH_LIST_FUTURE);
        }
    }

    public void saveFutureList(FutureExerciseList futureExercises) throws UnableToWriteFileException {
        ArrayList<String> futureExerciseList = new ExerciseListEncoder().encodeExerciseList(futureExercises);
        writeToFile(futureExerciseList, FILEPATH_LIST_FUTURE);
    }

}
