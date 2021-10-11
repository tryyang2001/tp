package seedu.duke.commands;

/**
 * Represents the command that when executed, changes the value of height and weight in the Profile.
 */
public class CreateProfileCommand extends Command {
    public static final String COMMAND_WORD = "create";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD
            + " " + COMMAND_PREFIX_NAME + COMMAND_PREFIX_DELIMITER + "X "
            + COMMAND_PREFIX_HEIGHT + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_WEIGHT + COMMAND_PREFIX_DELIMITER + "Z"
            + QUOTATION + " where X is your name, Y is your height in CM and Z is your weight in KG.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to create your profile? Use this format:"
            + LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Hello %1$s! Your profile has been created!"
            + LS + "Your height is %2$scm."
            + LS + "Your weight is %3$skg.";

    private final String name;
    private final double weight;
    private final double height;


    public CreateProfileCommand(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public CommandResult execute() {
        //TODO: Call relevant method, catch exceptions and return CommandResult with error message if required
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.name, this.height, this.weight));
    }
}
