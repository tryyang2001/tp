package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.lang.System;

import org.junit.jupiter.api.Assertions;

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
        String expectedTestResult = Ui.DIVIDER
                + Ui.LS
                + "Your calorie gained from food is:   500"
                + Ui.LS
                + "Your calorie lost from exercise is: 500"
                + Ui.LS
                + "Your net calorie intake is: 500"
                + Ui.LS
                + "Your calorie to goal is: 700"
                + Ui.LS
                + "Percentage to goal: \u001B[32m|████            |  41.7%\u001B[0m"
                + Ui.LS
                + Ui.DIVIDER
                + Ui.LS;
        Ui.printCalorieResult(500, 1000, 1200);
        Assertions.assertEquals(expectedTestResult, outputStreamCaptor.toString()
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}