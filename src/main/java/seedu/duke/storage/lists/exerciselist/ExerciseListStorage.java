package seedu.duke.storage.lists.exerciselist;

import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;

public class ExerciseListStorage extends Storage {

    public static final String FILENAME_LIST_EXERCISE = "exercise_list.txt";
    public static final String FILEPATH_LIST_EXERCISE = FILEPATH + FILENAME_LIST_EXERCISE;

    private ExerciseListDecoder decoder = new ExerciseListDecoder();
    private ExerciseListEncoder encoder = new ExerciseListEncoder();

    /**
     * Load exercises into an ExerciseList object.
     * Used when the selected profile is accessed and its respective ExerciseList is loaded.
     *
     * @return ExerciseList object with the details from the storage file
     * @throws UnableToReadFileException If the file is inaccessible or due to environment variables
     */
    public ExerciseList loadExerciseListFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_LIST_EXERCISE);
        return readFromExerciseListFile();
    }

    private ExerciseList readFromExerciseListFile() throws UnableToReadFileException {
        try {
            return decoder.getExerciseListFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_LIST_EXERCISE);
            throw new UnableToReadFileException(FILEPATH_LIST_EXERCISE);
        }
    }

    /**
     * Saves the exercises into storage.
     * Used when there is an update to the list.
     *
     * @param exercises ExerciseList to be saved
     */
    public void saveExercises(ExerciseList exercises) throws UnableToWriteFileException {
        ArrayList<String> exerciseList = encoder.encodeExerciseList(exercises);
        writeToFile(exerciseList, FILEPATH_LIST_EXERCISE);
    }
}
