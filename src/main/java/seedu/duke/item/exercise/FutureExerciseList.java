package seedu.duke.item.exercise;

import seedu.duke.item.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class FutureExerciseList extends ExerciseList {

    private static final int ONE_DAY = 1;
    private static final int ONE_WEEK = 7;
    
    /**
     * Deletes and exercise item from the future exercise list.
     *
     * @param index Index of the exercise to be deleted.
     * @return Exercise object removed.
     */
    public Exercise deleteItem(int index) {
        return (Exercise) itemList.remove(index);
    }
    /**
     * Converts the entire future exercise list to string format for printing purpose.
     *
     * @return The future exercise list in a single string.
     */

    /**
     * Converts the entire future exercise list to string format for printing purpose.
     *
     * @return The future exercise list in a single string.
     */
    @Override
    public String convertToString() {
        StringBuilder futureExerciseListToString = new StringBuilder();

        for (int i = 0; i < itemList.size(); i++) {
            futureExerciseListToString
                    .append(TAB)
                    .append(i + 1)
                    .append(". ")
                    .append(itemList.get(i))
                    .append(" (")
                    .append(getDayOfWeek(itemList.get(i).getDate()))
                    .append(" ")
                    .append(itemList.get(i).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                    .append(")")
                    .append(LS);
        }
        return futureExerciseListToString.toString().stripTrailing();
    }

    /**
     * Adds all recurring exercises between two dates into the FutureExerciseList.
     */
    public void addRecurringExercises(String description, int calories,
                                       LocalDate startDate, LocalDate endDate, ArrayList<Integer> dayOfTheWeek) {
        for (Integer day : dayOfTheWeek) {
            int dayOfReoccurrence = startDate.getDayOfWeek().getValue();
            LocalDate currentDate = startDate;
            while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
                if (dayOfReoccurrence == day) {
                    this.itemList.add(new Exercise(description, calories, currentDate));
                    this.itemList.sort(Comparator.comparing(Item::getDate));
                    currentDate = currentDate.plusDays(ONE_WEEK);
                    dayOfReoccurrence = currentDate.getDayOfWeek().getValue();
                } else {
                    currentDate = currentDate.plusDays(ONE_DAY);
                    dayOfReoccurrence = currentDate.getDayOfWeek().getValue();
                }
            }
        }
    }
}
