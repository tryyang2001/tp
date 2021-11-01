package seedu.duke.main;


import seedu.duke.data.profile.attributes.Weight;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.parser.exceptions.ParamMissingException;
import seedu.duke.ui.Ui;

/**
 * Creates a Weight Class and prompts user for valid weight input.
 */
public class CreateWeight {

    public static final String MESSAGE_INTRO_WEIGHT = "What's your weight? (in kg)";
    public static final String MESSAGE_WEIGHT = "Your weight is %skg.";
    private Ui ui;
    private Weight weight = new Weight();

    public CreateWeight(Ui ui) {
        this.ui = ui;
    }

    /**
     * Creates a valid profile weight for the profile instance.
     *
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    public Weight createNewWeight() throws ParamMissingException {
        while (!weight.isValid()) {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_WEIGHT);
            try {
                String userInput = ui.getUserInput();
                ui.checkEmptyUserInput(userInput);
                double weightInput = Double.parseDouble(userInput);
                weight.setWeight(weightInput);
                if (weight.isValid()) {
                    ui.formatMessageWithTopDivider(
                            String.format(MESSAGE_WEIGHT,
                                    weight.getWeight()));
                } else {
                    ui.formatMessageFramedWithDivider(ProfileUtils.ERROR_WEIGHT);
                }
            } catch (NumberFormatException e) {
                ui.formatMessageWithTopDivider("Invalid input, please input a valid positive number");
            }
        }
        return weight;
    }
}
