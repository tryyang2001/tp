package seedu.duke.storage;

import seedu.duke.exercise.Exercise;
import seedu.duke.exercise.ExerciseList;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;
import seedu.duke.profile.Profile;

import java.util.ArrayList;

/**
 * Converts the details from the bot for storage.
 */
public class Encoder {

    public Encoder() {

    }

    /**
     * Encodes the profile details into strings for storage.
     *
     * @param profile The profile to be encoded
     * @return An ArrayList of the profile details to be stored
     */
    public ArrayList<String> encodeProfileDetails(Profile profile) {
        //TODO Update profile to incorporate toFileTextString() after merging
        ArrayList<String> profileDetails = new ArrayList<>();
        profileDetails.add(profile.getName());
        profileDetails.add(profile.getHeight().toString());
        profileDetails.add(profile.getWeight().toString());
        profileDetails.add(profile.getCalorieGoal().toString());
        return profileDetails;
    }

    /**
     * Encodes the list of exercises into an ArrayList of string in preparation for storage.
     *
     * @param exerciseList The list of exercises to be encoded
     * @return An ArrayList of the exercises to be stored
     */
    public ArrayList<String> encodeExerciseList(ExerciseList exerciseList){
        ArrayList<String> exercises = new ArrayList<>();
        //TODO Update format of exercise added to arraylist after merging
        for (Exercise e : exerciseList) {
            exercises.add(e.toString());
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
        //TODO Update format of food added to arraylist after merging
        for (Food f : foodList) {
            foodItems.add(f.toString());
        }
        return foodItems;
    }
}
