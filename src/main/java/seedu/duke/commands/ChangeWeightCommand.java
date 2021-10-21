package seedu.duke.commands;

import seedu.duke.profile.Profile;
import seedu.duke.profile.attributes.Weight;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;

/**
 * Represents the command that when executed, changes the value of weight in the Profile.
 */
public class ChangeWeightCommand extends Command {
    public static final String COMMAND_WORD = "weight";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD
            + " X" + QUOTATION + ", where X is your weight in KG";
    public static final String MESSAGE_SUCCESS = "Your weight has been updated!" + LS + "Your weight is %skg.";


    private final Weight weight = new Weight();

    public ChangeWeightCommand(double weight) {
        this.weight.setWeight(weight);
    }

    private void checkIfCommandShouldExecute() throws InvalidCharacteristicException {
        if (!weight.isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_WEIGHT);
        }
    }

    @Override
    public CommandResult execute() {
        try {
            checkIfCommandShouldExecute();
            super.profile.setProfileWeight(this.weight);
            return new CommandResult(String.format(MESSAGE_SUCCESS, super.profile.getProfileWeight().getWeight()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
