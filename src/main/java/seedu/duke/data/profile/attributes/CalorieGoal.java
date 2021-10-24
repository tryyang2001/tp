package seedu.duke.data.profile.attributes;

/**
 * Calorie Goal attribute of profile.
 */
public class CalorieGoal implements Verifiable {

    private static final String LS = System.lineSeparator();

    public static final int LIMIT_UPPER_CALORIES = 2500;
    public static final int LIMIT_LOWER_CALORIES = -LIMIT_UPPER_CALORIES;

    protected int calorieGoal;

    public CalorieGoal() {

    }

    /**
     * Constructs a calorie goal object.
     *
     * @param calorieGoal goal input by user
     */
    public CalorieGoal(int calorieGoal) {
        setCalorieGoal(calorieGoal);
    }

    /**
     * Retrieves the calorie goal of CalorieGoal object.
     *
     * @return the calorie goal stored in CalorieGoal object
     */
    public int getCalorieGoal() {
        return calorieGoal;
    }

    /**
     * Sets the calorie goal for CalorieGoal object.
     *
     * @param calorieGoal calorie goal input by user
     */
    public void setCalorieGoal(int calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    @Override
    public boolean isValid() {
        if (calorieGoal < LIMIT_LOWER_CALORIES || calorieGoal > LIMIT_UPPER_CALORIES) {
            return false;
        }
        return true;
    }
}
