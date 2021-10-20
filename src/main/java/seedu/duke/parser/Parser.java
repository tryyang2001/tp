package seedu.duke.parser;

import seedu.duke.commands.Command;

/**
 * Represents a Parser that parses input arguments into their respective Command classes.
 */
public interface Parser {
    Command parse(String params);
}
