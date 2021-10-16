package seedu.duke.item.exercise;

import seedu.duke.item.Item;
import seedu.duke.item.ItemList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ExerciseList extends ItemList {

    protected ArrayList<Exercise> exerciseList = new ArrayList<>();

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
    @Override
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
     * Converts the entire exercise list to string format for printing purpose.
     *
     * @return The exercise list in a single string
     */
    @Override
    public String convertToString() {
        StringBuilder exerciseListInString = extractExerciseListByEachDate();
        return exerciseListInString.toString().stripTrailing();
    }

    private StringBuilder extractExerciseListByEachDate() {
        StringBuilder exerciseListInString = new StringBuilder(); //declares as StringBuilder for mutable String object
        for (int index = 0; index < exerciseList.size(); index++) {
            LocalDate currentDate = exerciseList.get(index).getDate();
            ExerciseList subList = new ExerciseList();
            while (index < exerciseList.size() && currentDate.isEqual(exerciseList.get(index).getDate())) {
                subList.addExercise(exerciseList.get(index++));
            }
            exerciseListInString
                    .append(String.format("You have done %d exercise(s) in %s (%s):",
                            subList.getSize(),
                            getDay(currentDate),
                            currentDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))))
                    .append(ItemList.LS);
            for (int i = 1; i <= subList.getSize(); i++) {
                exerciseListInString
                        .append(ItemList.TAB)
                        .append(String.format("%d. %s", i, subList.getExercise(i - 1)))
                        .append(ItemList.LS);
            }
            exerciseListInString
                    .append(String.format("Total calories burnt: %d cal",
                            this.getTotalCaloriesWithDate(currentDate)))
                    .append(ItemList.LS);
            if (index < exerciseList.size()) {
                exerciseListInString.append(ItemList.LS); //prevents last line spacing
            }
            index--;
        }
        return exerciseListInString;
    }

    /**
     * Converts the food list of a specific date to string format for printing purpose.
     *
     * @param date The date for the food list
     * @return The food list of the specific date in a single string
     */
    public String convertToStringByDate(LocalDate date) {
        StringBuilder exerciseListInString = extractExerciseListBySpecificDate(date);
        return exerciseListInString.toString().stripTrailing();
    }

    private StringBuilder extractExerciseListBySpecificDate(LocalDate date) {
        StringBuilder exerciseListInString = new StringBuilder(); //declares as StringBuilder for mutable String object
        ArrayList<Exercise> subList = (ArrayList<Exercise>) this.exerciseList.stream()
                .filter(e -> e.getDate().isEqual(date))
                .collect(Collectors.toList());
        exerciseListInString
                .append(String.format("You have consumed %d food item(s) in %s (%s):",
                        subList.size(),
                        getDay(date),
                        date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))))
                .append(ItemList.LS);
        for (int i = 1; i <= subList.size(); i++) {
            exerciseListInString
                    .append(ItemList.TAB)
                    .append(String.format("%d. %s", i, subList.get(i - 1)))
                    .append(ItemList.LS);
        }
        exerciseListInString
                .append(String.format("Total calories consumed: %d cal",
                        this.getTotalCaloriesWithDate(date)))
                .append(ItemList.LS);
        return exerciseListInString;
    }

    /**
     * Computes the sum of calorie of all exercises in exercise list.
     *
     * @return Integer value of the sum of calorie of all exercises.
     */
    @Override
    public int getTotalCalories() {
        int sumOfExerciseCalorie = 0;

        for (Exercise exercise : exerciseList) {
            sumOfExerciseCalorie += exercise.getCalories();
        }
        return sumOfExerciseCalorie;
    }

    /**
     * Computes the sum of calorie of all exercise done in a specific date.
     *
     * @param date The date to query all the exercises
     * @return Integer value of the sum of calorie of all exercises in the given date
     */
    public int getTotalCaloriesWithDate(LocalDate date) {
        return exerciseList.stream().filter(e -> e.getDate().isEqual(date)).mapToInt(Item::getCalories).sum();
    }

    /**
     * Deletes all exercises in the exercise list.
     */
    public void clearExerciseList() {
        this.exerciseList.clear();
    }

    /**
     * Sorts the exercise list in ascending format according to the date.
     */
    public void sortExerciseList() {
        this.exerciseList.sort(Comparator.comparing(Exercise::getDate));
    }

}
