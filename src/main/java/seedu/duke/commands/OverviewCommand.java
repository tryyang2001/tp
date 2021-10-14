package seedu.duke.commands;

import seedu.duke.ui.Statistics;
import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, displays the overview of calorie statistics.
 */
public class OverviewCommand extends Command {
    public static final String COMMAND_WORD = "overview";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    public static final String MESSAGE_SUCCESS = "This is an overview of your day:"
            + Ui.INDENTED_LS + "Calories consumed: %1$d calories"
            + Ui.INDENTED_LS + "Calories burnt: %2$d calories"
            + Ui.INDENTED_LS + "Net calories: %3$d calories"
            + Ui.LS + "%4$s";
    public static final String MESSAGE_HELP = "overview -- shows relevant calories statistics." + Ui.LS
                 + Ui.FORMAT_HEADER + MESSAGE_COMMAND_FORMAT + Ui.LS;
    protected Statistics statistics = new Statistics();

    @Override
    public CommandResult execute() {
        int caloriesConsumed = super.foodItems.getTotalCalories();
        int caloriesLost = super.exerciseItems.getTotalCalories();
        int caloriesGoal = super.profile.getCalorieGoal();
        String[] calorieReport = this.statistics.getCaloriesReport(caloriesLost, caloriesConsumed, caloriesGoal);
        return new CommandResult(this.statistics.formatMessage(calorieReport));
    }
}
