package seedu.duke.main;

import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.attributes.Age;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.parser.exceptions.ParamMissingException;
import seedu.duke.ui.Ui;

/**
 * Creates an Age Class and prompts user for valid age input.
 */
public class CreateAge {

    private Ui ui;
    private Age age = new Age();
    private static final String MESSAGE_INTRO_AGE = "How old are you?";
    private static final String MESSAGE_AGE = "You are %s years old.";
    private static final String MESSAGE_INVALID_POSITIVE_INT_INPUT = "Invalid input, "
            + "please input a valid positive whole number";


    public CreateAge(Ui ui) {
        this.ui = ui;
    }

    /**
     * Creates a valid profile age for the profile instance.
     *
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    public Age createNewAge() throws ParamMissingException {
        while (!age.isValid()) {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_AGE);
            try {
                String userInput = ui.getUserInput();
                ui.checkEmptyUserInput(userInput);
                int ageInput = Integer.parseInt(userInput);
                age.setAge(ageInput);
                if (age.isValid()) {
                    ui.formatMessageWithTopDivider(
                            String.format(MESSAGE_AGE,
                                    age.getAge()));
                } else {
                    ui.formatMessageFramedWithDivider(ProfileUtils.ERROR_AGE);
                }
            } catch (NumberFormatException e) {
                ui.formatMessageWithTopDivider(MESSAGE_INVALID_POSITIVE_INT_INPUT);
            }
        }
        return age;
    }
}
