package seedu.duke.storage;

import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;
import seedu.duke.profile.Profile;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Converts the profile, exercise list and food list from storage and inputs into the bot.
 */
public class Decoder {
    public static final String FILE_TEXT_DELIMITER = "\\|";

    /**
     * Retrieves profile data from profile.txt
     *
     * @return The profile object with its corresponding characteristics
     * @throws FileNotFoundException If the file is misplaced/missing
     * @throws InvalidCharacteristicException When the data is corrupted in the file.
     */
    public Profile getProfileFromData() throws FileNotFoundException, InvalidCharacteristicException {
        Profile profile = new Profile();
        File file = new File(Storage.FILEPATH_PROFILE);
        Scanner in = new Scanner(file);
        if (in.hasNext()) {
            profile = decodeProfileData(in.nextLine());
        }
        return profile;
    }

    private Profile decodeProfileData(String input) throws InvalidCharacteristicException {
        String[] profileDetails = input.split(FILE_TEXT_DELIMITER);
        String name = profileDetails[0];
        double height = Double.parseDouble(profileDetails[1]);
        double weight = Double.parseDouble(profileDetails[2]);
        int calorieGoal = Integer.parseInt(profileDetails[3]);
        return new Profile(name, height, weight, calorieGoal);
    }


    /**
     * Retrieves exercise list from exercise_list.txt.
     *
     * @return The exercise list with data loaded from file
     * @throws FileNotFoundException If file is misplaced/missing
     */
    public ExerciseList getExerciseListFromData() throws FileNotFoundException {
        ExerciseList exercises = new ExerciseList();
        File file = new File(Storage.FILEPATH_EXERCISE_LIST);
        Scanner in = new Scanner(file);
        while (in.hasNext()) {
            decodeExerciseData(exercises, in.nextLine());
        }
        return exercises;
    }

    private void decodeExerciseData(ExerciseList exercises, String line) {
        String[] exerciseDetails = line.split(FILE_TEXT_DELIMITER);
        String name = exerciseDetails[1];
        int calories = Integer.parseInt(exerciseDetails[2]);
        exercises.addExercise(new Exercise(name,calories));
    }

    /**
     * Retrieves food list from food_list.txt.
     *
     * @return The food list with data loaded from file
     * @throws FileNotFoundException If file is misplaced/missing
     */

    public FoodList getFoodListFromData() throws FileNotFoundException {
        FoodList foodItems = new FoodList();
        File file = new File(Storage.FILEPATH_FOOD_LIST);
        Scanner in = new Scanner(file);
        while (in.hasNext()) {
            decodeFoodData(foodItems, in.nextLine());
        }
        return foodItems;
    }

    private void decodeFoodData(FoodList foodItems, String line) {
        String[] foodDetails = line.split(FILE_TEXT_DELIMITER);
        String name = foodDetails[1];
        int calories = Integer.parseInt(foodDetails[2]);
        Food temp = new Food(name, calories);
        System.out.println(temp.toString());
        foodItems.addFood(new Food(name, calories));
    }
}

