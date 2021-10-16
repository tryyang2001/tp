package seedu.duke.item.food;

import seedu.duke.item.Item;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Food extends Item {
    public static final String FOOD_TYPE = "F";
    protected LocalDateTime dateTime;

    /**
     * Constructor for the food object.
     *
     * @param name     The name or description of the food
     * @param calories The calorie of the food consumed
     */
    public Food(String name, int calories) {
        super(name, calories);
        this.dateTime = LocalDateTime.now();
    }

    /**
     * /**
     * Constructor for the food object.
     *
     * @param name     The name or description of the food
     * @param calories The calorie intake from the food
     * @param dateTime The date and time when the food is consumed
     */
    public Food(String name, int calories, LocalDateTime dateTime) {
        super(name, calories);
        this.dateTime = dateTime;
    }

    /**
     * Gets the date and time of the food consumed.
     *
     * @return The date and time of the food consumed in LocalDateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Updates the date and time of the food consumed.
     *
     * @param dateTime The new date and time of the food consumed in LocalDateTime
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets the date of the food consumed.
     *
     * @return The date of the food consumed in LocalDate
     */
    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    /**
     * Gets the time of the food consumed.
     *
     * @return The time of the food consumed in LocalTime
     */
    public LocalTime getTime() {
        return this.dateTime.toLocalTime();
    }

    /**
     * Converts the food to external file string format.
     *
     * @return Name and calorie of the food in string
     */
    public String toFileTextString() {
        return FOOD_TYPE + super.toFileTextString();
    }
}
