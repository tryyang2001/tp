package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.lang.System;

import org.junit.jupiter.api.Assertions;
import seedu.duke.ui.Ui;

class uiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    //Credits to: https://www.baeldung.com/java-testing-system-out-println
    @Test
    void printCalorieResult_integerInputs_expectStringOnConsole() {
        Ui ui = new Ui();
        String expectedTestResult = ui.DIVIDER
                + ui.LS
                + "Your calorie gained from food is: 1000"
                + ui.LS
                + "Your calorie lost from exercise is: 500"
                + ui.LS
                + "Your net calorie intake is: 500"
                + ui.LS
                + "Your calorie to goal is: 700"
                + ui.LS
                + "Percentage to goal: \u001B[32m|████      |        41.7%\u001B[0m"
                + ui.LS
                + ui.DIVIDER
                + ui.LS;
        ui.printCalorieResult(500, 1000, 1200);
        Assertions.assertEquals(expectedTestResult, outputStreamCaptor.toString()
        );
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}