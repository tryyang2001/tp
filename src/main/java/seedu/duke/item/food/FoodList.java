package seedu.duke.item.food;

import seedu.duke.item.Item;
import seedu.duke.item.ItemList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class FoodList extends ItemList {

    private ArrayList<Food> foodRecords = new ArrayList<>();

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
        StringBuilder foodListInString = new StringBuilder(""); //declares as StringBuilder for mutable String object
        for (int index = 0; index < foodRecords.size(); index++) {
            LocalDate currentDate = foodRecords.get(index).getDate();
            FoodList subList = new FoodList();
            for (int i = 1; index < foodRecords.size() && currentDate.isEqual(foodRecords.get(index).getDate()); i++) {
                subList.addFood(foodRecords.get(index++));
            }
            String day = currentDate.getDayOfWeek().toString().charAt(0)
                    + currentDate.getDayOfWeek().toString().toLowerCase().substring(1);
            foodListInString
                    .append(String.format("You have consumed %d food item(s) in %s (%s):", subList.getSize(), day,
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
            index--;
        }
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

    public void sortFoodList() {
        this.foodRecords.sort(Comparator.comparing(Food::getDateTime));
    }
}
