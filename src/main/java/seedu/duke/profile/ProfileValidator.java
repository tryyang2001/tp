package seedu.duke.profile;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;

import java.util.Arrays;

/**
 * Validator for relevant profile attributes.
 */
public class ProfileValidator {

    private static final String LS = System.lineSeparator();

    public static final int NON_POSITIVE_LIMIT = 0;
    private static final char GENDER_M = 'M';
    private static final char GENDER_F = 'F';
    public static final int LIMIT_UPPER_CALORIES = 2500;
    public static final int LIMIT_LOWER_CALORIES = -LIMIT_UPPER_CALORIES;
    public static final int LIMIT_LOWER_ACTIVITY_FACTOR = 1;
    public static final int LIMIT_UPPER_ACTIVITY_LEVEL = 5;

    private static final String ERROR_HEIGHT = "Height cannot be less than or equal to 0." + LS
            + "Try a positive value instead!";
    private static final String ERROR_WEIGHT = "Weight cannot be less than or equal to 0." + LS
            + "Try a positive value instead!";
    private static final String ERROR_GENDER = "Please type in M or F only!";
    private static final String ERROR_AGE = "Age cannot be less than or equal to 0." + LS
            + "Try a positive value instead!";
    private static final String ERROR_ACTIVITY_FACTOR = "Please key in a value from " + LIMIT_LOWER_ACTIVITY_FACTOR
            + " to " + LIMIT_UPPER_ACTIVITY_LEVEL;
    private static final String ERROR_CALORIE_GOAL =
            "I don't think you should be aiming to be setting such a extreme goal of %d" + LS
            + "Try a range of " + LIMIT_LOWER_CALORIES  + " to " + LIMIT_UPPER_CALORIES;

    private static final int ATTRIBUTE_IDX_NAME = 0;
    private static final int ATTRIBUTE_IDX_HEIGHT = 1;
    private static final int ATTRIBUTE_IDX_WEIGHT = 2;
    private static final int ATTRIBUTE_IDX_CALORIE_GOAL = 3;
    private static final int ATTRIBUTE_IDX_GENDER = 4;
    private static final int ATTRIBUTE_IDX_AGE = 5;
    private static final int ATTRIBUTE_IDX_ACTIVITY_FACTOR = 6;
    private static final int ATTRIBUTE_COUNT = 7;

    private boolean[] isAttributeValid = new boolean[ATTRIBUTE_COUNT];

    public ProfileValidator() {
        initialiseAttributesToTrue();
    }

    /**
     * Retrieves attribute validity status for stages of profile set up.
     *
     * @return boolean array of the statuses.
     */
    public boolean[] getAttributesValidity() {
        return isAttributeValid;
    }

    /**
     *  Initialises array of attributes integrity to true first, so if one of them fails a check,
     *  its respective boolean will be changed to false.
     */
    public void initialiseAttributesToTrue() {
        Arrays.fill(isAttributeValid, Boolean.TRUE);
    }

    public void checkNameDataIntegrity(String name) {
        for (int i = NON_POSITIVE_LIMIT; i < name.length(); i++) {
            if (name.charAt(i) == '|' || name.charAt(i) == '/') {
                isAttributeValid[ATTRIBUTE_IDX_NAME] = false;
                break;
            }
        }
    }

    public void checkHeightDataIntegrity(double height) {
        if (height <= NON_POSITIVE_LIMIT) {
            this.isAttributeValid[ATTRIBUTE_IDX_HEIGHT] = false;
        }
    }

    public void checkWeightDataIntegrity(double weight) {
        if (weight <= NON_POSITIVE_LIMIT) {
            this.isAttributeValid[ATTRIBUTE_IDX_WEIGHT] = false;
        }
    }

    public void checkCalorieGoalDataIntegrity(int calorieGoal) {
        if (calorieGoal < LIMIT_LOWER_CALORIES || calorieGoal > LIMIT_UPPER_CALORIES) {
            this.isAttributeValid[ATTRIBUTE_IDX_WEIGHT] = false;
        }
    }

    public void checkGenderDataIntegrity(char gender) {
        if (!(gender == 'M' || gender == 'F')) {
            this.isAttributeValid[ATTRIBUTE_IDX_GENDER] = false;
        }
    }

    public void checkActivityFactorDataIntegrity(int activityFactor) {
        if (activityFactor < 1 || activityFactor > 5) {
            this.isAttributeValid[ATTRIBUTE_IDX_ACTIVITY_FACTOR] = false;
        }
    }

    public void checkAgeDataIntegrity(int age) {
        if (age <= NON_POSITIVE_LIMIT) {
            this.isAttributeValid[ATTRIBUTE_IDX_AGE] = false;
        }
    }

    public static void checkWeightValidity(double weight) throws InvalidCharacteristicException {
        if (weight <= NON_POSITIVE_LIMIT) {
            throw new InvalidCharacteristicException(ERROR_WEIGHT);
        }
    }

    public static void checkHeightValidity(double height) throws InvalidCharacteristicException {
        if (height <= NON_POSITIVE_LIMIT) {
            throw new InvalidCharacteristicException(ERROR_HEIGHT);
        }
    }

    public void checkCalorieGoalValidity(int calorieGoal) throws InvalidCharacteristicException {
        if (calorieGoal < LIMIT_LOWER_CALORIES || calorieGoal > LIMIT_UPPER_CALORIES) {
            throw new InvalidCharacteristicException(String.format(ERROR_CALORIE_GOAL, calorieGoal));
        }
    }

    public void checkGenderValidity(char gender) throws InvalidCharacteristicException {
        if (!(gender == GENDER_F || gender == GENDER_M)) {
            throw new InvalidCharacteristicException(ERROR_GENDER);
        }
    }

    public void checkAgeValidity(int age) throws InvalidCharacteristicException {
        if (age <= NON_POSITIVE_LIMIT) {
            throw new InvalidCharacteristicException(ERROR_AGE);
        }
    }

    public void checkActivityFactorValidity(int activityFactor) throws InvalidCharacteristicException {
        if (activityFactor < LIMIT_LOWER_ACTIVITY_FACTOR || activityFactor > LIMIT_UPPER_ACTIVITY_LEVEL) {
            throw new InvalidCharacteristicException(ERROR_ACTIVITY_FACTOR);
        }
    }


}
