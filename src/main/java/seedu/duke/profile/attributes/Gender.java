package seedu.duke.profile.attributes;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;

public class Gender implements Verifiable {

    private static final char GENDER_M = 'M';
    private static final char GENDER_F = 'F';

    protected char gender;

    public Gender() {

    }

    public Gender(char gender) {
        this.gender = gender;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public boolean isValid() {
        if (!(gender == GENDER_F || gender == GENDER_M)) {
            return false;
        }
        return true;
    }


}
