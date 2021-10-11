package seedu.duke.exercise;

import java.util.ArrayList;

public class ExerciseList {

    private static final String LS = System.lineSeparator();

    private ArrayList<Exercise> exerciseList = new ArrayList<>();

    /**
     * Returns the exercise list
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
        StringBuilder exerciseListToString;

        if (exerciseList.size() == 1) {
            exerciseListToString = new StringBuilder("You have done 1 exercise:" + LS);
        } else {
            exerciseListToString = new StringBuilder("You have done " + exerciseList.size() + " exercises:" + LS);
        }
        for (int i = 0; i < exerciseList.size(); i++) {
            exerciseListToString.append("\t").append(i+1).append(". ").append(exerciseList.get(i)).append(LS);
        }
        return exerciseListToString.toString();
    }

    /**
     * Computes the sum of calorie of all exercises in exercise list.
     *
     * @return Integer value of the sum of calorie of all exercises.
     */
    public int totalCalorie(){
        int sumOfExerciseCalorie = 0;

        for (Exercise exercise : exerciseList) {
            sumOfExerciseCalorie += exercise.getCalories();
        }
        return sumOfExerciseCalorie;
    }

}
