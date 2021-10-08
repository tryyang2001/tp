package seedu.duke.profile;

import org.junit.jupiter.api.Test;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ProfileTest {

    @Test
    void createProfile_negativeInput_expectException() {
        assertThrows(InvalidCharacteristicException.class,
            () -> {
                Profile p = new Profile(-1, -1);
            });
    }

    @Test
    void setHeight_negativeInput_expectException() throws InvalidCharacteristicException {
        Profile p = new Profile(170.2, 60.5);
        assertThrows(InvalidCharacteristicException.class,
            () -> p.setHeight(-1));
    }

    @Test
    void setWeight_negativeInput_expectException() throws InvalidCharacteristicException {
        Profile p = new Profile(170.2, 60.5);
        assertThrows(InvalidCharacteristicException.class,
            () -> p.setWeight(-1));
    }

    @Test
    void calculateBmi_twoDoubleInputs_expectDoubleReturned() throws InvalidCharacteristicException {
        assertEquals(20.4, Profile.calculateBmi(171.2, 59.8));
    }

    @Test
    void calculateBmi_negativeInputs_expectException() {
        assertThrows(InvalidCharacteristicException.class,
            () -> Profile.calculateBmi(-171.2, -59.8));
    }

}