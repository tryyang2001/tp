package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    //Credits to: https://www.baeldung.com/java-testing-system-out-println
    @Test
    void printCalorieResult_integerInputs_expectStringOnConsole() {
        String expectedTestResult = "Your calorie gained from food is:   500" + lineSeparator()
                + "Your calorie lost from exercise is: 500"
                + lineSeparator()
                + "Your net calorie intake is: 500"
                + lineSeparator()
                + "Your calorie to goal is: 700"
                + lineSeparator()
                + "Percentage to goal: \u001B[32m|████            |  41.7%\u001B[0m"
                + lineSeparator();
        Ui.printCalorieResult(500, 1000, 1200);
        assertEquals(expectedTestResult, outputStreamCaptor.toString()
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}