package seedu.duke.commands;

import seedu.duke.food.FoodList;
import seedu.duke.ui.Statistics;
import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, displays the overview of calorie statistics.
 */
public class OverviewCommand extends Command {
    public static final String COMMAND_WORD = "overview";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    public static final String MESSAGE_SUCCESS = "This is an overview of your day:" + Ui.LS
            + "Calories consumed: %1$d calories" + Ui.LS
            + "Calories lost: %2$d calories" + Ui.LS
            + "Net calories: %3$d calories" + Ui.LS + "%4$s";

    @Override
    public CommandResult execute() {
        //TODO: Implement total calories method for ExerciseList, FoodList
        int caloriesConsumed = this.foodItems.totalCalories();
        int caloriesLost = this.exerciseItems.totalCalorie();
        int caloriesGoal = this.profile.getCalorieGoal();
        String[] calorieReport = this.statistics.getCaloriesReport(caloriesLost, caloriesConsumed, caloriesGoal);
        return new CommandResult(this.statistics.formatMessage(calorieReport));
    }
}
