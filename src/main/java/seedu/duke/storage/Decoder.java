package seedu.duke.storage;

import seedu.duke.item.exercise.Exercise;
import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.item.food.Food;
import seedu.duke.item.food.FoodList;
import seedu.duke.profile.Profile;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Converts the profile, exercise list and food list from storage and inputs into the bot.
 */
public class Decoder {

    public static final String FILE_TEXT_DELIMITER = "\\|";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    private static final Logger logger = Logger.getLogger(Decoder.class.getName());

    /**
     * Retrieves profile data from profile.txt
     *
     * @return The profile object with its corresponding characteristics
     * @throws FileNotFoundException          If the file is misplaced/missing
     * @throws InvalidCharacteristicException When the data is corrupted in the file.
     */
    public Profile getProfileFromData() throws FileNotFoundException, InvalidCharacteristicException {
        File file = new File(Storage.FILEPATH_PROFILE);
        Scanner in = new Scanner(file);
        try {
            if (in.hasNext()) {
                logger.log(Level.FINE, "Retrieving profile file.");
                return decodeProfileDataFromString(in.nextLine());
            }
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
        return new Profile();
    }

    private Profile decodeProfileDataFromString(String input) throws InvalidDataException {
        try {
            Profile profile = new Profile();
            final String[] profileDetails = input.split(FILE_TEXT_DELIMITER);
            final String name = profileDetails[0];
            final double height = Double.parseDouble(profileDetails[1]);
            final double weight = Double.parseDouble(profileDetails[2]);
            final char gender = profileDetails[3].charAt(0);
            final int age = Integer.parseInt(profileDetails[4]);
            final int calorieGoal = Integer.parseInt(profileDetails[5]);
            final int activityFactor = Integer.parseInt(profileDetails[6]);
            profile.setProfileWithRawInputs(name, height, weight, gender, age, calorieGoal, activityFactor);
            return profile;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidDataException(Storage.FILENAME_PROFILE, input);
        }
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
        logger.log(Level.FINE, "Decoding exercise data from file...");
        while (in.hasNext()) {
            try {
                decodeExerciseDataFromString(exercises, in.nextLine());
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        logger.log(Level.FINE, "Retrieved exercise data from file.");
        return exercises;
    }

    private void decodeExerciseDataFromString(ExerciseList exercises, String line) throws InvalidDataException {
        try {
            final String[] exerciseDetails = line.split(FILE_TEXT_DELIMITER);
            final String name = exerciseDetails[1];
            final int calories = Integer.parseInt(exerciseDetails[2]);
            final LocalDate dateOfExercise = parseDate(exerciseDetails[3]);
            exercises.addExercise(new Exercise(name, calories, dateOfExercise));
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            logger.log(Level.WARNING, "A line in exercise list is not valid.", line);
            throw new InvalidDataException(Storage.FILENAME_EXERCISE_LIST, line);
        }
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
        logger.log(Level.FINE, "Decoding food list data from file...");
        while (in.hasNext()) {
            try {
                decodeFoodDataFromString(foodItems, in.nextLine());
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        logger.log(Level.FINE, "Retrieved food list data from file.");
        return foodItems;
    }

    private void decodeFoodDataFromString(FoodList foodItems, String line) throws InvalidDataException {
        try {
            final String[] foodDetails = line.split(FILE_TEXT_DELIMITER);
            final String name = foodDetails[1];
            final int calories = Integer.parseInt(foodDetails[2]);
            final LocalDateTime dateTimeOfFood = parseDateTime(foodDetails[3]);
            foodItems.addFood(new Food(name, calories, dateTimeOfFood));
        } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            logger.log(Level.WARNING, "A line in food list is not valid.", line);
            throw new InvalidDataException(Storage.FILENAME_FOOD_LIST, line);
        }
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }
}

