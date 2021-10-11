package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.parser.Parser;

import java.util.Scanner;

/**
 * Main class of Fitbot.
 * Initialises the application and starts interaction with user.
 */
public class Main {

    //TODO: Initialise all required objects (Ui, ExerciseList, FoodList, Profile, Storage)
    private static Parser parser; //Parser should be moved into UI
    private static Scanner scanner; //Scanner should be moved into UI

    private static final String MESSAGE_BYE = "Bye! Hope to see you again soon!!";

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        new Main().run(args);
    }

    /**
     * Runs the application until command is given to exit it.
     **/
    private void run(String[] args) {
        start();
        enterTaskModeUntilByeCommand();
        exit();
    }

    /**
     * Initialises the application by creating the required objects and (to be implemented) loading data from the
     * storage file, then showing the welcome message.
     */
    private void start() {
        parser = new Parser();
        scanner = new Scanner(System.in);
        //TODO: Instantiate all required objects
    }

    public String getUserInput() {  //To be moved into UI
        return scanner.nextLine();
    }

    public void showMessages(String... lines) { //To be moved into UI
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Reads the user input and executes appropriate command.
     * Runs indefinitely until user inputs the Bye command.
     */
    private void enterTaskModeUntilByeCommand() {
        Command command;
        do {
            String userInput = getUserInput();
            command = parser.parseCommand(userInput);
            CommandResult result = executeCommand(command);
            showMessages(result.toString());
        } while (!ByeCommand.isBye(command));
    }

    /**
     * Executes the given Command and (to be implemented) calls for storage operation if required.
     *
     * @param command Command to be executed
     * @return CommandResult representing result of execution of the command
     */
    private CommandResult executeCommand(Command command) {
        //TODO: command.setData();
        CommandResult result = command.execute();
        return result;
    }

    /**
     * Exits the application.
     */
    private void exit() {
        System.out.println(MESSAGE_BYE);
        System.exit(0);
    }


}
