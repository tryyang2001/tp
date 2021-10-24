package seedu.duke.storage.data.exercise.futurelist;

import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

public interface UpcomingStorageInterface {
    FutureExerciseList loadFutureExerciseList() throws UnableToReadFileException;
    void saveFutureExerciseList(FutureExerciseList futureExercises) throws UnableToWriteFileException;
}
