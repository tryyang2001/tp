package seedu.duke.profile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileTest {

    @Test
    void calculateBmi_twoIntInputs_expectDoubleReturned() {
        assertEquals(20.8, Profile.calculateBmi(170, 60));
    }
}