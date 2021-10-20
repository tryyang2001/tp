package seedu.duke.item.exercise;

import java.time.format.DateTimeFormatter;


public class FutureExerciseList extends ExerciseList {

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
}
