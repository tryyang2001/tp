package seedu.duke.item;

import java.time.LocalDate;

public abstract class ItemList {
    protected static final String LS = System.lineSeparator();
    protected static final String TAB = "\t";

    public abstract String convertToString();

    public abstract int getSize();

    public abstract int getTotalCalories();

    /**
     * Gets the day of the week of the given date.
     *
     * @param currentDate The date to query the day of the week
     * @return The day of the week in string
     */
    protected String getDay(LocalDate currentDate) {
        String day = currentDate.getDayOfWeek().toString();
        day = day.charAt(0) + day.substring(1).toLowerCase();
        return day;
    }
}
