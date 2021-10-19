package seedu.duke.item.exercise;

import seedu.duke.item.Item;

import java.time.LocalDate;

public class Exercise extends Item {
    public static final String EXERCISE_TYPE = "E";
    protected LocalDate date;

    /**
     * Constructor for exercise object.
     *
     * @param name     The name or description of the exercise.
     * @param calories The calorie burnt from the exercise.
     */
    public Exercise(String name, int calories) {
        super(name, calories);
        this.date = LocalDate.now();
    }

    /**
     * Constructor for the exercise object.
     *
     * @param name     The name or description of the exercise
     * @param calories The calorie burnt from the exercise
     * @param date     The date when the exercise is taken
     */
    public Exercise(String name, int calories, LocalDate date) {
        super(name, calories);
        this.date = date;
    }

    /**
     * Gets the date of the exercise taken.
     *
     * @return The date of the exercise done in LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Updates the date of the exercise taken.
     *
     * @param date The new date of the exercise done in LocalDate
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Converts the exercise to external file string format.
     *
     * @return Name, calorie and date of the exercise in string
     */
    @Override
    public String toFileTextString() {
        return EXERCISE_TYPE
                + super.toFileTextString()
                + FILE_TEXT_DELIMITER
                + this.getDate().format(DATE_FORMATTER);
    }
}
