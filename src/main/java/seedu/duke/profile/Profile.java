package seedu.duke.profile;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;

/**
 * Profile that contains the relevant data input by user.
 */
public class Profile {
    private static final String BMI_STATUS_UNDERWEIGHT = "Underweight";
    private static final String BMI_STATUS_HEALTHY = "Healthy";
    private static final String BMI_STATUS_OVERWEIGHT = "Overweight";
    private static final String BMI_STATUS_OBESE = "Obese";
    public static final double BMI_LIMIT_UNDERWEIGHT = 18.5;
    public static final double BMI_LIMIT_HEALTHY = 24.9;
    public static final double BMI_LIMIT_OVERWEIGHT = 29.9;

    public static final String FILE_TEXT_DELIMITER = "|";

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

    private String name;
    private double height;
    private double weight;
    private int calorieGoal;
    private char gender;
    private int age;
    private int activityFactor;

    private ProfileValidator profileValidator = new ProfileValidator();

    //TODO Update profile constructors and setProfile methods to more relevant attributes once merged

    /**
     * Constructor for the Profile class.
     *
     * @param name        Name of user
     * @param height      Height of user
     * @param weight      Weight of user
     * @param calorieGoal Calorie target of user
     * @throws InvalidCharacteristicException If a value of <= 0 is provided for height or weight
     */
    public Profile(String name, double height, double weight, int calorieGoal)
            throws InvalidCharacteristicException {
        profileValidator.initialiseAttributesToTrue();
        setName(name);
        setHeight(height);
        setWeight(weight);
        setCalorieGoal(calorieGoal);
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
     * @throws InvalidCharacteristicException
     */
    public Profile(String name, double height, double weight, int calorieGoal, char gender, int age, int activityFactor)
            throws InvalidCharacteristicException {
        profileValidator.initialiseAttributesToTrue();
        setName(name);
        setHeight(height);
        setWeight(weight);
        setCalorieGoal(calorieGoal);
        setGender(gender);
        setAge(age);
        setActivityFactor(activityFactor);

    }

    public Profile() {

    }

    public void setProfile(String name, double height, double weight, int calorieGoal)
            throws InvalidCharacteristicException {
        profileValidator.initialiseAttributesToTrue();
        setName(name);
        setHeight(height);
        setWeight(weight);
        setCalorieGoal(calorieGoal);
    }

    /**
     * Sets the profile in ProfileUpdateCommand.
     * Ensures the data are of valid inputs before setting them.
     *
     * @param name           Name of user
     * @param height         Height of user
     * @param weight         Weight of user
     * @param calorieGoal    Calorie target of user
     * @param gender         Gender of user (M/F)
     * @param age            Age of user
     * @param activityFactor Activity level of user
     * @throws InvalidCharacteristicException If the data values input are not valid
     */
    public void setProfile(String name, double height, double weight, int calorieGoal,
                           char gender, int age, int activityFactor) throws InvalidCharacteristicException {
        profileValidator.initialiseAttributesToTrue();
        setName(name);
        setHeight(height);
        setWeight(weight);
        setCalorieGoal(calorieGoal);
        setGender(gender);
        setAge(age);
        setActivityFactor(activityFactor);
    }

    /**
     * This method is used for the checking of data integrity upon startup of the application.
     * Ensures the user has not misappropriated the contents of the file to an invalid argument.
     *
     * @param name           Name of user
     * @param height         Height of user
     * @param weight         Weight of user
     * @param calorieGoal    Calorie target of user
     * @param gender         Gender of user (M/F)
     * @param age            Age of user
     * @param activityFactor Activity level of user
     * @return Checks of whether each attribute is valid.
     */
    public boolean[] setProfileFromData(String name, double height, double weight, int calorieGoal,
                           char gender, int age, int activityFactor) {
        profileValidator.initialiseAttributesToTrue();
        setNameFromData(name);
        setHeightFromData(height);
        setWeightFromData(weight);
        setCalorieGoalFromData(calorieGoal);
        setGenderFromData(gender);
        setAgeFromData(age);
        setActivityFactorFromData(activityFactor);
        return profileValidator.getAttributesValidity();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) throws InvalidCharacteristicException {
        profileValidator.checkWeightValidity(weight);
        this.weight = weight;
    }

    public void setHeight(double height) throws InvalidCharacteristicException {
        profileValidator.checkHeightValidity(height);
        this.height = height;
    }

    public void setCalorieGoal(int calorieGoal) throws InvalidCharacteristicException {
        profileValidator.checkCalorieGoalValidity(calorieGoal);
        this.calorieGoal = calorieGoal;
    }

    public void setGender(char gender) throws InvalidCharacteristicException {
        profileValidator.checkGenderValidity(gender);
        this.gender = gender;
    }

    public void setAge(int age) throws InvalidCharacteristicException {
        profileValidator.checkAgeValidity(age);
        this.age = age;
    }

    public void setActivityFactor(int activityFactor) throws InvalidCharacteristicException {
        profileValidator.checkActivityFactorValidity(activityFactor);
        this.activityFactor = activityFactor;
    }

    public void setNameFromData(String name) {
        profileValidator.checkNameDataIntegrity(name);
        this.name = name;
    }

    public void setWeightFromData(double weight) {
        profileValidator.checkWeightDataIntegrity(weight);
        this.weight = weight;
    }

    public void setHeightFromData(double height) {
        profileValidator.checkHeightDataIntegrity(height);
        this.height = height;
    }

    public void setCalorieGoalFromData(int calorieGoal) {
        profileValidator.checkCalorieGoalDataIntegrity(calorieGoal);
        this.calorieGoal = calorieGoal;
    }

    public void setGenderFromData(char gender) {
        profileValidator.checkGenderDataIntegrity(gender);
        this.gender = gender;
    }

    public void setAgeFromData(int age) {
        profileValidator.checkAgeDataIntegrity(age);
        this.age = age;
    }

    public void setActivityFactorFromData(int activityFactor) {
        profileValidator.checkActivityFactorDataIntegrity(activityFactor);
        this.activityFactor = activityFactor;
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

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getActivityFactor() {
        return activityFactor;
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
        switch (activityFactor) {
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

    private double getBaseBmrValue() {
        double bmr;
        if (gender == GENDER_M) {
            bmr = GENDER_M_WEIGHT_FACTOR * weight
                    + GENDER_M_HEIGHT_FACTOR * height
                    - GENDER_M_AGE_FACTOR * age
                    + GENDER_M_CONSTANT;
        } else {
            bmr = GENDER_F_WEIGHT_FACTOR * weight
                    + GENDER_F_HEIGHT_FACTOR * height
                    - GENDER_F_AGE_FACTOR * age
                    + GENDER_F_CONSTANT;
        }
        return bmr;
    }

    /**
     * Calculates the BMI based on the profile's characteristics.
     *
     * @return The calculated BMI of the profile's height and weight
     * @throws InvalidCharacteristicException When the profile data integrity is compromised through modifying .txt file
     */
    public double calculateBmi() throws InvalidCharacteristicException {
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
        ProfileValidator.checkHeightValidity(height);
        ProfileValidator.checkWeightValidity(weight);
        assert weight > 0 : "Weight cannot be non-positive.";
        assert height > 0 : "Height cannot be non-positive.";
        return computeBmi(height, weight);
    }

    private static double computeBmi(double height, double weight) {
        double heightInM = height / 100.0;
        return Math.round((weight / (Math.pow(heightInM, 2))) * 10) / 10.0;
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
        return getName() + FILE_TEXT_DELIMITER + getHeight() + FILE_TEXT_DELIMITER
                + getWeight() + FILE_TEXT_DELIMITER + getCalorieGoal() + FILE_TEXT_DELIMITER
                + getGender() + FILE_TEXT_DELIMITER + getAge() + FILE_TEXT_DELIMITER
                + getActivityFactor();
    }

}
