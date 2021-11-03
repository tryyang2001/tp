package seedu.duke.main;

import seedu.duke.ui.Ui;

public class AttributeCreator {


    protected Ui ui;

    AttributeCreator(Ui ui) {
        this.ui = ui;
    }

    // Followed the format of Bye command to standardise bye message.
    private static final String MESSAGE_SUCCESS = "Exiting Fitbot...." + Ui.LS
            + "Bye! Hope to see you again soon!!";

    protected boolean isBye(String userInput) {
        return userInput.toLowerCase().equals("bye");
    }

    protected void exit() {
        ui.formatMessageFramedWithDivider(MESSAGE_SUCCESS);
        System.exit(0);
    }

    protected void confirmInputBye(String userInput) {
        if (isBye(userInput.trim())) {
            exit();
        }
    }

}
