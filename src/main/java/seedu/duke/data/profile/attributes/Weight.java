package seedu.duke.data.profile.attributes;

import seedu.duke.data.item.Verifiable;

/**
 * Weight attribute of profile.
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

    /**
     * Retrieves the weight from the Weight object.
     *
     * @return the weight of Weight object
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the Weight object.
     *
     * @param weight weight input by user
     */
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
