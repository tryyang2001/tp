package seedu.duke.commands;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;

/**
 * Represents the command that when executed, changes the value of weight in the Profile.
 */
public class ChangeWeightCommand extends Command {
    public static final String COMMAND_WORD = "weight";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD
            + " X" + QUOTATION + ", where X is your weight in KG";
    public static final String MESSAGE_SUCCESS = "Your weight has been updated!" + LS + "Your weight is %skg.";

    private final double weight;

    public ChangeWeightCommand(double weight) {
        this.weight = weight;
    }

    @Override
    public CommandResult execute() {
        try {
            super.profile.setWeight(this.weight);
            return new CommandResult(String.format(MESSAGE_SUCCESS, super.profile.getWeight()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
