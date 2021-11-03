package seedu.duke.main;

import seedu.duke.data.profile.attributes.Name;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.commands.CommandResult;
import seedu.duke.logic.parser.exceptions.ParamMissingException;
import seedu.duke.ui.Ui;

/**
 * Creates a Name Class and prompts user for valid name input.
 */
public class CreateName extends AttributeCreator {

    public static final String MESSAGE_BYE_DETECTED = "The command word 'bye' is detected.\n "
            + "Do you wish to exit this program or to set yor name as %s.\n"
            + "Type '1' if you wish to exit. Type '2' if you wish to set your name as your input.\n "
            + "Else, type in any key for Fitbot to ask for your name again.";
    public static final String CHECK_REPEAT_MESSAGE = "";
    private Name name = new Name();
    public static final String MESSAGE_INTRO_NAME = "What's your name?";
    public static final String MESSAGE_NAME = "Nice name you have there! Hello %s";

    public CreateName(Ui ui) {
        super(ui);
    }

    /**
     * Creates a valid profile name for the profile instance.
     *
     * @throws ParamMissingException if user input a string of 0 characters.
     */
    public Name createNewName() throws ParamMissingException {
        while (!name.isValid()) {
            ui.formatMessageWithBottomDivider(MESSAGE_INTRO_NAME);
            String userInput = ui.getUserInput().trim();
            ui.checkEmptyUserInput(userInput);
            userInput = checkAndConfirmInputBye(userInput);
            if (userInput.equals(CHECK_REPEAT_MESSAGE)) {
                ui.formatMessageWithTopDivider();
                System.out.println(userInput);
                continue;
            }
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

    private String checkAndConfirmInputBye(String userInput) {
        boolean isBye = isBye(userInput);
        if (!isBye) {
            return userInput;
        }
        ui.formatMessageFramedWithDivider(String.format(MESSAGE_BYE_DETECTED, userInput));
        String userConfirmByeInput = ui.getUserInput().trim();
        System.out.println(userConfirmByeInput);
        if (userConfirmByeInput.equals("1")) {
            exit();
        } else if (userConfirmByeInput.equals("2")) {
            return userInput;
        }
        assert !userConfirmByeInput.equals("1") && !userConfirmByeInput.equals("2") : "other inputs";
        return CHECK_REPEAT_MESSAGE;
    }

}
