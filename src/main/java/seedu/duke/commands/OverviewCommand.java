package seedu.duke.commands;

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


    @Override
    public CommandResult execute() {
        int foodCalories = super.foodItems.getTotalCalories();
        int exerciseCalories = super.exerciseItems.getTotalCalories();
        int netCalories = super.profile.calculateNetCalories(foodCalories, exerciseCalories);
        //TODO: get goal status
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                foodCalories, exerciseCalories, netCalories,
                "goal_status (placeholder)"));
    }
}
