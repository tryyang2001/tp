package seedu.duke.item.exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class FutureExerciseList extends ExerciseList {

    private static final int ONE_WEEK = 7;
    private static final int ONE_DAY = 1;

    /**
     * Converts the entire future exercise list to string format for printing purpose.
     *
     * @return The future exercise list in a single string.
     */
    @Override
    public String convertToString() {
        StringBuilder futureExerciseListToString = new StringBuilder();

        for (int i = 0; i < exerciseList.size(); i++) {
            futureExerciseListToString
                    .append(TAB)
                    .append(i + 1)
                    .append(". ")
                    .append(exerciseList.get(i))
                    .append(" (")
                    .append(getDay(exerciseList.get(i).getDate()))
                    .append(" ")
                    .append(exerciseList.get(i).getDate().format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
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
                    exerciseList.add(new Exercise(description, calories, currentDate));
                    exerciseList.sort(Comparator.comparing(Exercise::getDate));
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
