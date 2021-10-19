package seedu.duke.profile.attributes;

public class ActivityFactor implements Verifiable {

    public static final int LIMIT_LOWER_ACTIVITY_FACTOR = 1;
    public static final int LIMIT_UPPER_ACTIVITY_LEVEL = 5;

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

    protected int activityFactor;

    public ActivityFactor(int activityFactor) {
        setActivityFactor(activityFactor);
    }

    public int getActivityFactor() {
        return activityFactor;
    }

    public void setActivityFactor(int activityFactor) {
        this.activityFactor = activityFactor;
    }

    public boolean isValid() {
        if (activityFactor < 1 || activityFactor > 5) {
            return false;
        }
        return true;
    }

}
