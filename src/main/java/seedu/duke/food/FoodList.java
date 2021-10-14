package seedu.duke.food;

import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class FoodList {

    private ArrayList<Food> foodRecords = new ArrayList<>();

    /**
     * Returns food item in the food list.
     *
     * @param index The index of the food item
     * @return the food item with the given index
     */
    public Food get(int index) {
        return this.foodRecords.get(index);
    }

    /**
     * Returns the size of the array list.
     *
     * @return the size of the array list attribute
     */
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
    public String convertToString() {
        StringBuilder foodListInString = new StringBuilder(""); //declares as StringBuilder for mutable String object
        for (int i = 0; i < foodRecords.size(); i++) {
            foodListInString.append("\t").append(i + 1).append(". ").append(foodRecords.get(i)).append(Ui.LS);
            //avoids counting the new line character as the length
            foodListInString.setLength(foodListInString.length() - 1);
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
    public int getTotalCalories() {
        int sumOfFoodCalorie = 0;
        for (Food food : foodRecords) {
            sumOfFoodCalorie += food.getCalories();
        }
        assert sumOfFoodCalorie >= 0 : "Total calories cannot less than 0";
        return sumOfFoodCalorie;
    }
}
