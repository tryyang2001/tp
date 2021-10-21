package seedu.duke.commands;

import seedu.duke.ui.Statistics;

/**
 * Represents the command that when executed, displays the overview of calorie statistics.
 */
public class OverviewCommand extends Command {
    public static final String COMMAND_WORD = "overview";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
    public static final String MESSAGE_SUCCESS = "This is an overview of your day:"
            + INDENTED_LS + "Calories consumed: %1$d calories"
            + INDENTED_LS + "Calories burnt: %2$d calories"
            + INDENTED_LS + "Net calories: %3$d calories"
            + LS + "%4$s";

    protected Statistics statistics = new Statistics();

    @Override
    public CommandResult execute() {
        int caloriesConsumed = super.foodItems.getTotalCalories();
        int caloriesLost = super.exerciseItems.getTotalCalories();
        int caloriesGoal = super.profile.getProfileCalorieGoal().getCalorieGoal();
        String[] calorieReport = this.statistics.getCaloriesReport(caloriesLost, caloriesConsumed, caloriesGoal);
        return new CommandResult(this.statistics.formatMessage(calorieReport));
    }
}
