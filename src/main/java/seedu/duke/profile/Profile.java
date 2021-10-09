package seedu.duke.profile;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.profile.exceptions.NullCharacteristicException;

/**
 * Profile that contains the relevant data input by user.
 */
public class Profile {
    private String name;
    private double height;
    private double weight;
    private int calorieGoal;

    /**
     * Constructor for the Profile class.
     *
     * @param name Name of user
     * @param height Height of user
     * @param weight Weight of user
     * @throws InvalidCharacteristicException If a value of <= 0 is provided for height or weight
     * @throws NullCharacteristicException When the input name is null or ""
     */
    public Profile(String name, double height, double weight)
            throws InvalidCharacteristicException, NullCharacteristicException {
        setName(name);
        setHeight(height);
        setWeight(weight);
        setCalorieGoal(0); //Initialize to 0 first
    }

    public void setName(String name) throws NullCharacteristicException {
        checkNameValidity(name);
        this.name = name;
    }

    public void setHeight(double height) throws InvalidCharacteristicException {
        checkHeightValidity(height);
        this.height = height;
    }

    public void setWeight(double weight) throws InvalidCharacteristicException {
        checkWeightValidity(weight);
        this.weight = weight;
    }

    public void setCalorieGoal(int calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public double calculateBmi() {
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

    private static void checkNameValidity(String name) throws NullCharacteristicException {
        if (name == null || name.length() == 0) {
            throw new NullCharacteristicException("Name");
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
        if (bmi < 18.5) {
            result = "Underweight";
        } else if (bmi <= 24.9) {
            result = "Healthy";
        } else if (bmi <= 29.9) {
            result = "Overweight";
        } else {
            result = "Obese";
        }
        return result;
    }

    public int calculateNetCalories(int foodCalories, int exerciseCalories) {
        return foodCalories - exerciseCalories;
    }

}
