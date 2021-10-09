package seedu.duke.commands;

/**
 * Represents the command that when executed, changes the value of name in the Profile.
 */
public class ChangeNameCommand extends Command {
    public static final String COMMAND_WORD = "name";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD
            + " X" + QUOTATION + "where X is your name";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format!"
            + "Trying to update your name? Use this format:" + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Your name has been updated!" + LS + "Hello %s!";

    private final String name;

    public ChangeNameCommand(String name) {
        this.name = name;
    }

    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return InvalidCommand if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.name));
    }
}
