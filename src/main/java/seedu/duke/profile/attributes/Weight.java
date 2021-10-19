package seedu.duke.profile.attributes;

/**
 * Weight attribute of profile
 */
public class Weight implements Verifiable {

    public static final int NON_POSITIVE_LIMIT = 0;

    protected double weight;

    public Weight() {

    }

    /**
     * Constructs a weight object.
     *
     * @param weight weight input by user
     */
    public Weight(double weight) {
        setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean isValid() {
        if (weight <= NON_POSITIVE_LIMIT) {
            return false;
        }
        return true;
    }
}
