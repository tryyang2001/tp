package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.item.food.FoodList;
import seedu.duke.profile.Profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsTest {

    private FoodList foodItems;
    private ExerciseList exerciseItems;
    private Profile profile;
    Statistics stats = new Statistics(foodItems, exerciseItems,profile);

    @Test
    void printCalorieResult_netCaloriesAndCalorieGoal_messages() {
        int caloriesGoal = 5000;
        int foodCalories = 3000;
        int exerciseCalories = 2000;
        int netCalories = foodCalories - exerciseCalories;
        String message = stats.printCaloriesMessage(netCalories, caloriesGoal);
        assertEquals("You are 4000 cal away from your goal", message);
    }

    @Test
    void printCalories_foodCaloriesExerciseCaloriesAndCalorieGoal_messageArray() {
        int caloriesGoal = 5000;
        int foodCalories = 3000;
        int exerciseCalories = 2000;
        String expectedResult = "You are 6000 cal away from your goal";
        assertEquals(expectedResult, stats.getCaloriesReport(foodCalories, exerciseCalories, caloriesGoal)[4]);
    }


}