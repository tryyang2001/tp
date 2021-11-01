package seedu.duke.main;

import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.attributes.Height;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.parser.exceptions.ParamMissingException;
import seedu.duke.ui.Ui;

/**
 * Creates a Height Class and prompts user for valid height input.
 */
public class CreateHeight {

    private Ui ui;
    private static final String MESSAGE_HEIGHT = "Your height is %scm.";
    private static final String MESSAGE_INTRO_HEIGHT = "What's your height? (in cm)";
    private static final String MESSAGE_INVALID_POSITIVE_DOUBLE_INPUT = "Invalid input,"
            + " please input a valid positive number";
    private Height height = new Height();

    public CreateHeight(Ui ui) {
        this.ui = ui;
    }

    /**
     * Creates a valid profile height for the profile instance.
     *
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    public Height createNewHeight() throws ParamMissingException {
        while (!height.isValid()) {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_HEIGHT);
            try {
                String userInput = ui.getUserInput();
                ui.checkEmptyUserInput(userInput);
                double heightInput = Double.parseDouble(userInput);
                height.setHeight(heightInput);
                if (height.isValid()) {
                    ui.formatMessageWithTopDivider(
                            String.format(MESSAGE_HEIGHT,
                                    height.getHeight()));
                } else {
                    ui.formatMessageFramedWithDivider(ProfileUtils.ERROR_HEIGHT);
                }
            } catch (NumberFormatException e) {
                ui.formatMessageWithTopDivider(MESSAGE_INVALID_POSITIVE_DOUBLE_INPUT);
            }
        }
        return height;
    }
}
