package seedu.duke.logic.commands;

import seedu.duke.ui.Statistics;

/**
 * Represents the command that when executed, displays the overview of calorie statistics.
 */
public class OverviewCommand extends Command {
    public static final String COMMAND_WORD = "overview";
    public static final String MESSAGE_COMMAND_FORMAT =
            CommandMessages.QUOTATION + COMMAND_WORD + CommandMessages.QUOTATION;
    protected Statistics statistics;


    @Override
    public CommandResult execute() {
        statistics = new Statistics(super.foodItems, super.exerciseItems, super.profile);
        return new CommandResult(this.statistics.overviewSummary());
    }
}
