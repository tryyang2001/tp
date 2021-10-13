package seedu.duke.ui;

import java.util.logging.Level;
import java.util.logging.Logger;


/* A class that manage the statics of the calories*/
public class Statistics {
    public static final String MESSAGE_CALORIE_GAIN = "Your calorie gained from food is: %d";
    public static final String MESSAGE_CALORIE_LOST = "Your calorie lost from exercise is: %d";
    public static final String MESSAGE_CALORIE_NET = "Your net calorie intake is: %d";
    public static final String MESSAGE_CALORIE_GOAL = "Your calorie to goal is: %d";


    public Statistics() {
    }

    private static Logger logger = Logger.getLogger(Statistics.class.getName());

    /**
     * Calculate netCalories and format exerciseCalories, foodCalories, calorieGoal
     * into strings.
     *
     * @param exerciseCalories is the total calories lost by exercising
     * @param foodCalories     is the total calories gained by consuming food
     * @param calorieGoal      is the goal set by the user
     * @return formatted strings.
     */
    public String[] getCaloriesReport(int exerciseCalories, int foodCalories, int calorieGoal) {

        int netCalories = foodCalories - exerciseCalories;
        int remainingCalories = calorieGoal - netCalories;
        return new String[]{String.format(MESSAGE_CALORIE_GAIN, foodCalories),
                String.format(MESSAGE_CALORIE_LOST, exerciseCalories),
                String.format(MESSAGE_CALORIE_NET, netCalories),
                String.format(MESSAGE_CALORIE_GOAL, remainingCalories),
                printCaloriesMessage(netCalories, calorieGoal)};
    }

    public String formatMessage(String... messages) {
        StringBuilder content = new StringBuilder("");
        for (String message : messages) {
            content.append(message).append(Ui.LS);
        }
        return content.toString();
    }


    public String printCaloriesMessage(int netCalories, int calorieGoal) {
        logger.log(Level.FINE, "preparing calories message");
        int calorieDifference = calorieGoal - netCalories;
        String message;
        if (calorieDifference > 0) {
            message = String.format("You are %s cal away from your goal", calorieDifference);
        } else if (calorieDifference < 0) {
            message = String.format("You have exceeded your calorie goal by %s cal ", calorieDifference);
        } else {
            assert calorieDifference == 0 : "calorieDifference should be 0";

            message = String.format("You have reached your calorie goal exactly. Good job!");
        }
        return message;
    }

}