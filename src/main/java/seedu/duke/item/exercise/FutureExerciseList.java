package seedu.duke.item.exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class FutureExerciseList extends ExerciseList {


    protected ArrayList<Exercise> futureExerciseList = new ArrayList<>();

    public ArrayList<Exercise> getFutureExerciseList() {
        return futureExerciseList;
    }

    public Exercise getFutureExercise(int index) {
        return this.futureExerciseList.get(index);
    }

    public void addFutureExercise(Exercise exercise) {
        this.futureExerciseList.add(exercise);
        sortFutureExerciseList();
    }

    public Exercise deleteFutureExercise(int index) {
        return futureExerciseList.remove(index);
    }

    public void clearFutureExerciseList() {
        this.futureExerciseList.clear();
    }


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

    @Override
    public int getSize() {
        return futureExerciseList.size();
    }

    @Override
    public int getTotalCalories() {
        int sumOfExerciseCalorie = 0;

        for (Exercise exercise : futureExerciseList) {
            sumOfExerciseCalorie += exercise.getCalories();
        }
        return sumOfExerciseCalorie;
    }

    public void sortFutureExerciseList() {
        this.futureExerciseList.sort(Comparator.comparing(Exercise::getDate));
    }

}
