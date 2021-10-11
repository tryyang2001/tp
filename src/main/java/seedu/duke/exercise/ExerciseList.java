package seedu.duke.exercise;

import java.util.ArrayList;

public class ExerciseList {

    public static final String MESSAGE_EXERCISE_ADDED = "An exercise has been added:";
    private static final String MESSAGE_EXERCISE_DELETED = "You have removed the exercise:";
    private static final String MESSAGE_EMPTY_EXERCISE_LIST = "No exercise is found.";
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

        System.out.println(MESSAGE_EXERCISE_ADDED);
        System.out.println("\t" + exercise);
    }

    /**
     * Deletes an exercise item from the exercise list.
     *
     * @param index Index of the exercise to be deleted.
     */
    public void deleteExercise(int index) {
        Exercise deletedExercise = exerciseList.remove(index);

        System.out.println(MESSAGE_EXERCISE_DELETED);
        System.out.println("\t" + deletedExercise);
        if (exerciseList.size() == 1) {
            System.out.println("There is" + exerciseList.size() + "task left.");
        } else {
            System.out.println("There are" + exerciseList.size() + "tasks left.");
        }
    }

    /**
     * Prints out all the exercises in the exercise list.
     */
    public void printExerciseList() {
        if (exerciseList.size() == 0) {
            System.out.println(MESSAGE_EMPTY_EXERCISE_LIST);
        } else if (exerciseList.size() == 1) {
            System.out.println("You have done 1 exercise:");
            System.out.println("\t" + exerciseList.get(0));
        } else {
            System.out.println("You have done " + exerciseList.size() + " exercises:");
            for (int i = 0; i < exerciseList.size(); i++) {
                System.out.println("\t" + i + 1 + ". " + exerciseList.get(i));
            }
        }
    }

    /**
     * Returns exercise list in a string format.
     *
     * @return Exercise list in string format.
     */
    public String convertExerciseListToString() {
        StringBuilder exerciseListToString;

        if (exerciseList.size() == 0) {
            exerciseListToString = new StringBuilder(MESSAGE_EMPTY_EXERCISE_LIST + LS);
        } else if (exerciseList.size() == 1) {
            exerciseListToString = new StringBuilder("You have done 1 exercise:" + LS);
        } else {
            exerciseListToString = new StringBuilder("You have done " + exerciseList.size() + " exercises:" + LS);
        }
        for (int i = 0; i < exerciseList.size(); i++) {
            exerciseListToString.append("\t").append(i+1).append(". ").append(exerciseList.get(i)).append(LS);
        }
        return exerciseListToString.toString();
    }
}
