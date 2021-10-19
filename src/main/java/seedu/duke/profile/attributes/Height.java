package seedu.duke.profile.attributes;

public class Height implements Verifiable {

    public static final int NON_POSITIVE_LIMIT = 0;

    protected double height;

    public Height(double height) {
        setHeight(height);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isValid() {
        if (height <= NON_POSITIVE_LIMIT) {
            return false;
        }
        return true;
    }

}
