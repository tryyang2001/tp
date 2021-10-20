package seedu.duke.profile.attributes;

/**
 * Height attribute of profile.
 */
public class Height implements Verifiable {

    public static final int NON_POSITIVE_LIMIT = 0;

    protected double height;

    public Height() {

    }

    /**
     * Constructs a height object.
     *
     * @param height height input by user.
     */
    public Height(double height) {
        setHeight(height);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean isValid() {
        if (height <= NON_POSITIVE_LIMIT) {
            return false;
        }
        return true;
    }

}
