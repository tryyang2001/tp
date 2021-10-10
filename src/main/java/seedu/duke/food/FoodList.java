package seedu.duke.food;

import java.util.ArrayList;

public class FoodList {
    public static final String LS = System.lineSeparator();
    public static final String MESSAGE_FOOD_LEFT_COUNT = "There %s %d food left" + LS;
    public static final String MESSAGE_FOOD_COUNT = "You have consumed %d food items:" + LS;
    public static final String MESSAGE_FOOD_CLEAR = "All food items have been deleted.";
    public static final String MESSAGE_EMPTY_FOOD_LIST = "\tThere is no record in the food list.";
    public static final String MESSAGE_FOOD_DELETION = "You have removed this food item: " + LS + "\t%s" + LS;

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
    public int size() {
        return foodRecords.size();
    }

    /**
     * Adds a food item into the food list.
     *
     * @param food The food class object to add
     */
    public void add(Food food) {
        this.foodRecords.add(food);
    }

    /**
     * Adds a food item according to its name and calorie into the food list.
     *
     * @param foodName The name description of the food
     * @param calories  The calorie of the food
     */
    public void add(String foodName, int calories) {
        this.foodRecords.add(new Food(foodName, calories));
    }

    /**
     * Prints the list of all the food items.
     */
    public void printFoodList() {
        if (foodRecords.size() > 0) {
            System.out.printf(MESSAGE_FOOD_COUNT, foodRecords.size());
            for (int i = 0; i < foodRecords.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + foodRecords.get(i));
            }
        } else {
            System.out.println(MESSAGE_EMPTY_FOOD_LIST);
        }
    }

    /**
     * Deletes a food item according to its index number.
     *
     * @param index The index of the food item
     */
    public void delete(int index) {
        Food deletedFood = foodRecords.remove(index);
        System.out.printf(MESSAGE_FOOD_DELETION, deletedFood);
        String isOrAre = "is";
        if (foodRecords.size() > 1) {
            isOrAre = "are";
        }
        System.out.printf(MESSAGE_FOOD_LEFT_COUNT, isOrAre, foodRecords.size());
    }

    /**
     * Deletes all the food items from the food list.
     */
    public void deleteAll() {
        this.foodRecords.clear();
        System.out.println(MESSAGE_FOOD_CLEAR);
    }
}
