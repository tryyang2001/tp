package seedu.duke.item.exercise;

import seedu.duke.item.Item;
import seedu.duke.item.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ExerciseList extends ItemList {
    public static final String MESSAGE_EXERCISE_DONE = "You have done %d exercise(s) in %s (%s):";
    public static final String MESSAGE_TOTAL_CALORIE_BURNT = "Total calories burnt: %d cal";

    /**
     * Default constructor for exercise list.
     */
    public ExerciseList() {
        itemList = new ArrayList<>();
    }

    /**
     * Deletes an exercise item from the exercise list.
     *
     * @param index Index of the exercise to be deleted.
     */
    //TODO: remove this method after changing code in DeleteExerciseCommand.
    public Exercise deleteItem(int index) {
        return (Exercise) itemList.remove(index);
    }

    public Exercise deleteItem(int index, LocalDate date) {
        assert index >= 0 && index < itemList.size() : "Index cannot be out of bound";
        Exercise deletedExercise = new Exercise("", 1, date);
        int actualIndex = getActualIndex(index, deletedExercise);
        deletedExercise = (Exercise) itemList.remove(actualIndex);
        return deletedExercise;
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

    /**
     * Converts the food list of a specific date to string format for printing purpose.
     *
     * @param date The date for the food list
     * @return The food list of the specific date in a single string
     */
    public String convertToStringBySpecificDate(LocalDate date) {
        StringBuilder exerciseListInString = extractExerciseListBySpecificDate(date);
        return exerciseListInString.toString().stripTrailing();
    }

    /**
     * Sorts the exercise list in ascending format according to the date.
     */
    public void sortList() {
        this.itemList.sort(Comparator.comparing(Item::getDate));
    }

    private int getActualIndex(int index, Item deletedExercise) {
        for (int i = 0; i < itemList.size(); i++) {
            if (isListToQuery(deletedExercise, i)) {
                if (isExerciseToDelete(deletedExercise, i, index)) {
                    return i + index;
                }
                break;
            }
        }
        return -1;
    }

    private boolean isExerciseToDelete(Item deletedExercise, int currentIndex, int index) {
        return itemList.get(currentIndex + index).getDate().equals(deletedExercise.getDate());
    }

    private boolean isListToQuery(Item deletedExercise, int currentIndex) {
        return itemList.get(currentIndex).getDate().equals(deletedExercise.getDate());
    }

    /**
     * Helper function used in convertToString to extract exercises list
     * according to each date presented in the entire exercise list.
     *
     * @return String which contains exercise lists with different date
     */
    private StringBuilder extractExerciseListByEachDate() {
        StringBuilder exerciseListInString = new StringBuilder();
        for (int index = 0; index < itemList.size(); index++) {
            LocalDate currentDate = itemList.get(index).getDate();
            ExerciseList subList = new ExerciseList();
            while (index < itemList.size() && currentDate.isEqual(itemList.get(index).getDate())) {
                subList.addItem(itemList.get(index++));
            }
            convertItemCountToString(exerciseListInString, subList.getSize(), currentDate, MESSAGE_EXERCISE_DONE);
            for (int i = 1; i <= subList.getSize(); i++) {
                convertItemToString(exerciseListInString, i, subList.getItem(i - 1));
            }
            convertTotalCaloriesToString(
                    exerciseListInString,
                    this.getTotalCaloriesWithDate(currentDate),
                    MESSAGE_TOTAL_CALORIE_BURNT);
            if (index < itemList.size()) {
                exerciseListInString.append(ItemList.LS); //prevents last line spacing
            }
            index--;
        }
        return exerciseListInString;
    }

    /**
     * Helper method used in convertToStringBySpecificDate for extracting
     * exercise list which contains all the exercises done on the date.
     *
     * @param date The date to query all the exercises done
     * @return StringBuilder type string which contains an exercise list with the given date
     */
    private StringBuilder extractExerciseListBySpecificDate(LocalDate date) {
        StringBuilder exerciseListInString = new StringBuilder();
        ArrayList<Item> subList = (ArrayList<Item>) this.itemList.stream()
                .filter(e -> e.getDate().isEqual(date))
                .collect(Collectors.toList());
        convertItemCountToString(exerciseListInString, subList.size(), date, MESSAGE_EXERCISE_DONE);
        for (int i = 1; i <= subList.size(); i++) {
            convertItemToString(exerciseListInString, i, subList.get(i - 1));
        }
        convertTotalCaloriesToString(
                exerciseListInString,
                this.getTotalCaloriesWithDate(date),
                MESSAGE_TOTAL_CALORIE_BURNT);
        return exerciseListInString;
    }
}
