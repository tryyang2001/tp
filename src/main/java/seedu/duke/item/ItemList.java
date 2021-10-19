package seedu.duke.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * ItemList is an abstract class that contains all the common methods for food list and exercise list.
 */
public abstract class ItemList {
    public static final String MESSAGE_ITEM = "%d. %s";
    protected static final String DATE_FORMAT = "dd MMM yyyy";
    protected static final String LS = System.lineSeparator();
    protected static final String TAB = "\t";
    protected ArrayList<Item> itemList;

    /**
     * Returns item in the item list.
     *
     * @param index The index of the item
     * @return the item with the given index
     */
    public Item getItem(int index) {
        return itemList.get(index);
    }

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    public Item deleteItem(int index) {
        return this.itemList.remove(index);
    }

    public int getTotalCalories() {
        int totalCalories = itemList.stream().mapToInt(Item::getCalories).sum();
        assert totalCalories >= 0 : "Total calories cannot less than 0";
        return totalCalories;
    }

    public void clearList() {
        itemList.clear();
    }

    public abstract void sortList();

    public abstract String convertToString();

    public abstract int getSize();

    /**
     * Gets the day of the week of the given date.
     *
     * @param currentDate The date to query the day of the week
     * @return The day of the week in string
     */
    protected String getDayOfWeek(LocalDate currentDate) {
        String day = currentDate.getDayOfWeek().toString();
        day = day.charAt(0) + day.substring(1).toLowerCase();
        return day;
    }

    /**
     * Common method used in food list and exercise list to generate the item count string.
     *
     * @param itemListInString The StringBuilder that will contain the correct output string format
     * @param size             The size of the item list
     * @param date             The date to query the item count
     * @param message          The string format to display
     */
    protected void convertItemCountToString(StringBuilder itemListInString, int size, LocalDate date, String message) {
        itemListInString
                .append(String.format(message, size, getDayOfWeek(date),
                        date.format(DateTimeFormatter.ofPattern(DATE_FORMAT))))
                .append(ItemList.LS);
    }

    /**
     * Common method used in food list and exercise list to generate the item string.
     *
     * @param itemListInString The StringBuilder that will contain the correct output string format
     * @param index            The index of the item in the list
     * @param item             The item to convert to string
     */
    protected void convertItemToString(StringBuilder itemListInString, int index, Item item) {
        itemListInString.append(ItemList.TAB).append(String.format(MESSAGE_ITEM, index, item)).append(ItemList.LS);
    }

    /**
     * Common method used in food list and exercise list to generate the total calorie string.
     *
     * @param itemListInString The StringBuilder that will contain the correct output string format
     * @param totalCalories    The sum of calorie for each item stored inside the list
     * @param message          The string format to display
     */
    protected void convertTotalCaloriesToString(StringBuilder itemListInString, int totalCalories, String message) {
        itemListInString.append(String.format(message, totalCalories)).append(ItemList.LS);
    }
}
