package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.logic.parser.exceptions.MissingParamException;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    @Test
    void checkEmptyUserInput_emptyStringInput_throwsException() {
        Ui ui = new Ui();
        assertThrows(MissingParamException.class, () -> ui.checkEmptyUserInput(""));
    }

    @Test
    void checkEmptyUserInput_oneStringInput_doesNotThrowException() {
        Ui ui = new Ui();
        assertDoesNotThrow(() ->  ui.checkEmptyUserInput("1") );
        assertDoesNotThrow(() ->  ui.checkEmptyUserInput("this") );
        assertDoesNotThrow(() ->  ui.checkEmptyUserInput(" ") );
    }
}