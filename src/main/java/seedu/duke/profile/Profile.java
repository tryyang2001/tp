package seedu.duke.profile;

import seedu.duke.profile.attributes.ActivityFactor;
import seedu.duke.profile.attributes.Age;
import seedu.duke.profile.attributes.CalorieGoal;
import seedu.duke.profile.attributes.Gender;
import seedu.duke.profile.attributes.Height;
import seedu.duke.profile.attributes.Name;
import seedu.duke.profile.attributes.Weight;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;

public class Profile {

    public static final String FILE_TEXT_DELIMITER = "|";

    private static final String LS = System.lineSeparator();

    public static final int NON_POSITIVE_LIMIT = 0;

    public static final String ERROR_HEIGHT = "Height cannot be less than or equal to 0." + LS
            + "Try a positive value instead!";
    public static final String ERROR_WEIGHT = "Weight cannot be less than or equal to 0." + LS
            + "Try a positive value instead!";
    public static final String ERROR_GENDER = "Please type in M or F only!";
    public static final String ERROR_AGE = "Age cannot be less than or equal to 0." + LS
            + "Try a positive value instead!";
    public static final String ERROR_ACTIVITY_FACTOR = "Please key in a value from "
            + ActivityFactor.LIMIT_LOWER_ACTIVITY_FACTOR
            + " to "
            + ActivityFactor.LIMIT_UPPER_ACTIVITY_LEVEL;
    public static final String ERROR_CALORIE_GOAL =
            "I don't think you should be aiming to be setting such a extreme goal of %d" + LS
                    + "Try a range of " + CalorieGoal.LIMIT_LOWER_CALORIES  + " to " + CalorieGoal.LIMIT_UPPER_CALORIES;

    private static final String BMI_STATUS_UNDERWEIGHT = "Underweight";
    private static final String BMI_STATUS_HEALTHY = "Healthy";
    private static final String BMI_STATUS_OVERWEIGHT = "Overweight";
    private static final String BMI_STATUS_OBESE = "Obese";
    public static final double BMI_LIMIT_UNDERWEIGHT = 18.5;
    public static final double BMI_LIMIT_HEALTHY = 24.9;
    public static final double BMI_LIMIT_OVERWEIGHT = 29.9;

    public static final double FACTOR_SEDENTARY = 1.2;
    public static final double FACTOR_LIGHT = 1.375;
    public static final double FACTOR_MODERATE = 1.55;
    public static final double FACTOR_INTENSE = 1.725;
    public static final double FACTOR_EXTREME = 1.9;

    private static final char GENDER_M = 'M';
    private static final char GENDER_F = 'F';

    public static final double GENDER_M_WEIGHT_FACTOR = 13.397;
    public static final double GENDER_M_HEIGHT_FACTOR = 4.799;
    public static final double GENDER_M_AGE_FACTOR = 5.677;
    public static final double GENDER_M_CONSTANT = 88.362;
    public static final double GENDER_F_WEIGHT_FACTOR = 9.247;
    public static final double GENDER_F_HEIGHT_FACTOR = 3.098;
    public static final double GENDER_F_AGE_FACTOR = 4.330;
    public static final double GENDER_F_CONSTANT = 447.593;

    protected Name name = new Name();
    protected Height height = new Height();
    protected Weight weight = new Weight();
    protected Gender gender = new Gender();
    protected Age age = new Age();
    protected CalorieGoal calorieGoal = new CalorieGoal();
    protected ActivityFactor activityFactor = new ActivityFactor();

    public Profile(){

    }

