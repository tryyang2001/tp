package seedu.duke.item.exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class FutureExerciseList extends ExerciseList {

    protected ArrayList<Exercise> futureExerciseList = new ArrayList<>();

    /**
     * Returns the future exercise list.
     *
     * @return Future exercise list.
     */
    public ArrayList<Exercise> getFutureExerciseList() {
        return futureExerciseList;
    }

    /**
     * Returns exercise item as a specific index in the future exercise list.
     *
     * @param index Index of the exercise item.
     * @return exercise item in the future exercise list.
     */
    public Exercise getFutureExercise(int index) {
        return this.futureExerciseList.get(index);
    }

    /**
     * Adds an exercise item into the future exercise item and sort by date in an ascending order.
     *
     * @param exercise Exercise class object to be added.
     */
    public void addFutureExercise(Exercise exercise) {
        this.futureExerciseList.add(exercise);
        sortFutureExerciseList();
    }

    /**
     * Deletes and exercise item from the future exercise list.
     *
     * @param index Index of the exercise to be deleted.
     * @return Exercise object removed.
     */
    public Exercise deleteFutureExercise(int index) {
        return futureExerciseList.remove(index);
    }

    /**
     * Deletes all exercises in the future exercise list.
     */
    public void clearFutureExerciseList() {
        this.futureExerciseList.clear();
    }

    /**
     * Converts the entire future exercise list to string format for printing purpose.
     *
     * @return The future exercise list in a single string.
     */
    @Override
    public String convertToString() {
        StringBuilder futureExerciseListToString = new StringBuilder();

        for (int i = 0; i < futureExerciseList.size(); i++) {
            futureExerciseListToString
                    .append(TAB)
                    .append(i + 1)
                    .append(". ")
                    .append(futureExerciseList.get(i))
                    .append(" (")
                    .append(getDay(futureExerciseList.get(i).getDate()))
                    .append(" ")
                    .append(futureExerciseList.get(i).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                    .append(")")
                    .append(LS);
        }
        return futureExerciseListToString.toString().stripTrailing();
    }

    /**
     * Returns the number of exercises in the future exercise list.
     *
     * @return Number of exercises in the future exercise list.
     */
    @Override
    public int getSize() {
        return futureExerciseList.size();
    }

    /**
     * Computes the sum of calorie of all exercises in the future exercise list.
     *
     * @return Integer value of the sum of calorie of all exercises in the future exercise list.
     */
    @Override
    public int getTotalCalories() {
        int sumOfExerciseCalorie = 0;

        for (Exercise exercise : futureExerciseList) {
            sumOfExerciseCalorie += exercise.getCalories();
        }
        return sumOfExerciseCalorie;
    }

    /**
     * Sorts the future exercise list in ascending format according to the date.
     *
     */
    public void sortFutureExerciseList() {
        this.futureExerciseList.sort(Comparator.comparing(Exercise::getDate));
    }

}
