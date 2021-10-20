package seedu.duke.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public abstract class DecoderNew {
    protected static final String FILE_TEXT_DELIMITER = "\\|";
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    protected static final Logger logger = Logger.getLogger(Decoder.class.getName());

    protected LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }

    protected LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }
}
