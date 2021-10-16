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
        assertEquals("chicken rice (607 cal)", foodList.getFood(foodList.getSize() - 1).toString());
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
    void printEmptyFoodList_emptyFoodList_expectEmptyString() {
        FoodList foodList = new FoodList();
        assertEquals("", foodList.convertToString());
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
        foodList.addFood(new Food("chicken rice", 607));
        assertThrows(IndexOutOfBoundsException.class, () -> foodList.deleteFood(3));
    }

    @Test
    void deleteNegativeIndexFoodItem_negativeIndexInput_expectIndexOutOfBoundException() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("chicken rice", 607));
        assertThrows(IndexOutOfBoundsException.class, () -> foodList.deleteFood(-1));
    }

    @Test
    void deleteNonDigitIndexFoodItem_nonDigitIndexInput_expectNumberFormatException() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("chicken rice", 607));
        assertThrows(NumberFormatException.class, () -> foodList.deleteFood(Integer.parseInt("a")));
    }

    @Test
    void deleteFromEmptyFoodList_emptyFoodList_expectIndexOutOfBoundException() {
        FoodList foodList = new FoodList();
        assertThrows(IndexOutOfBoundsException.class, () -> foodList.deleteFood(1));
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
    void deleteAllFromEmptyFoodList_emptyFoodList_expectEmptyList() {
        FoodList foodList = new FoodList();
        foodList.clearFoodList();
        assertEquals(0,foodList.getSize());
    }

    @Test
    void totalFoodCalories_callTotalCaloriesMethod_expectSumOfFoodCalories() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("chicken rice", 607));
        foodList.addFood(new Food("yong tau foo", 536));
        foodList.addFood(new Food("mcspicy alacarte", 528));
        foodList.addFood(new Food("char kway teow", 744));
        assertEquals(2415, foodList.getTotalCalories());
    }

    @Test
    void totalFoodCalories_emptyFoodList_expectZeroSum() {
        FoodList foodList = new FoodList();
        assertEquals(0, foodList.getTotalCalories());
    }
}