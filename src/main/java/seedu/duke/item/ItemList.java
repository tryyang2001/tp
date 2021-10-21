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

    /**
     * Returns the size of the array list.
     *
     * @return the size of the array list attribute
     */
    public int getSize() {
        return itemList.size();
    }

    /**
     * Adds an item into the item list.
     *
     * @param item The item class object to add
     */
    public void addItem(Item item) {
        this.itemList.add(item);
        sortList();
    }

    /**
     * Computes the sum of calorie of all items in the item list.
     *
     * @return Integer value of the sum of calorie of all the items
     */
    public int getTotalCalories() {
        int totalCalories = itemList.stream().mapToInt(Item::getCalories).sum();
        assert totalCalories >= 0 : "Total calories cannot less than 0";
        return totalCalories;
    }

    /**
     * Computes the sum of calorie of all food items consumed in a specific date.
     *
     * @param date The date to query all the consumed food items
     * @return Integer value of the sum of calorie of all food items consumed in the given date
     */
    public int getTotalCaloriesWithDate(LocalDate date) {
        int totalCalories = itemList.stream()
                .filter(i -> i.getDate().isEqual(date))
                .mapToInt(Item::getCalories)
                .sum();
        assert totalCalories >= 0 : "Total calories cannot less than 0";
        return totalCalories;
    }

    /**
     * Deletes all the item inside the list.
     */
    public void clearList() {
        itemList.clear();
    }

    /**
     * Sorts the item list according to date (and) time, will be implemented in FoodList and ExerciseList.
     */
    public abstract void sortList();

    /**
     * Converts the item list to a string for printing purpose,
     * will be implemented in FoodList and ExerciseList.
     *
     * @return The string of the item list
     */
    public abstract String convertToString();

    /**
     * Converts the item list of a specific date to a string for printing purpose,
     * will be implemented in FoodList and ExerciseList.
     *
     * @param date The given date to query the item list
     * @return The string of the item list of a specific date
     */
    public abstract String convertToStringBySpecificDate(LocalDate date);

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
