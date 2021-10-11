package seedu.duke.ui;

import seedu.duke.parser.Parser;

import java.text.DecimalFormat;
import java.lang.System;
import java.util.Scanner;

/**
 * This class deals with interaction with user on CLI.
 * Also helps to change color of output if required.
 */
public class Ui {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_GRAY = "\u001B[90m";
    public static final String FULL_BLOCK = "█";
    public static final String HALF_BLOCK = "▌";
    public static final int MAX_BAR = 16;
    public static final int BAR_WIDTH = 10;
    public static final String SPACE = " ";
    public static final String DIVIDER = "___________________________________________"
            + "_______________________________________________";
    public static final String LS = System.lineSeparator();
    public static final String EMOJI_1 = " ᕦ(ò_óˇ)";
    public static final String FITBOT_V0 = "  ______ _ _   _           _"
            + LS
            + " |  ____(_) | | |         | |"
            + LS
            + " | |__   _| |_| |__   ___ | |_"
            + LS
            + " |  __| | | __| '_ \\ / _ \\| __|"
            + LS
            + " | |    | | |_| |_) | (_) | |_"
            + LS
            + " |_|    |_|\\__|_.__/ \\___/ \\__|";
    public static final String MESSAGE_CALORIE_GAIN = "Your calorie gained from food is: %d";
    public static final String MESSAGE_CALORIE_LOST = "Your calorie lost from exercise is: %d";
    public static final String MESSAGE_CALORIE_NET = "Your net calorie intake is: %d";
    public static final String MESSAGE_CALORIE_GOAL = "Your calorie to goal is: %d";
    public static final String MESSAGE_CALORIE_TO_GOAL_PERCENTAGE = "Percentage to goal: ";
    //TODO: These constants are to be moved to UI class
    public static final String QUOTATION = "\"";
    private static final String MESSAGE_BYE = "Bye! Hope to see you again soon!!";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.printStartApplicationPage();
    }

    public String getUserInput() {  //To be moved into UI
        return scanner.nextLine();
    }

    /**
     * Generate a progress bar between net calorie and calorieGoal.
     *
     * @param netCalorie  is the net calories gained by consuming food and calorie lost by exercising.
     * @param calorieGoal is the goal set by the user.
     * @return progress statistic if calorieGoal is positive integer.
     */
    private static String printCalorieProgress(int netCalorie, int calorieGoal) {
        if (calorieGoal > 0) {
            float percentage = (float) (((float) netCalorie / calorieGoal) * 100.0);
            String newPercentage = getPercentage(percentage);
            int barNum = getBarNum(percentage);
            String result = getResult(barNum);
            String color = determineColor(percentage);
            return MESSAGE_CALORIE_TO_GOAL_PERCENTAGE + color + result + newPercentage + '%' + ANSI_RESET;
        }
        return "";
    }

    private static String getPercentage(float percentage) {
        DecimalFormat df = new DecimalFormat("#.#");
        String newPercentage = df.format(percentage);
        return newPercentage == null ? "0.0" : newPercentage;
    }

    private static int getBarNum(float percentage) {
        int barNum = Math.round(percentage / BAR_WIDTH);
        return barNum > 15 ? 15 : barNum;
    }

    private static String getResult(int barNum) {
        String result = "|";
        int counter = 0;
        for (int i = 0; i < barNum; i++) {
            result = result + FULL_BLOCK;
            counter++;
            if (counter == 10) {
                result += '|';
            }
        }
        for (int i = 0; i < (MAX_BAR - barNum); i++) {
            result = result + SPACE;
            counter++;
            if (counter == 10) {
                result += '|';
            }
        }
        return result + "  ";
    }

    private static String determineColor(float percentage) {
        String color;
        if (percentage <= 100) {
            color = ANSI_GREEN;
        } else if (percentage <= 120) {
            color = ANSI_YELLOW;
        } else {
            color = ANSI_RED;
        }
        return color;
    }

    /**
     * Surround strings with lines for user to differentiate results.
     *
     * @param messages is the strings that need to be printed on CLI
     */
    public void formatMessageFramedWithDivider(String... messages) {
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(DIVIDER);
    }

    /**
     * Calculate netCalories and format exerciseCalories, foodCalories, calorieGoal
     * into strings.
     *
     * @param exerciseCalories is the total calories lost by exercising
     * @param foodCalories     is the total calories gained by consuming food
     * @param calorieGoal      is the goal set by the user
     * @return formatted strings.
     */
    private static String[] printCalories(int exerciseCalories, int foodCalories, int calorieGoal) {
        int netCalories = foodCalories - exerciseCalories;
        int remainingCalories = calorieGoal - netCalories;
        return new String[]{String.format(MESSAGE_CALORIE_GAIN, foodCalories),
                String.format(MESSAGE_CALORIE_LOST, exerciseCalories),
                String.format(MESSAGE_CALORIE_NET, netCalories),
                String.format(MESSAGE_CALORIE_GOAL, remainingCalories),
                printCalorieProgress(netCalories, calorieGoal)};
    }

    /**
     * Print all the statistics regarding calories.
     *
     * @param exerciseCalories is the total calories lost by exercising
     * @param foodCalories     is the total calories gained by consuming food
     * @param calorieGoal      is the goal set by the user
     */
    public void printCalorieResult(int exerciseCalories, int foodCalories, int calorieGoal) {
        formatMessageFramedWithDivider(printCalories(exerciseCalories, foodCalories, calorieGoal));
    }


    public void printStartApplicationPage() {
        System.out.println(FITBOT_V0 + EMOJI_1);
    }
}
