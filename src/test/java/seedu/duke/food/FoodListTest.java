package seedu.duke.food;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FoodListTest {
    @Test
    void addFoodUsingFoodClassParameter_foodClassParameter_expectExistsInList() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("chicken rice", 607));
        assertEquals("chicken rice (607 cal)", foodList.get(foodList.getSize() - 1).toString());
    }

    @Test
    void printNonEmptyFoodList_nonEmptyFoodList_expectCorrectOutputString() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("chicken rice", 607));
        foodList.addFood(new Food("yong tau foo", 536));
        foodList.addFood(new Food("mcspicy alacarte", 528));
        foodList.addFood(new Food("char kway teow", 744));
        System.out.println(foodList.convertToString());
    }

    @Test
    void deleteExistingFoodItem_validIndexInput_expectDeleteSuccessful() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("chicken rice", 607));
        foodList.addFood(new Food("yong tau foo", 536));
        foodList.deleteFood(1);
        assertNotEquals(2, foodList.getSize());
    }

    @Test
    void deleteNonExistingFoodItem_invalidIndexInput_expectIndexOutOfBoundException() {
        FoodList foodList = new FoodList();
        assertThrows(IndexOutOfBoundsException.class, () -> foodList.deleteFood(0));
    }

    @Test
    void deleteAllFoodItems_callDeleteAllMethod_expectEmptyList() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("chicken rice", 607));
        foodList.addFood(new Food("yong tau foo", 536));
        foodList.addFood(new Food("mcspicy alacarte", 528));
        foodList.addFood(new Food("char kway teow", 744));
        foodList.clearFoodList();
        assertEquals(0, foodList.getSize());
    }

    @Test
    void totalFoodCalories_callTotalCaloriesMethod_expectSumOfFoodCalories() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("chicken rice", 607));
        foodList.addFood(new Food("yong tau foo", 536));
        foodList.addFood(new Food("mcspicy alacarte", 528));
        foodList.addFood(new Food("char kway teow", 744));
        assertEquals(2415, foodList.totalCalories());
    }
}