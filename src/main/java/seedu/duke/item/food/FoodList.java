package seedu.duke.item.food;

import seedu.duke.item.Item;
import seedu.duke.item.ItemList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FoodList extends ItemList {

    public static final String MESSAGE_FOOD_CONSUMED = "You have consumed %d food item(s) on %s (%s):";
    public static final String DATE_FORMAT = "dd MMM yyyy";
    public static final String MESSAGE_FOOD = "%d. %s";
    public static final String MESSAGE_TOTAL_CALORIE_CONSUMED = "Total calories consumed: %d cal";
    public static final String MESSAGE_MORNING = "In the morning:";
    public static final String MESSAGE_AFTERNOON = "In the afternoon:";
    public static final String MESSAGE_EVENING = "In the evening:";
    public static final String MESSAGE_NIGHT = "At night:";
    protected ArrayList<Food> foodRecords = new ArrayList<>();

    /**
     * Returns food item in the food list.
     *
     * @param index The index of the food item
     * @return the food item with the given index
     */
    public Food getFood(int index) {
        return this.foodRecords.get(index);
    }

    /**
     * Returns the size of the array list.
     *
     * @return the size of the array list attribute
     */
    @Override
    public int getSize() {
        return foodRecords.size();
    }

    /**
     * Adds a food item into the food list.
     *
     * @param food The food class object to add
     */
    public void addFood(Food food) {
        this.foodRecords.add(food);
    }

    /**
     * Converts the entire food list to string format for printing purpose.
     *
     * @return The food list in a single string
     */
    @Override
    public String convertToString() {
        StringBuilder foodListInString = extractFoodListByEachDateAndTime();
        return foodListInString.toString().stripTrailing();
    }

    /**
     * Converts the food list of a specific date to string format for printing purpose.
     *
     * @param date The date for the food list
     * @return The food list of the specific date in a single string
     */
    public String convertToStringByDate(LocalDate date) {
        StringBuilder foodListInString = extractFoodListBySpecificDate(date);
        return foodListInString.toString().stripTrailing();
    }

    /**
     * Extracts a food list according to the given date and time period and converts it to string for printing purpose.
     *
     * @param date       The date given to query the food list
     * @param timePeriod The time period given to query the food list
     * @return
     */
    public String convertToStringBySpecificDateAndTime(LocalDate date, TimePeriod timePeriod) {
        StringBuilder foodListInString = new StringBuilder();
        ArrayList<Food> subList = (ArrayList<Food>) this.foodRecords.stream()
                .filter(f -> f.getDate().isEqual(date) && f.getTimePeriod().equals(timePeriod))
                .collect(Collectors.toList());
        FoodList timePeriodList = new FoodList();
        for (Food f : subList) {
            if (f.getTimePeriod().equals(timePeriod)) {
                timePeriodList.addFood(f);
            }
        }
        convertItemCountToString(date, foodListInString, subList.size());
        addTimePeriodMessage(timePeriod, foodListInString);
        for (int i = 1; i <= timePeriodList.getSize(); i++) {
            convertFoodItemToString(foodListInString, i, timePeriodList.getFood(i - 1));
        }
        convertTotalCaloriesToString(foodListInString, subList.stream().mapToInt(Food::getCalories).sum());
        return foodListInString.toString().stripTrailing();
    }

    /**
     * Deletes a food item according to its index number.
     *
     * @param index The index of the food item
     */
    public Food deleteFood(int index) {
        return foodRecords.remove(index);
    }

    /**
     * Deletes all the food items from the food list.
     */
    public void clearFoodList() {
        this.foodRecords.clear();
    }

    /**
     * Computes the sum of calorie of all food items in the food list.
     *
     * @return Integer value of the sum of calorie of all food
     */
    @Override
    public int getTotalCalories() {
        int sumOfFoodCalorie = 0;
        for (Food food : foodRecords) {
            sumOfFoodCalorie += food.getCalories();
        }
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
        return foodRecords.stream().filter(f -> f.getDate().isEqual(date)).mapToInt(Item::getCalories).sum();
    }

    /**
     * Sorts the food list in ascending format according to the date and time.
     */
    public void sortFoodList() {
        this.foodRecords.sort(Comparator.comparing(Food::getDateTime));
    }

    /**
     * Counts the number of food items consumed at night.
     *
     * @return The integer value count which indicates the number of food items consumed at night
     */
    public int getSupperCount() {
        int count = 0;
        for (Food f : foodRecords) {
            if (f.getTimePeriod().equals(TimePeriod.Night)) {
                count++;
            }
        }
        return count;
    }

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

    private void convertTotalCaloriesToString(StringBuilder foodListInString, int totalCalories) {
        foodListInString
                .append(String.format(MESSAGE_TOTAL_CALORIE_CONSUMED,
                        totalCalories))
                .append(ItemList.LS);
    }

    private void convertItemCountToString(LocalDate date, StringBuilder foodListInString, int size) {
        foodListInString
                .append(String.format(MESSAGE_FOOD_CONSUMED,
                        size,
                        getDay(date),
                        date.format(DateTimeFormatter.ofPattern(DATE_FORMAT))))
                .append(ItemList.LS);
    }

    private void convertFoodItemToString(StringBuilder foodListInString, int index, Food food) {
        foodListInString
                .append(ItemList.TAB)
                .append(String.format(MESSAGE_FOOD, index, food))
                .append(ItemList.LS);
    }

    /**
     * Helper method for extracting each food list according to the date and time presented in the entire food list.
     *
     * @return StringBuilder type string which contains food lists with different date and time
     */
    private StringBuilder extractFoodListByEachDateAndTime() {
        StringBuilder foodListInString = new StringBuilder(); //declares as StringBuilder for mutable String object
        for (int index = 0; index < foodRecords.size(); index++) {
            LocalDate currentDate = foodRecords.get(index).getDate();
            FoodList subList = new FoodList();
            while (index < foodRecords.size() && currentDate.isEqual(foodRecords.get(index).getDate())) {
                subList.addFood(foodRecords.get(index++));
            }
            assert subList.getSize() > 0 : "Sub list should not be empty.";
            convertItemCountToString(currentDate, foodListInString, subList.getSize());
            separateDifferentTimePeriodFoodList(foodListInString, subList);
            convertTotalCaloriesToString(foodListInString, this.getTotalCaloriesWithDate(currentDate));
            if (index < foodRecords.size()) {
                foodListInString.append(ItemList.LS); //prevents last line spacing
            }
            index--; //prevents double adding of index
        }
        return foodListInString;
    }

    /**
     * Extracts food list on the same date according to its different time period.
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
     * Appends the StringBuilder foodListInString with each time period food list.
     *
     * @param foodListInString The StringBuilder food list which contains the correct output string
     * @param timePeriodList   The food list that contains food items that consumed within the time period
     * @param periodMessage    The message to indicate the time period
     */
    private void appendWithList(StringBuilder foodListInString, FoodList timePeriodList, String periodMessage) {
        if (timePeriodList.getSize() > 0) {
            foodListInString.append(periodMessage).append(ItemList.LS);
            for (int i = 1; i <= timePeriodList.getSize(); i++) {
                convertFoodItemToString(foodListInString, i, timePeriodList.getFood(i - 1));
            }
        }
    }

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
     * Helper method for extracting food list which contains all the food item consumed on the date.
     *
     * @param date The date to query all the food items consumed
     * @return StringBuilder type string which contains a food list with the given date
     */
    private StringBuilder extractFoodListBySpecificDate(LocalDate date) {
        StringBuilder foodListInString = new StringBuilder(); //declares as StringBuilder for mutable String object
        ArrayList<Food> subList = (ArrayList<Food>) this.foodRecords.stream()
                .filter(f -> f.getDate().isEqual(date))
                .collect(Collectors.toList());
        convertItemCountToString(date, foodListInString, subList.size());
        for (int i = 1; i <= subList.size(); i++) {
            convertFoodItemToString(foodListInString, i, subList.get(i - 1));
        }
        convertTotalCaloriesToString(foodListInString, this.getTotalCaloriesWithDate(date));
        return foodListInString;
    }
}
