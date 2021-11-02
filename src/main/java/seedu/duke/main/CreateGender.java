package seedu.duke.main;

import seedu.duke.data.profile.attributes.Gender;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.ui.Ui;

/**
 * Creates a Gender Class and prompts user for valid gender input.
 */
public class CreateGender {

    private Gender gender = new Gender();
    private Ui ui;
    public static final String MESSAGE_INTRO_GENDER = "What is your gender?(If you are a male, type 'm'"
            + ", if you are a female, type 'f')";
    public static final char MALE_CHAR = 'M';
    public static final String MESSAGE_MALE = "You are a male.";
    public static final String MESSAGE_FEMALE = "You are a female.";

    public CreateGender(Ui ui) {
        this.ui = ui;
    }

    /**
     * Creates a valid profile gender for the profile instance.
     *
     * @throws MissingParamException if user input a string of 0 characters.
     */
    public Gender createNewGender() {
        while (!gender.isValid()) {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_GENDER);
            String userInput = ui.getUserInput();
            if (userInput.length() == 1) {
                char genderInput = userInput.charAt(0);
                gender.setGender(genderInput);
            }
            if (gender.isValid()) {
                ui.formatMessageWithTopDivider(
                        getGenderMessage(gender.getGender()));
            } else {
                ui.formatMessageWithTopDivider(ProfileUtils.ERROR_GENDER);
            }


        }
        return gender;
    }

    private String getGenderMessage(char gender) {
        return gender == MALE_CHAR ? MESSAGE_MALE : MESSAGE_FEMALE;
    }
}
