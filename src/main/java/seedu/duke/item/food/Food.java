package seedu.duke.item.food;

import seedu.duke.item.Item;

public class Food extends Item {
    public static final String FOOD_TYPE = "F";

    /**
     * Constructor for the food object.
     *
     * @param name     The name or description of the food
     * @param calories The calorie of the food consumed
     */
    public Food(String name, int calories) {
        super(name, calories);
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
