package seedu.duke.main;

import seedu.duke.data.profile.attributes.Name;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.ui.Ui;

/**
 * Creates a Name Class and prompts user for valid name input.
 */
public class CreateName {

    private Ui ui;
    private Name name = new Name();
    public static final String MESSAGE_INTRO_NAME = "What's your name?";
    public static final String MESSAGE_NAME = "Nice name you have there! Hello %s";

    public CreateName(Ui ui) {
        this.ui = ui;
    }

    /**
     * Creates a valid profile name for the profile instance.
     *
     * @throws MissingParamException if user input a string of 0 characters.
     */
    public Name createNewName() throws MissingParamException {
        while (!name.isValid()) {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_NAME);
            String userInput = ui.getUserInput();
            ui.checkEmptyUserInput(userInput);
            name.setName(userInput);
            if (name.isValid()) {
                ui.formatMessageWithTopDivider(
                        String.format(MESSAGE_NAME,
                                name.getName()));
            } else {
                ui.formatMessageWithTopDivider(ProfileUtils.ERROR_NAME);
            }
        }
        assert name.isValid() : " name is valid";
        return name;
    }
}