    /**
     * Constructor for the Profile class.
     *
     * @param name           Name of user
     * @param height         Height of user
     * @param weight         Weight of user
     * @param calorieGoal    Calorie target of user
     * @param gender         Gender of user (M/F)
     * @param age            Age of user
     * @param activityFactor Activity level of user
     */
    public Profile(Name name, Height height, Weight weight, Gender gender,
                      Age age, CalorieGoal calorieGoal, ActivityFactor activityFactor) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.age = age;
        this.calorieGoal = calorieGoal;
        this.activityFactor = activityFactor;
    }

    /**
     * Sets the profile in various commands with the raw inputs if necessary.
     *
     * @param name           Name of user
     * @param height         Height of user
     * @param weight         Weight of user
     * @param calorieGoal    Calorie target of user
     * @param gender         Gender of user (M/F)
     * @param age            Age of user
     * @param activityFactor Activity level of user
     */
    public void setProfileWithRawInputs(String name, double height, double weight,
                                        char gender, int age, int calorieGoal, int activityFactor) {
        this.name.setName(name);
        this.height.setHeight(height);
        this.weight.setWeight(weight);
        this.gender.setGender(gender);
        this.age.setAge(age);
        this.calorieGoal.setCalorieGoal(calorieGoal);
        this.activityFactor.setActivityFactor(activityFactor);
    }

    public void setProfileName(Name name) {
        this.name = name;
    }

    public void setProfileHeight(Height height) {
        this.height = height;
    }

    public void setProfileWeight(Weight weight) {
        this.weight = weight;
    }

    public void setProfileGender(Gender gender) {
        this.gender = gender;
    }

    public void setProfileAge(Age age) {
        this.age = age;
    }

    public void setProfileCalorieGoal(CalorieGoal calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public void setProfileActivityFactor(ActivityFactor activityFactor) {
        this.activityFactor = activityFactor;
    }

    public Name getProfileName() {
        return this.name;
    }

    public Height getProfileHeight() {
        return this.height;
    }

    public Weight getProfileWeight() {
        return this.weight;
    }

    public Gender getProfileGender() {
        return this.gender;
    }

    public Age getProfileAge() {
        return this.age;
    }

    public CalorieGoal getProfileCalorieGoal() {
        return this.calorieGoal;
    }

    public ActivityFactor getProfileActivityFactor() {
        return this.activityFactor;
    }

    /**
     * Retrieves the Basal Metabolic Rate of the user based on their activity factor indicated on the profile.
     * A higher activity factor indicates a greater metabolic rate and thus more calories they burn off.
     *
     * @return BMR value based on their indicated activity factor
     */
    public int getBmr() {
        double bmr = getBaseBmrValue();
        double factor = 1.0; //Initialise to 1
        switch (activityFactor.getActivityFactor()) {
        case 1:
            factor = FACTOR_SEDENTARY;
            break;
        case 2:
            factor = FACTOR_LIGHT;
            break;
        case 3:
            factor = FACTOR_MODERATE;
            break;
        case 4:
            factor = FACTOR_INTENSE;
            break;
        case 5:
            factor = FACTOR_EXTREME;
            break;
        default:
            break;
        }
        return (int) Math.round(bmr * factor);
    }

    /**
     * Calculates the BMI based on the profile's characteristics.
     *
     * @return The calculated BMI of the profile's height and weight
     */
    public double calculateBmi() {
        return computeBmi(height.getHeight(), weight.getWeight());
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
        assert weight > 0 : "Weight cannot be non-positive.";
        assert height > 0 : "Height cannot be non-positive.";
        return computeBmi(height, weight);
    }

    private static double computeBmi(double height, double weight) {
        double heightInM = height / 100.0;
        return Math.round((weight / (Math.pow(heightInM, 2))) * 10) / 10.0;
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
        if (bmi < BMI_LIMIT_UNDERWEIGHT) {
            result = BMI_STATUS_UNDERWEIGHT;
        } else if (bmi <= BMI_LIMIT_HEALTHY) {
            result = BMI_STATUS_HEALTHY;
        } else if (bmi <= BMI_LIMIT_OVERWEIGHT) {
            result = BMI_STATUS_OVERWEIGHT;
        } else {
            result = BMI_STATUS_OBESE;
        }
        return result;
    }

    private double getBaseBmrValue() {
        double bmr;
        if (gender.getGender() == GENDER_M) {
            bmr = GENDER_M_WEIGHT_FACTOR * weight.getWeight()
                    + GENDER_M_HEIGHT_FACTOR * height.getHeight()
                    - GENDER_M_AGE_FACTOR * age.getAge()
                    + GENDER_M_CONSTANT;
        } else {
            bmr = GENDER_F_WEIGHT_FACTOR * weight.getWeight()
                    + GENDER_F_HEIGHT_FACTOR * height.getHeight()
                    - GENDER_F_AGE_FACTOR * age.getAge()
                    + GENDER_F_CONSTANT;
        }
        return bmr;
    }

    /**
     * Calculates the difference between food calories and exercise calories, factoring in metabolic rate.
     *
     * @param foodCalories     Total intake consumption
     * @param exerciseCalories Total output exerted
     * @return The net calories of food - (exercise + BMR)
     * @throws InvalidCharacteristicException Only if activity factor has been misappropriated in .txt file
     */
    public int calculateNetCalories(int foodCalories, int exerciseCalories) throws InvalidCharacteristicException {
        return foodCalories - exerciseCalories - getBmr();
    }

    /**
     * Converts the file into a string that is used for storage.
     *
     * @return String that is used for storage.
     */
    public String toFileTextString() {
        return name.getName() + FILE_TEXT_DELIMITER + height.getHeight() + FILE_TEXT_DELIMITER
                + weight.getWeight() + FILE_TEXT_DELIMITER + gender.getGender() + FILE_TEXT_DELIMITER
                + age.getAge() + FILE_TEXT_DELIMITER + calorieGoal.getCalorieGoal() + FILE_TEXT_DELIMITER
                + activityFactor.getActivityFactor();
    }

}
