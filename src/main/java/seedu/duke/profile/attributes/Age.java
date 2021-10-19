package seedu.duke.profile.attributes;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;

public class Age implements Verifiable {

    public static final int NON_POSITIVE_LIMIT = 0;

    protected int age;

    public Age() {

    }

    public Age(int age) {
        setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isValid() {
        if (age <= NON_POSITIVE_LIMIT) {
            return false;
        }
        return true;
    }
}
