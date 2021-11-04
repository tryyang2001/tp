package seedu.duke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.attributes.ActivityFactor;
import seedu.duke.data.profile.attributes.Age;
import seedu.duke.data.profile.attributes.Gender;
import seedu.duke.data.profile.attributes.Height;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsTest {

    private FoodList foodItems;
    private ExerciseList exerciseItems;
    private Profile profile;
    Statistics stats = new Statistics(new FoodList(), new ExerciseList(),new Profile());

    @Test
    void printCalorieResult_netCaloriesAndCalorieGoal_messages() {
        int caloriesGoal = 5000;
        int foodCalories = 3000;
        int exerciseCalories = 2000;
        int netCalories = foodCalories - exerciseCalories;
        String message = stats.printCaloriesMessage(netCalories, caloriesGoal);
        assertEquals("You are 4000 cal away from your goal!", message);
    }

    @Test
    void printCalories_foodCaloriesExerciseCaloriesAndCalorieGoal_messageArray() {
        this.profile = new Profile();
        profile.setProfileAge(new Age(12));
        profile.setProfileHeight(new Height(170.0));
        profile.setProfileGender(new Gender('M'));
        profile.setProfileActivityFactor(new ActivityFactor(3));
        int caloriesGoal = 5000;
        int foodCalories = 3000;
        int exerciseCalories = 2000;
        String expectedResult = "You are 6448 cal away from your goal!";
        assertEquals(expectedResult, stats.getCaloriesReport(foodCalories, exerciseCalories, caloriesGoal)[4]);
    }


}