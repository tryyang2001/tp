package seedu.duke.profile;

import org.junit.jupiter.api.Test;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ProfileTest {

    @Test
    void setProfileParameters_invalidInputs_expectInvalidCharacteristicException() {
        Profile p = new Profile();
        double height = -1;
        double weight = -1;
        char gender = 'D';
        int age = -1;
        int activityFactorUnderRange = 0;
        int activityFactorOverRange = 6;
        assertThrows(InvalidCharacteristicException.class, () -> p.setHeight(height));
        assertThrows(InvalidCharacteristicException.class, () -> p.setWeight(weight));
        assertThrows(InvalidCharacteristicException.class, () -> p.setGender(gender));
        assertThrows(InvalidCharacteristicException.class, () -> p.setAge(age));
        assertThrows(InvalidCharacteristicException.class, () -> p.setActivityFactor(activityFactorOverRange));
        assertThrows(InvalidCharacteristicException.class, () -> p.setActivityFactor(activityFactorUnderRange));
    }

    @Test
    void calculateBmi_twoDoubleInputs_expectDoubleReturned() throws InvalidCharacteristicException {
        double height = 171.2;
        double weight = 59.8;
        assertEquals(20.4, Profile.calculateBmi(height, weight));
    }

    @Test
    void calculateBmi_negativeHeightInput_expectInvalidCharacteristicException() {
        double height = -171.2;
        double weight = 59.8;
        assertThrows(InvalidCharacteristicException.class,
            () -> Profile.calculateBmi(height, weight));
    }

    @Test
    void calculateBmi_negativeWeightInput_expectInvalidCharacteristicException() {
        double height = 171.2;
        double weight = -59.8;
        assertThrows(InvalidCharacteristicException.class,
                () -> Profile.calculateBmi(height, weight));
    }

    @Test
    void retrieveBmiStatus_validBmiInputs_expectCorrectStatuses() {
        String expectedStatusUnderweight = "Underweight";
        String expectedStatusHealthy = "Healthy";
        String expectedStatusOverweight = "Overweight";
        String expectedStatusObese = "Obese";
        double bmiUnderweight = 10.5;
        double bmiHealthy = 22.5;
        double bmiOverweight = 25.5;
        double bmiObese = 30.0;
        assertEquals(expectedStatusUnderweight, Profile.retrieveBmiStatus(bmiUnderweight));
        assertEquals(expectedStatusHealthy, Profile.retrieveBmiStatus(bmiHealthy));
        assertEquals(expectedStatusOverweight, Profile.retrieveBmiStatus(bmiOverweight));
        assertEquals(expectedStatusObese, Profile.retrieveBmiStatus(bmiObese));
    }

    @Test
    void getBmrValuesMen_validInputs_expectCorrectBmrValues() throws InvalidCharacteristicException {
        Profile p = new Profile();
        String name = "John";
        double height = 170.1;
        double weight = 60;
        int calorieGoal = 300;
        char gender = 'M';
        int age = 22;
        int activityFactor = 1;
        double baseBmr = 1583.5979;
        p.setProfile(name, height, weight, calorieGoal, gender, age, activityFactor);

        int bmrSedentary = (int) Math.round(baseBmr * 1.2);
        assertEquals(bmrSedentary, p.getBmr());

        int bmrLight = (int) Math.round(baseBmr * 1.375);
        p.setActivityFactor(2);
        assertEquals(bmrLight, p.getBmr());

        int bmrModerate = (int) Math.round(baseBmr * 1.55);
        p.setActivityFactor(3);
        assertEquals(bmrModerate, p.getBmr());

        int bmrIntense = (int) Math.round(baseBmr * 1.725);
        p.setActivityFactor(4);
        assertEquals(bmrIntense, p.getBmr());

        int bmrExtreme = (int) Math.round(baseBmr * 1.9);
        p.setActivityFactor(5);
        assertEquals(bmrExtreme, p.getBmr());
    }

    @Test
    void getBmrValuesFemale_validInputs_expectCorrectBmrValues() throws InvalidCharacteristicException {
        Profile p = new Profile();
        String name = "Mary";
        double height = 160.1;
        double weight = 45.2;
        int calorieGoal = 300;
        char gender = 'F';
        int age = 20;
        int activityFactor = 1;
        double baseBmr = 1274.9472;
        p.setProfile(name, height, weight, calorieGoal, gender, age, activityFactor);

        int bmrSedentary = (int) Math.round(baseBmr * 1.2);
        assertEquals(bmrSedentary, p.getBmr());

        int bmrLight = (int) Math.round(baseBmr * 1.375);
        p.setActivityFactor(2);
        assertEquals(bmrLight, p.getBmr());

        int bmrModerate = (int) Math.round(baseBmr * 1.55);
        p.setActivityFactor(3);
        assertEquals(bmrModerate, p.getBmr());

        int bmrIntense = (int) Math.round(baseBmr * 1.725);
        p.setActivityFactor(4);
        assertEquals(bmrIntense, p.getBmr());

        int bmrExtreme = (int) Math.round(baseBmr * 1.9);
        p.setActivityFactor(5);
        assertEquals(bmrExtreme, p.getBmr());
    }

    @Test
    void toFileTextString_validInputs_expectCorrectString() throws InvalidCharacteristicException {
        Profile p = new Profile();
        String name = "John";
        double height = 170.1;
        double weight = 60;
        int calorieGoal = 300;
        char gender = 'M';
        int age = 22;
        int activityFactor = 1;
        p.setProfile(name, height, weight, calorieGoal, gender, age, activityFactor);
        String correctOutput = "John|170.1|60.0|300|M|22|1";
        assertEquals(correctOutput, p.toFileTextString());
    }

}