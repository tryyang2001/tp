package seedu.duke.profile.attributes;

public class ActivityFactor implements Verifiable {

    public static final int LIMIT_LOWER_ACTIVITY_FACTOR = 1;
    public static final int LIMIT_UPPER_ACTIVITY_LEVEL = 5;

    protected int activityFactor;

    public ActivityFactor() {

    }

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
