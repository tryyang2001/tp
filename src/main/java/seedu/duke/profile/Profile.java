package seedu.duke.profile;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;

/**
 * Profile that contains the relevant data input by user.
 */
public class Profile {
    private double height;
    private double weight;
    private int calorieGoal;

    public Profile(double height, double weight) throws InvalidCharacteristicException {
        setHeight(height);
        setWeight(weight);
        setCalorieGoal(0); //Initialize to 0 first
    }

    public Profile() throws InvalidCharacteristicException {
        setHeight(0);
        setWeight(0);
        setCalorieGoal(0);
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
     * Used when the user decides that he input values that is not be stored in his profile.
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
        if (weight < 0) {
            throw new InvalidCharacteristicException("Weight");
        }
    }

    private static void checkHeightValidity(double height) throws InvalidCharacteristicException {
        if (height < 0) {
            throw new InvalidCharacteristicException("Height");
        }
    }

    public int calculateNetCalories(int foodCalories, int exerciseCalories) {
        return foodCalories - exerciseCalories;
    }

}
