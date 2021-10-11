package seedu.duke.commands;

import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, displays the list of available commands to the user.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + Ui.QUOTATION;
    private static final String MESSAGE_SUCCESS = "These are the available commands:" + Ui.LS + "%s";

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, "list of commands (placeholder)"));
    }
}
