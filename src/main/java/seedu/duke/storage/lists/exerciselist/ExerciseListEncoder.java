package seedu.duke.storage.lists.exerciselist;

import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.storage.EncoderNew;

import java.util.ArrayList;

public class ExerciseListEncoder extends EncoderNew {

    /**
     * Encodes the list of exercises into an ArrayList of string in preparation for storage.
     *
     * @param exerciseList The list of exercises to be encoded
     * @return An ArrayList of the exercises to be stored
     */
    public ArrayList<String> encodeExerciseList(ExerciseList exerciseList) {
        ArrayList<String> exercises = new ArrayList<>();
        for (int i = 0; i < exerciseList.getSize(); i++) {
            detailsToSave.add(exerciseList.getExercise(i).toFileTextString());
        }
        return detailsToSave;
    }
}
