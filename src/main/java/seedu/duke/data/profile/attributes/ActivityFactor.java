package seedu.duke.data.profile.attributes;

import seedu.duke.data.item.Verifiable;

/**
 * Activity Factor attribute of profile.
 */
public class ActivityFactor implements Verifiable {

    public static final int LIMIT_LOWER_ACTIVITY_FACTOR = 1;
    public static final int LIMIT_UPPER_ACTIVITY_LEVEL = 5;

    protected int activityFactor;

    public ActivityFactor() {

    }

    /**
     * Constructs an activity factor object.
     *
     * @param activityFactor activity factor input by user
     */
    public ActivityFactor(int activityFactor) {
        setActivityFactor(activityFactor);
    }

    /**
     * Retrieves the activity factor of ActivityFactor object.
     *
     * @return the activity factor of ActivityFactor object
     */
    public int getActivityFactor() {
        return activityFactor;
    }

    /**
     * Sets the activity factor of ActivityFactor object.
     *
     * @param activityFactor activity factor input by user
     */
    public void setActivityFactor(int activityFactor) {
        this.activityFactor = activityFactor;
    }

    @Override
    public boolean isValid() {
        if (activityFactor < 1 || activityFactor > 5) {
            return false;
        }
        return true;
    }

}
