package seedu.duke;

import java.text.DecimalFormat;
import java.lang.System;

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
    public static final String EMOJI_1 = " ᕦ(ò_óˇ)";
    public static final String FITBOT_V0 = "  ______ _ _   _           _"
            + System.lineSeparator()
            + " |  ____(_) | | |         | |"
            + System.lineSeparator()
            + " | |__   _| |_| |__   ___ | |_"
            + System.lineSeparator()
            + " |  __| | | __| '_ \\ / _ \\| __|"
            + System.lineSeparator()
            + " | |    | | |_| |_) | (_) | |_"
            + System.lineSeparator()
            + " |_|    |_|\\__|_.__/ \\___/ \\__|";

    public static void printCalorieProgress(int calorieConsumed, int calorieGoal) {
        if (calorieGoal > 0) {
            float percentage = (float) (((float) calorieConsumed / calorieGoal) * 100.0);
            String newPercentage = getPercentage(percentage);
            int barNum = getBarNum(percentage);
            String result = getResult(barNum);
            String color = determineColor(percentage);
            System.out.println(color + result + newPercentage + '%' + ANSI_RESET);
        }
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
        for (int i = 0; i < barNum; i++) {
            result = result + FULL_BLOCK;
        }
        for (int i = 0; i < (MAX_BAR - barNum); i++) {
            result = result + SPACE;
        }
        return result + "|  ";
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

    public static void printCalories(int exerciseCalories, int foodCalories, int calorieGoal) {
        int netCalories = foodCalories = exerciseCalories;
        int remainingCalories = calorieGoal - netCalories;
        System.out.println("Your calorie gained from food is:   "
                + foodCalories);
        System.out.println("Your calorie lost from exercise is: "
                + exerciseCalories);
        System.out.println("Your net calorie intake is: "
                + netCalories);
        System.out.println("Your calorie to goal is: "
                + remainingCalories);
        System.out.print("Percentage to goal: ");// need to change the functions according to slam
    }

    public static void printCalorieResult(int exerciseCalories, int foodCalories, int calorieGoal) {
        int netCalories = foodCalories = exerciseCalories;
        printCalories(exerciseCalories, foodCalories, calorieGoal);
        printCalorieProgress(netCalories, calorieGoal);

    }

    public static void printStartApplicationPage() {
        System.out.println(FITBOT_V0 + EMOJI_1);
    }
}
