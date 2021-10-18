package seedu.duke.commands;

/**
 * Represents the command that when executed, signals to the application to exit.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + QUOTATION;
    private static final String MESSAGE_SUCCESS = "Exiting Fitbot...." + LS
            + "Bye! Hope to see you again soon!!";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Determines if command is the Bye command so that the main program knows when to exit.
     */
    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }
}
