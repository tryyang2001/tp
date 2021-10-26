package seedu.duke.data.profile.attributes;

/**
 * Interface that implements verifiability to various objects.
 */
interface Verifiable {
    /**
     * Implements a simple check that verifies if the attribute tied to Verifiable is valid.
     *
     * @return True if valid, false if invalid as specified by each attribute
     */
    boolean isValid();
}
