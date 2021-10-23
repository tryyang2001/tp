package seedu.duke.storage.lists.exerciselist;

import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.storage.Encoder;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExerciseListEncoder extends Encoder {

    /**
     * Encodes the list of exercises into an ArrayList of string in preparation for storage.
     *
     * @param exerciseList The list of exercises to be encoded
     * @return An ArrayList of the exercises to be stored
     */
    public ArrayList<String> encodeExerciseList(ExerciseList exerciseList) {
        for (int i = 0; i < exerciseList.getSize(); i++) {
            detailsToSave.add(exerciseList.getItem(i).toFileTextString());
        }
        return detailsToSave;
    }
}
