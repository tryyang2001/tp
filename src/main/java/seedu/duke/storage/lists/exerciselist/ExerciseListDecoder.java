package seedu.duke.storage.lists.exerciselist;

import seedu.duke.item.exercise.Exercise;
import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.storage.Decoder;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;

public class ExerciseListDecoder extends Decoder {

    /**
     * Retrieves exercise list from exercise_list.txt.
     *
     * @return The exercise list with data loaded from file
     * @throws FileNotFoundException If file is misplaced/missing
     */
    public ExerciseList getExerciseListFromData() throws FileNotFoundException {
        ExerciseList exercises = new ExerciseList();
        File file = new File(ExerciseListStorage.FILEPATH_LIST_EXERCISE);
        Scanner in = new Scanner(file);
        logger.log(Level.FINE, "Decoding exercise data from file...");
        while (in.hasNext()) {
            try {
                decodeExerciseDataFromString(exercises, in.nextLine());
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        logger.log(Level.FINE, "Retrieved exercise data from file.");
        return exercises;
    }

    private void decodeExerciseDataFromString(ExerciseList exercises, String line) throws InvalidDataException {
        try {
            final String[] exerciseDetails = line.split(FILE_TEXT_DELIMITER);
            final String name = exerciseDetails[1];
            final int calories = Integer.parseInt(exerciseDetails[2]);
            final LocalDate dateOfExercise = parseDate(exerciseDetails[3]);
            exercises.addItem(new Exercise(name, calories, dateOfExercise));
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            logger.log(Level.WARNING, "A line in exercise list is not valid.", line);
            throw new InvalidDataException(ExerciseListStorage.FILENAME_LIST_EXERCISE, line);
        }
    }

}
