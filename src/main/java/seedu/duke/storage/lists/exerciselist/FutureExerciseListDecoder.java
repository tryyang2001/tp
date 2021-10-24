package seedu.duke.storage.lists.exerciselist;

import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.storage.Decoder;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;

public class FutureExerciseListDecoder extends Decoder {

    /**
     * Retrieves future exercise list from future_list.txt.
     *
     * @return The exercise list with data loaded from file
     * @throws FileNotFoundException If file is misplaced/missing
     */
    public FutureExerciseList getFutureListFromData() throws FileNotFoundException {
        FutureExerciseList exercises = new FutureExerciseList();
        File file = new File(FutureExerciseListStorage.FILEPATH_LIST_FUTURE);
        Scanner in = new Scanner(file);
        logger.log(Level.FINE, "Decoding exercise data from file...");
        while (in.hasNext()) {
            try {
                decodeFutureExerciseDataFromString(exercises, in.nextLine());
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        logger.log(Level.FINE, "Retrieved exercise data from file.");
        return exercises;
    }

    private void decodeFutureExerciseDataFromString(FutureExerciseList exercises,
                                                    String line) throws InvalidDataException {
        try {
            final String[] exerciseDetails = line.split(FILE_TEXT_DELIMITER);
            final String name = exerciseDetails[1];
            final int calories = Integer.parseInt(exerciseDetails[2]);
            final LocalDate dateOfExercise = parseDate(exerciseDetails[3]);
            exercises.addItem(new Exercise(name, calories, dateOfExercise));
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            logger.log(Level.WARNING, "A line in exercise list is not valid.", line);
            throw new InvalidDataException(FutureExerciseListStorage.FILENAME_LIST_FUTURE, line);
        }
    }
}
