package seedu.duke.profile;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.profile.exceptions.NullCharacteristicException;

/**
 * Profile that contains the relevant data input by user.
 */
public class Profile {

    private static final String BMI_STATUS_UNDERWEIGHT = "Underweight";
    private static final String BMI_STATUS_HEALTHY = "Healthy";
    private static final String BMI_STATUS_OVERWEIGHT = "Overweight";
    private static final String BMI_STATUS_OBESE = "Obese";
    public static final String FILE_TEXT_DELIMITER = "|";

    private String name;
    private double height;
    private double weight;
    private int calorieGoal;

    /**
     * Constructor for the Profile class.
     *
     * @param name   Name of user
     * @param height Height of user
     * @param weight Weight of user
     * @throws InvalidCharacteristicException If a value of <= 0 is provided for height or weight
     * @throws NullCharacteristicException    When the input name is null or ""
     */
    public Profile(String name, double height, double weight, int calorieGoal)
            throws InvalidCharacteristicException {
        setName(name);
        setHeight(height);
        setWeight(weight);
        setCalorieGoal(calorieGoal);
    }

    public Profile() {

    }

    public void setProfile(String name, double height, double weight, int calorieGoal)
            throws InvalidCharacteristicException {
        setName(name);
        setHeight(height);
        setWeight(weight);
        setCalorieGoal(calorieGoal);
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }

    public String getName() {
        return this.name;
    }

    public int getCalorieGoal() {
        return this.calorieGoal;
    }

    public void setHeight(double height) throws InvalidCharacteristicException {
        checkHeightValidity(height);
        assert height > 0 : "Height cannot be non-positive.";
        this.height = height;
    }

    public void setWeight(double weight) throws InvalidCharacteristicException {
        checkWeightValidity(weight);
        assert weight > 0 : "Weight cannot be non-positive.";
        this.weight = weight;
    }

    public void setCalorieGoal(int calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public double calculateBmi() throws InvalidCharacteristicException {
        checkHeightValidity(height);
        checkWeightValidity(weight);
        return computeBmi(height, weight);
    }

    /**
     * Manually calculates the BMI.
     * Used when the user input values that is not be stored in his/her profile.
     *
     * @param height Value of manual input of height
     * @param weight Value of manual input of weight
     * @return The calculated BMI of the manual inputs
     * @throws InvalidCharacteristicException When the user inputs negative values for either height or weight
     */
    public static double calculateBmi(double height, double weight) throws InvalidCharacteristicException {
        checkHeightValidity(height);
        checkWeightValidity(weight);
        return computeBmi(height, weight);
    }

    private static double computeBmi(double height, double weight) {
        double heightInM = height / 100.0;
        return Math.round((weight / (Math.pow(heightInM, 2))) * 10) / 10.0;
    }


    private static void checkWeightValidity(double weight) throws InvalidCharacteristicException {
        if (weight <= 0) {
            throw new InvalidCharacteristicException("Weight");
        }
    }

    private static void checkHeightValidity(double height) throws InvalidCharacteristicException {
        if (height <= 0) {
            throw new InvalidCharacteristicException("Height");
        }
    }

    private static void checkBmiValidity(int bmi) throws InvalidCharacteristicException {
        if (bmi <= 1) {
            throw new InvalidCharacteristicException("Height");
        }
    }

    /**
     * Retrieves the indication with regard to the value of their BMI.
     * Should not have any exceptions thrown since the other functions handled invalid cases.
     *
     * @param bmi The bmi of the user
     * @return The status of his current body
     **/
    public static String retrieveBmiStatus(double bmi) {
        String result;
        assert bmi > 0 : "BMI cannot be non-positive.";
        if (bmi < 18.5) {
            result = BMI_STATUS_UNDERWEIGHT;
        } else if (bmi <= 24.9) {
            result = BMI_STATUS_HEALTHY;
        } else if (bmi <= 29.9) {
            result = BMI_STATUS_OVERWEIGHT;
        } else {
            result = BMI_STATUS_OBESE;
        }
        return result;
    }

    /**
     * Calculates the difference between food calories and exercise calories.
     *
     * @param foodCalories Total intake consumption
     * @param exerciseCalories Total output exerted
     * @return The net calories of food - exercise
     */
    public int calculateNetCalories(int foodCalories, int exerciseCalories) {
        return foodCalories - exerciseCalories;
    }

    /**
     * Converts the file into a string that is used for storage.
     *
     * @return String that is used for storage.
     */
    public String toFileTextString() {
        return getName() + FILE_TEXT_DELIMITER + getHeight() + FILE_TEXT_DELIMITER
                + getWeight() + FILE_TEXT_DELIMITER + getCalorieGoal();
    }

    /**
     * Check if all attributes of profile are valid.
     *
     * @return false if at least one of the profile attributes are invalid.
     */
    public boolean checkProfileCreated(){
        return height > 0 && weight > 0 && name != null;
    }

}
