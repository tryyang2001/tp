package seedu.duke.profile;

/**
 * Used upon startup of the application to load the profile to enter its different stages.
 */
public class ProfileLoader extends ProfileOld {

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

    private void setNameFromData(String name) {
        profileValidator.checkNameDataIntegrity(name);
        this.name = name;
    }

    private void setWeightFromData(double weight) {
        profileValidator.checkWeightDataIntegrity(weight);
        this.weight = weight;
    }

    private void setHeightFromData(double height) {
        profileValidator.checkHeightDataIntegrity(height);
        this.height = height;
    }

    private void setCalorieGoalFromData(int calorieGoal) {
        profileValidator.checkCalorieGoalDataIntegrity(calorieGoal);
        this.calorieGoal = calorieGoal;
    }

    private void setGenderFromData(char gender) {
        profileValidator.checkGenderDataIntegrity(gender);
        this.gender = gender;
    }

    private void setAgeFromData(int age) {
        profileValidator.checkAgeDataIntegrity(age);
        this.age = age;
    }

    private void setActivityFactorFromData(int activityFactor) {
        profileValidator.checkActivityFactorDataIntegrity(activityFactor);
        this.activityFactor = activityFactor;
    }
}
