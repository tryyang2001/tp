package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FoodListTest {

    @Test
    void testAddFoodUsingFoodClassParameter_foodClassParameter_expectExistsInList() {
        FoodList foodList = new FoodList();
        foodList.add(new Food("chicken rice", 607));
        assertEquals("chicken rice (607 cal)", foodList.get(foodList.size() - 1).toString());
    }

    @Test
    void testAddFoodUsingStringParameters_stringParameterInputs_expectExistsInList() {
        FoodList foodList = new FoodList();
        foodList.add("chicken rice", 607);
        assertEquals("chicken rice (607 cal)", foodList.get(foodList.size() - 1).toString());
    }

    @Test
    void printEmptyFoodList_noItemInList_expectErrorMessage() {
        new FoodList().printFoodList();
    }

    @Test
    void deleteExistingFoodItem_validIndexInput_expectDeleteSuccessful() {
        FoodList foodList = new FoodList();
        foodList.add("chicken rice", 607);
        foodList.add("yong tau foo", 536);
        foodList.delete(1);
        assertNotEquals(2, foodList.size());
    }

    @Test
    void deleteNonExistingFoodItem_invalidIndexInput_expectIndexOutOfBoundException() {
        FoodList foodList = new FoodList();
        assertThrows(IndexOutOfBoundsException.class, () -> foodList.delete(0));
    }

    @Test
    void deleteAllFoodItems_callDeleteAllMethod_expectEmptyList() {
        FoodList foodList = new FoodList();
        foodList.add("chicken rice", 607);
        foodList.add("yong tau foo", 536);
        foodList.add("mcspicy alacarte", 528);
        foodList.add("char kway teow", 744);
        foodList.deleteAll();
        assertEquals(0, foodList.size());
    }
}