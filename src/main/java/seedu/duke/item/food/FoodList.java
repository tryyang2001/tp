package seedu.duke.item.food;

import seedu.duke.item.Item;
import seedu.duke.item.ItemList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FoodList extends ItemList {

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

    private StringBuilder extractFoodListByEachDateAndTime() {
        StringBuilder foodListInString = new StringBuilder(); //declares as StringBuilder for mutable String object
        for (int index = 0; index < foodRecords.size(); index++) {
            LocalDate currentDate = foodRecords.get(index).getDate();
            FoodList subList = new FoodList();
            while (index < foodRecords.size() && currentDate.isEqual(foodRecords.get(index).getDate())) {
                subList.addFood(foodRecords.get(index++));
            }
            foodListInString
                    .append(String.format("You have consumed %d food item(s) in %s (%s):",
                            subList.getSize(),
                            getDay(currentDate),
                            currentDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))))
                    .append(ItemList.LS);
            for (int i = 1; i <= subList.getSize(); i++) {
                foodListInString
                        .append(ItemList.TAB)
                        .append(String.format("%d. %s", i, subList.getFood(i - 1)))
                        .append(ItemList.LS);
            }
            foodListInString
                    .append(String.format("Total calories consumed: %d cal",
                            this.getTotalCaloriesWithDate(currentDate)))
                    .append(ItemList.LS);
            if (index < foodRecords.size()) {
                foodListInString.append(ItemList.LS); //prevents last line spacing
            }
            index--; //prevents double adding of index
        }
        return foodListInString;
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

    private StringBuilder extractFoodListBySpecificDate(LocalDate date) {
        StringBuilder foodListInString = new StringBuilder(); //declares as StringBuilder for mutable String object
        ArrayList<Food> subList = (ArrayList<Food>) this.foodRecords.stream()
                .filter(f -> f.getDate().isEqual(date))
                .collect(Collectors.toList());
        foodListInString
                .append(String.format("You have consumed %d food item(s) in %s (%s):",
                        subList.size(),
                        getDay(date),
                        date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))))
                .append(ItemList.LS);
        for (int i = 1; i <= subList.size(); i++) {
            foodListInString
                    .append(ItemList.TAB)
                    .append(String.format("%d. %s", i, subList.get(i - 1)))
                    .append(ItemList.LS);
        }
        foodListInString
                .append(String.format("Total calories consumed: %d cal",
                        this.getTotalCaloriesWithDate(date)))
                .append(ItemList.LS);
        return foodListInString;
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
}
