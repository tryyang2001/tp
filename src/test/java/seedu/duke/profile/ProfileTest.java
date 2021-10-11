package seedu.duke.profile;

import org.junit.jupiter.api.Test;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.profile.exceptions.NullCharacteristicException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ProfileTest {

    @Test
    void createProfile_negativeInputs_expectException() {
        String name = "John";
        double height = -1;
        double weight = -1;
        int calorieGoal = 0;
        assertThrows(InvalidCharacteristicException.class,
            () -> {
                Profile p = new Profile(name, height, weight, calorieGoal);
            });
    }

    @Test
    void setHeight_negativeInput_expectException() throws InvalidCharacteristicException {
        String name = "John";
        double height = 170.2;
        double weight = 60.5;
        int calorieGoal = 1240;
        Profile p = new Profile(name, height, weight, calorieGoal);
        assertThrows(InvalidCharacteristicException.class,
            () -> p.setHeight(-1));
    }

    @Test
    void setWeight_negativeInput_expectException() throws InvalidCharacteristicException {
        String name = "John";
        double height = 170.2;
        double weight = 60.5;
        int calorieGoal = 1240;
        Profile p = new Profile(name, height, weight, calorieGoal);
        assertThrows(InvalidCharacteristicException.class,
            () -> p.setWeight(-1));
    }

    @Test
    void calculateBmi_twoDoubleInputs_expectDoubleReturned() throws InvalidCharacteristicException {
        double height = 171.2;
        double weight = 59.8;
        assertEquals(20.4, Profile.calculateBmi(height, weight));
    }

    @Test
    void calculateBmi_negativeInputs_expectException() {
        double height = -171.2;
        double weight = -59.8;
        assertThrows(InvalidCharacteristicException.class,
            () -> Profile.calculateBmi(height, weight));
    }

    @Test
    void retrieveBmiStatus_healthyBmiInput_expectHealthyStatus() {
        String expectedStatus = "Healthy";
        double bmi = 22.5;
        assertEquals(expectedStatus, Profile.retrieveBmiStatus(bmi));
    }
}