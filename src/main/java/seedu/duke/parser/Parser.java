package seedu.duke.parser;

import seedu.duke.commands.Command;

public interface Parser {
    Command parse(String params);
}
