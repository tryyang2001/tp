package seedu.duke.ui;

import seedu.duke.commands.HelpCommand;

import java.lang.System;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This class deals with interaction with user on CLI.
 * Also helps to change color of output if required.
 */
public class Ui {
    // to delete on v2.1
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_GRAY = "\u001B[90m";
    public static final String FULL_BLOCK = "█";
    public static final String HALF_BLOCK = "▌";
    public static final int MAX_BAR = 16;
    public static final int BAR_WIDTH = 10;
    public static final String SPACE = " ";
    public static final String TAB = "\t";
    public static final String DIVIDER = "___________________________________________"
            + "_______________________________________________________________";
    public static final String LS = System.lineSeparator();
    public static final String INDENTED_LS = LS + TAB;
    public static final String EMOJI_1 = " ᕦ(ò_óˇ)";
    public static final String FITBOT_V0 = "  ______ _ _   _           _"
            + LS
            + " |  ____(_) | | |         | |"
            + LS
            + " | |__   _| |_| |__   ___ | |_"
            + LS
            + " |  __| | | __| '_ \\ / _ \\| __|"
            + LS
            + " | |    | | |_| |_) | (_) | |_"
            + LS
            + " |_|    |_|\\__|_.__/ \\___/ \\__|";



    public static final String QUOTATION = "\"";
    public static final String MESSAGE_WELCOME = "Welcome to Fitbot, a desktop app that helps university students"
            + " who are looking to keep track of their "
            + LS
            + "fitness and health. Please type a command or view the list of "
            + "available commands by typing " + HelpCommand.MESSAGE_COMMAND_FORMAT + ".";
    public static final String FORMAT_HEADER = "Format: ";
    public static final String FORMAT_PLACEHOLDER = "        ";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.printStartApplicationPage();
    }

    public String getUserInput() {  //To be moved into UI
        return scanner.nextLine();
    }

    private static Logger logger = Logger.getLogger(Ui.class.getName());

    /**
     * Surround strings with lines for user to differentiate results.
     *
     * @param messages is the strings that need to be printed on CLI
     */
    public void formatMessageFramedWithDivider(String... messages) {
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(DIVIDER);
    }


    public void printStartApplicationPage() {
        logger.log(Level.FINE, "start of application");
        System.out.println(FITBOT_V0 + EMOJI_1 + LS + MESSAGE_WELCOME);
    }

}
