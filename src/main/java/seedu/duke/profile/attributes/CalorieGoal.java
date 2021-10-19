package seedu.duke.profile.attributes;

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

    public int getCalorieGoal() {
        return calorieGoal;
    }

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
