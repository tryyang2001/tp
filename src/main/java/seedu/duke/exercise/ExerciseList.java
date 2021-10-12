package seedu.duke.exercise;

import seedu.duke.ui.Ui;

import java.util.ArrayList;


public class ExerciseList {

    public static final String MESSAGE_EXERCISE_ADDED = "An exercise has been added:";
    private static final String MESSAGE_EXERCISE_DELETED = "You have removed the exercise:";
    private static final String MESSAGE_EMPTY_EXERCISE_LIST = "No exercise is found.";

    private ArrayList<Exercise> exerciseList = new ArrayList<>();

    /**
     * Returns the exercise list.
     *
     * @return Exercise list.
     */
    public ArrayList<Exercise> getExerciseList() {
        return exerciseList;
    }

    /**
     * Returns exercise item at a specific index in the exercise list.
     *
     * @param index Index of the exercise item.
     * @return exercise item in the exercise list.
     */
    public Exercise getExercise(int index) {
        return this.exerciseList.get(index);
    }

    /**
     * Returns the number of exercises in the exercise list.
     *
     * @return Number of exercises in the exercise list.
     */
    public int getSize() {
        return exerciseList.size();
    }

    /**
     * Adds an exercise item into the exercise list.
     *
     * @param exercise Exercise class object to be added.
     */
    public void addExercise(Exercise exercise) {
        this.exerciseList.add(exercise);
    }

    /**
     * Deletes an exercise item from the exercise list.
     *
     * @param index Index of the exercise to be deleted.
     */
    public Exercise deleteExercise(int index) {
        return exerciseList.remove(index);
    }

    /**
     * Returns exercise list in a string format.
     *
     * @return Exercise list in string format.
     */
    public String convertToString() {
        StringBuilder exerciseListToString = new StringBuilder();

        for (int i = 0; i < exerciseList.size(); i++) {
            exerciseListToString.append("\t").append(i + 1).append(". ").append(exerciseList.get(i)).append(Ui.LS);
        }
        return exerciseListToString.toString();
    }

    /**
     * Computes the sum of calorie of all exercises in exercise list.
     *
     * @return Integer value of the sum of calorie of all exercises.
     */
    public int getTotalCalories() {
        int sumOfExerciseCalorie = 0;

        for (Exercise exercise : exerciseList) {
            sumOfExerciseCalorie += exercise.getCalories();
        }
        return sumOfExerciseCalorie;
    }

    /**
     * Deletes all exercises in the exercise list.
     */
    public void clearExerciseList() {
        this.exerciseList.clear();
    }

}
