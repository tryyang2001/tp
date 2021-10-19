package seedu.duke.item.food;

import seedu.duke.item.Item;
import seedu.duke.item.ItemList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FoodList extends ItemList {

    public static final String MESSAGE_FOOD_CONSUMED = "You have consumed %d food item(s) on %s (%s):";
    public static final String MESSAGE_TOTAL_CALORIE_CONSUMED = "Total calories consumed: %d cal";
    public static final String MESSAGE_MORNING = "In the morning:";
    public static final String MESSAGE_AFTERNOON = "In the afternoon:";
    public static final String MESSAGE_EVENING = "In the evening:";
    public static final String MESSAGE_NIGHT = "At night:";
    public static final String MESSAGE_NO_FOOD_IN_DATE_TIME =
            "There is no food item found by the given date and time period";
    public static final String MESSAGE_NO_FOOD_IN_DATE = "There is no food item found by the given date";

    public FoodList() {
        itemList = new ArrayList<>();
    }

    /**
     * Returns food item in the food list.
     *
     * @param index The index of the food item
     * @return the food item with the given index
     */
    public Food getFood(int index) {
        return this.itemList.get(index);
    }

    /**
     * Returns the size of the array list.
     *
     * @return the size of the array list attribute
     */
    @Override
    public int getSize() {
        return itemList.size();
    }

    /**
     * Adds a food item into the food list.
     *
     * @param food The food class object to add
     */
    public void addFood(Food food) {
        this.itemList.add(food);
    }

    /**
     * Converts the entire food list to string format for printing purpose.
     *
     * @return The food list in a single string
     */
    @Override
    public String convertToString() {
        StringBuilder foodListInString = extractFoodListByEachDateAndTimePeriod();
        return foodListInString.toString().stripTrailing();
    }

    /**
     * Converts the food list of a specific date to string format for printing purpose.
     *
     * @param date The date for the food list
     * @return The food list of the specific date in a single string
     */
    public String convertToStringBySpecificDate(LocalDate date) {
        StringBuilder foodListInString = extractFoodListBySpecificDate(date);
        return foodListInString.toString().stripTrailing();
    }

    /**
     * Extracts a food list according to the given date and time period and converts it to string for printing purpose.
     *
     * @param date       The date given to query the food list
     * @param timePeriod The time period given to query the food list
     * @return The food list of the specific date and time period in a single string
     */
    public String convertToStringBySpecificDateAndTime(LocalDate date, TimePeriod timePeriod) {
        StringBuilder foodListInString = new StringBuilder();
        ArrayList<Food> subList = filterListAccordingToDateAndTimePeriod(date, timePeriod);
        if (subList.size() == 0) {
            foodListInString
                    .append(MESSAGE_NO_FOOD_IN_DATE_TIME)
                    .append(ItemList.LS);
            return foodListInString.toString().stripTrailing();
        }
        processListToString(date, timePeriod, foodListInString, subList);
        return foodListInString.toString().stripTrailing();
    }

    /**
     * Deletes a food item according to its index number.
     *
     * @param index The index of the food item
     */
    public Food deleteFood(int index) {
        return itemList.remove(index);
    }

    /**
     * Deletes a food item according to its index number, date and time.
     *
     * @param index The index of the food item as shown in the view f/ command
     * @param date  The date of the food item consumed
     * @param time  The time of the food item consumed
     * @return The deleted food item
     */
    public Food deleteFood(int index, LocalDate date, LocalTime time) {
        assert index >= 0 && index < itemList.size() : "Index cannot be out of bound";
        LocalDateTime dateTime = date.atTime(time);
        Food deletedFood = new Food("", 0, dateTime); //constructs food object to get the time period
        int actualIndex = getActualIndex(index, deletedFood);
        deletedFood = itemList.remove(actualIndex);
        return deletedFood;
    }

    /**
     * Deletes all the food items from the food list.
     */
    public void clearFoodList() {
        this.itemList.clear();
    }

    /**
     * Computes the sum of calorie of all food items in the food list.
     *
     * @return Integer value of the sum of calorie of all food
     */
    @Override
    public int getTotalCalories() {
        int sumOfFoodCalorie = itemList.stream().mapToInt(Food::getCalories).sum();
        assert sumOfFoodCalorie >= 0 : "Total calories cannot less than 0";
        return sumOfFoodCalorie;
    }

    /**
     * Computes the sum of calorie of all food items consumed in a specific date.
     *
     * @param date The date to query all the consumed food items
     * @return Integer value of the sum of calorie of all food items consumed in the given date
     */
    public int getTotalCaloriesWithDate(LocalDate date) {
        int sumOfFoodCalorie = itemList.stream()
                .filter(f -> f.getDate().isEqual(date))
                .mapToInt(Food::getCalories)
                .sum();
        assert sumOfFoodCalorie >= 0 : "Total calories cannot less than 0";
        return sumOfFoodCalorie;
    }

    /**
     * Sorts the food list in ascending format according to the date and time.
     */
    public void sortFoodList() {
        this.itemList.sort(Comparator.comparing(Food::getDateTime));
    }

    /**
     * Counts the number of food items consumed at night.
     *
     * @return The integer value count which indicates the number of food items consumed at night
     */
    public int getSupperCount() {
        return (int) itemList.stream().filter(f -> f.getTimePeriod().equals(TimePeriod.Night)).count();
    }

    /**
     * Helper method used in deleteFood to get the actual index from the entire food list of the food item to delete.
     *
     * @param index       The index of the food item as shown in the view f/ command
     * @param deletedFood The food item to delete
     * @return The actual index of the food item in the entire food list
     */
    private int getActualIndex(int index, Food deletedFood) {
        for (int i = 0; i < itemList.size(); i++) {
            if (isFoodToDelete(deletedFood, i, index)) {
                return i + index;
            }
        }
        return -1;
    }

    /**
     * Helper method used in getActualIndex to determine if the food item is the food to delete.
     *
     * @param deletedFood  The food item to delete
     * @param currentIndex The current index of the entire food list
     * @param index        The index of the food item as shown in the view f/ command
     * @return True if the current food item is the food to delete, false otherwise
     */
    private boolean isFoodToDelete(Food deletedFood, int currentIndex, int index) {
        boolean isSameDate = itemList.get(currentIndex).getDate().equals(deletedFood.getDate());
        boolean isSameTimePeriod = itemList.get(currentIndex).getTimePeriod().equals(deletedFood.getTimePeriod());
        boolean hasTheIndex = itemList.get(currentIndex + index).getTimePeriod().equals(deletedFood.getTimePeriod());
        return isSameDate && isSameTimePeriod && hasTheIndex;
    }

    /**
     * Helper method used in convertToStringBySpecificDateAndTime to append strings to the string builder.
     *
     * @param date             The date to query the food list
     * @param timePeriod       The time period to query the food list
     * @param foodListInString The StringBuilder food list which contains the correct output string
     * @param subList          The array list of food items that contains all the food items with same date and time
     *                         period as the given date and timePeriod
     */
    private void processListToString(LocalDate date, TimePeriod timePeriod,
                                     StringBuilder foodListInString, ArrayList<Food> subList) {
        FoodList timePeriodList = new FoodList();
        for (Food f : subList) {
            if (f.getTimePeriod().equals(timePeriod)) {
                timePeriodList.addFood(f);
            }
        }
        convertItemCountToString(foodListInString, subList.size(), date, MESSAGE_FOOD_CONSUMED);
        addTimePeriodMessage(timePeriod, foodListInString);
        for (int i = 1; i <= timePeriodList.getSize(); i++) {
            convertItemToString(foodListInString, i, timePeriodList.getFood(i - 1));
        }
        convertTotalCaloriesToString(
                foodListInString,
                subList.stream().mapToInt(Food::getCalories).sum(),
                MESSAGE_TOTAL_CALORIE_CONSUMED);
    }

    /**
     * Helper method used in convertToStringBySpecificDateAndTime to filter the original food list.
     *
     * @param date       The date to query the food list
     * @param timePeriod The time period to query the food list
     * @return The array list which contains food items with same date and time period as provided
     */
    private ArrayList<Food> filterListAccordingToDateAndTimePeriod(LocalDate date, TimePeriod timePeriod) {
        return (ArrayList<Food>) this.itemList.stream()
                .filter(f -> f.getDate().isEqual(date) && f.getTimePeriod().equals(timePeriod))
                .collect(Collectors.toList());
    }

    /**
     * Helper method used in processListToString to retrieve the relevant message to the provided time period.
     *
     * @param timePeriod       The time period to query the food list
     * @param foodListInString The StringBuilder food list which contains the correct output string
     */
    private void addTimePeriodMessage(TimePeriod timePeriod, StringBuilder foodListInString) {
        switch (timePeriod) {
        case Morning:
            foodListInString.append(MESSAGE_MORNING).append(ItemList.LS);
            break;
        case Afternoon:
            foodListInString.append(MESSAGE_AFTERNOON).append(ItemList.LS);
            break;
        case Evening:
            foodListInString.append(MESSAGE_EVENING).append(ItemList.LS);
            break;
        case Night:
            foodListInString.append(MESSAGE_NIGHT).append(ItemList.LS);
            break;
        default:
        }
    }

    /**
     * Helper method used in convertToString for extracting each food list
     * according to the date and time presented in the entire food list.
     *
     * @return StringBuilder type string which contains food lists with different date and time
     */
    private StringBuilder extractFoodListByEachDateAndTimePeriod() {
        StringBuilder foodListInString = new StringBuilder(); //declares as StringBuilder for mutable String object
        for (int index = 0; index < itemList.size(); index++) {
            LocalDate currentDate = itemList.get(index).getDate();
            FoodList subList = new FoodList();
            while (index < itemList.size() && currentDate.isEqual(itemList.get(index).getDate())) {
                subList.addFood(itemList.get(index++));
            }
            assert subList.getSize() > 0 : "Sub list should not be empty.";
            convertItemCountToString(foodListInString, subList.getSize(), currentDate, MESSAGE_FOOD_CONSUMED);
            separateDifferentTimePeriodFoodList(foodListInString, subList);
            convertTotalCaloriesToString(
                    foodListInString,
                    this.getTotalCaloriesWithDate(currentDate),
                    MESSAGE_TOTAL_CALORIE_CONSUMED);
            if (index < itemList.size()) {
                foodListInString.append(ItemList.LS); //prevents last line spacing
            }
            index--; //prevents double adding of index
        }
        return foodListInString;
    }

    /**
     * Helper method used in extractFoodListByEachDateAndTimePeriod to extract food list
     * on the same date according to its different time period.
     *
     * @param foodListInString The StringBuilder food list.
     * @param subList          FoodList which contains only the food items for the same date
     */
    private void separateDifferentTimePeriodFoodList(StringBuilder foodListInString, FoodList subList) {
        FoodList morningList = new FoodList();
        FoodList afternoonList = new FoodList();
        FoodList eveningList = new FoodList();
        FoodList midnightList = new FoodList();
        extractFoodListByEachTimePeriod(subList, morningList, afternoonList, eveningList, midnightList);
        appendWithList(foodListInString, morningList, MESSAGE_MORNING);
        appendWithList(foodListInString, afternoonList, MESSAGE_AFTERNOON);
        appendWithList(foodListInString, eveningList, MESSAGE_EVENING);
        appendWithList(foodListInString, midnightList, MESSAGE_NIGHT);
    }

    /**
     * Helper method used in separateDifferentTimePeriodFoodList to append
     * the StringBuilder foodListInString with each time period food list.
     *
     * @param foodListInString The StringBuilder food list which contains the correct output string
     * @param timePeriodList   The food list that contains food items that consumed within the time period
     * @param periodMessage    The message to indicate the time period
     */
    private void appendWithList(StringBuilder foodListInString, FoodList timePeriodList, String periodMessage) {
        if (timePeriodList.getSize() > 0) {
            foodListInString.append(periodMessage).append(ItemList.LS);
            for (int i = 1; i <= timePeriodList.getSize(); i++) {
                convertItemToString(foodListInString, i, timePeriodList.getFood(i - 1));
            }
        }
    }

    /**
     * Helper method used in separateDifferentTimePeriodFoodList to extract the larger subList into 4 smaller lists
     * which contain food items with same date according to the time period.
     *
     * @param subList       The food list that contains all the food items consumed on the same date
     * @param morningList   The food list that contains all the food items consumed in the morning of a given date
     * @param afternoonList The food list that contains all the food items consumed in the afternoon of a given date
     * @param eveningList   The food list that contains all the food items consumed in the evening of a given date
     * @param nightList     The food list that contains all the food items consumed at night of a given date
     */
    private void extractFoodListByEachTimePeriod(FoodList subList, FoodList morningList, FoodList afternoonList,
                                                 FoodList eveningList, FoodList nightList) {
        for (int i = 1; i <= subList.getSize(); i++) {
            switch (subList.getFood(i - 1).getTimePeriod()) {
            case Morning:
                morningList.addFood(subList.getFood(i - 1));
                break;
            case Afternoon:
                afternoonList.addFood(subList.getFood(i - 1));
                break;
            case Evening:
                eveningList.addFood(subList.getFood(i - 1));
                break;
            case Night:
                nightList.addFood(subList.getFood(i - 1));
                break;
            default:
            }
        }
    }

    /**
     * Helper method used in convertToStringBySpecificDate for extracting
     * food list which contains all the food item consumed on the date.
     *
     * @param date The date to query all the food items consumed
     * @return StringBuilder type string which contains a food list with the given date
     */
    private StringBuilder extractFoodListBySpecificDate(LocalDate date) {
        StringBuilder foodListInString = new StringBuilder(); //declares as StringBuilder for mutable String object
        ArrayList<Food> subList = (ArrayList<Food>) this.itemList.stream()
                .filter(f -> f.getDate().isEqual(date))
                .collect(Collectors.toList());
        if (subList.size() == 0) {
            foodListInString
                    .append(MESSAGE_NO_FOOD_IN_DATE)
                    .append(ItemList.LS);
            return foodListInString;
        }
        convertItemCountToString(foodListInString, subList.size(), date, MESSAGE_FOOD_CONSUMED);
        for (int i = 1; i <= subList.size(); i++) {
            convertItemToString(foodListInString, i, subList.get(i - 1));
        }
        convertTotalCaloriesToString(
                foodListInString,
                this.getTotalCaloriesWithDate(date),
                MESSAGE_TOTAL_CALORIE_CONSUMED);
        return foodListInString;
    }
}
