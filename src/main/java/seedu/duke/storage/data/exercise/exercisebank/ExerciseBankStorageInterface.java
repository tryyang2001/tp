package seedu.duke.storage.data.exercise.exercisebank;

import seedu.duke.data.item.ItemBank;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

public interface ExerciseBankStorageInterface {
    ItemBank loadExerciseBank() throws UnableToReadFileException;
    void saveExerciseBank(ItemBank exerciseBank) throws UnableToWriteFileException;
}
