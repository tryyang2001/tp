package seedu.duke.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ExerciseListTest {

    @Test
    void addExercise_exerciseClassParameter_expectAddInList() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addExercise(new Exercise("Jumping Jacks", 100));
        assertEquals("Jumping Jacks (100 cal)", exerciseList.getExercise(0).toString());
    }

    @Test
    void deleteExercise_exerciseIndex_expectDeleteFromList() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addExercise(new Exercise("Running", 250));
        exerciseList.addExercise(new Exercise("Jumping Jacks", 100));
        exerciseList.deleteExercise(0);
        assertEquals("Jumping Jacks (100 cal)", exerciseList.getExercise(0).toString());
    }

    @Test
    void deleteExercise_exerciseIndex_expectCorrectNumberOfTasksLeft() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addExercise(new Exercise("Running", 250));
        exerciseList.addExercise(new Exercise("Jumping Jacks", 100));
        exerciseList.deleteExercise(0);
        assertEquals(1, exerciseList.getSize());
    }

    @Test
    void deleteExercise_invalidIndex_expectException() {
        ExerciseList exerciseList = new ExerciseList();
        assertThrows(IndexOutOfBoundsException.class, () -> exerciseList.deleteExercise(0));
    }

    @Test
    void totalCalorie_someExercises_expectTotalCalorie() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addExercise(new Exercise("Running", 250));
        exerciseList.addExercise(new Exercise("Jumping Jacks", 100));
        exerciseList.addExercise(new Exercise("Skipping", 200));
        exerciseList.addExercise(new Exercise("Swimming", 300));
        assertEquals(850, exerciseList.totalCalorie());
    }
}
