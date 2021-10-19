package seedu.duke.profile.attributes;

public class Weight {

    public static final int NON_POSITIVE_LIMIT = 0;

    protected double weight;

    public Weight(double weight) {
        setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isValid() {
        if (weight <= NON_POSITIVE_LIMIT) {
            return false;
        }
        return true;
    }
}
