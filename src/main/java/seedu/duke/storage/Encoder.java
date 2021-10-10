package seedu.duke.storage;

import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Encoder {

    public Encoder() {

    }

    //TODO Update relevant details of profile after merging
    public ArrayList<String> encodeProfileDetails(Profile profile) {
        ArrayList<String> profileDetails = new ArrayList<>();
        profileDetails.add(profile.name);
        profileDetails.add(profile.height.toString());
        profileDetails.add(profile.weight.toString());
        profileDetails.add(profile.calorieGoal.toString());
        return profileDetails;
    }

    //TODO Update method after merge
    public ArrayList<String> encodeExerciseList(ExerciseList exerciseList){
        ArrayList<String> exercises = new ArrayList<>();
        //TODO Update exercise list after merging
        for (Exercise e : exerciseList) {
            exercises.add(e.toString());
        }
        return exercises;
    }

    //TODO Update method after merge
    public ArrayList<String> encodeFoodList(FoodList foodList) {
        ArrayList<String> foodItems = new ArrayList<>();
        for (Food f : foodList) {
            foodItems.add(f.toString());
        }
        return foodItems;
    }

    //TODO Decoder methods

}
