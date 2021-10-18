package seedu.duke.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * ItemList is an abstract class that contains all the common methods for food list and exercise list.
 */
public abstract class ItemList {
    public static final String MESSAGE_ITEM = "%d. %s";
    protected static final String DATE_FORMAT = "dd MMM yyyy";
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

    protected void convertItemCountToString(StringBuilder itemListInString, int size, LocalDate date, String message) {
        itemListInString
                .append(String.format(message, size, getDay(date),
                        date.format(DateTimeFormatter.ofPattern(DATE_FORMAT))))
                .append(ItemList.LS);
    }

    protected void convertItemToString(StringBuilder itemListInString, int index, Item item, String message) {
        itemListInString
                .append(ItemList.TAB)
                .append(String.format(message, index, item))
                .append(ItemList.LS);
    }

    protected void convertTotalCaloriesToString(StringBuilder itemListInString, int totalCalories, String message) {
        itemListInString
                .append(String.format(message,
                        totalCalories))
                .append(ItemList.LS);
    }
}
