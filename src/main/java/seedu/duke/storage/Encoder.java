package seedu.duke.storage;

import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.FoodList;
import seedu.duke.profile.Profile;

import java.util.ArrayList;

/**
 * Converts profile, exercise list and food list for storage.
 */
public class Encoder {

    /**
     * Encodes the profile details into strings for storage.
     *
     * @param profile The profile to be encoded
     * @return An ArrayList of the profile details to be stored
     */
    public ArrayList<String> encodeProfileDetails(Profile profile) {
        ArrayList<String> profileDetails = new ArrayList<>();
        profileDetails.add(profile.toFileTextString());
        return profileDetails;
    }

    /**
     * Encodes the list of exercises into an ArrayList of string in preparation for storage.
     *
     * @param exerciseList The list of exercises to be encoded
     * @return An ArrayList of the exercises to be stored
     */
    public ArrayList<String> encodeExerciseList(ExerciseList exerciseList) {
        ArrayList<String> exercises = new ArrayList<>();
        for (int i = 0; i < exerciseList.getSize(); i++) {
            exercises.add(exerciseList.getExercise(i).toFileTextString());
        }
        return exercises;
    }

    /**
     * Encodes the list of exercises into an ArrayList of string in preparation for storage.
     *
     * @param foodList The list of food items to be encoded
     * @return An ArrayList of the food items to be stored
     */
    public ArrayList<String> encodeFoodList(FoodList foodList) {
        ArrayList<String> foodItems = new ArrayList<>();
        for (int i = 0; i < foodList.getSize(); i++) {
            foodItems.add(foodList.get(i).toFileTextString());
        }
        return foodItems;
    }
}
