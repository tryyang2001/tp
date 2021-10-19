package seedu.duke.profile.attributes;

/**
 * Age attribute of profile.
 */
public class Age implements Verifiable {

    public static final int NON_POSITIVE_LIMIT = 0;

    protected int age;

    public Age() {

    }

    /**
     * Constructs an age object
     *
     * @param age age input by user
     */
    public Age(int age) {
        setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean isValid() {
        if (age <= NON_POSITIVE_LIMIT) {
            return false;
        }
        return true;
    }
}
