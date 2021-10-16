package seedu.duke.item.exercise;

import seedu.duke.item.Item;

public class Exercise extends Item {
    public static final String EXERCISE_TYPE = "E";

    /**
     * Constructor for exercise object.
     *
     * @param name     Description of the exercise.
     * @param calories Calorie burnt from the exercise.
     */
    public Exercise(String name, int calories) {
        super(name, calories);
    }

    /**
     * Converts the exercise to external file string format.
     *
     * @return Name and calorie of the exercise in string
     */
    public String toFileTextString() {
        return EXERCISE_TYPE + super.toFileTextString();
    }
}
