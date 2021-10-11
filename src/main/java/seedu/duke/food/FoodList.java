package seedu.duke.food;

import java.util.ArrayList;

public class FoodList {
    public static final String LS = System.lineSeparator();
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
        StringBuilder foodListInString; //declares as StringBuilder for mutable String object
        if (foodRecords.size() == 1) {
            foodListInString = new StringBuilder("You have consumed 1 food item:" + LS);
        } else {
            foodListInString = new StringBuilder("You have consumed " + foodRecords.size() + " food items: " + LS);
        }
        for (int i = 0; i < foodRecords.size(); i++) {
            foodListInString.append("\t").append(i + 1).append(". ").append(foodRecords.get(i)).append(LS);
        }
        return foodListInString.toString();
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
    public void deleteAll() {
        this.foodRecords.clear();
    }
}
