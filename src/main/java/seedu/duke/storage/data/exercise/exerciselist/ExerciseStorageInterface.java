package seedu.duke.storage.data.exercise.exerciselist;

import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

public interface ExerciseStorageInterface {

    ExerciseList loadExerciseList() throws UnableToReadFileException;

    void saveExerciseList(ExerciseList exercises) throws UnableToWriteFileException;

}
