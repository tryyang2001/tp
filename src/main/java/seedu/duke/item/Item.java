package seedu.duke.item;

public abstract class Item {
    public static final String FILE_TEXT_DELIMITER = "|";
    protected String name;
    protected int calories;

    /**
     * Constructor for item object.
     *
     * @param name     The name or description of the item
     * @param calories The calorie intake/burnt from the item
     */
    public Item(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    /**
     * Gets the name or description of the item.
     *
     * @return The name or description of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the calorie intake/burnt for the item.
     *
     * @return The calorie intake/burnt from the item
     */
    public int getCalories() {
        return this.calories;
    }

    /**
     * Updates the name or description of the item.
     *
     * @param name The new name or description of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates the calorie intake/burnt from the item.
     *
     * @param calories The new calorie intake/burnt from the item
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Converts the item in correct string format.
     *
     * @return The item name and calorie displayed in string
     */
    @Override
    public String toString() {
        return this.getName() + " (" + this.getCalories() + " cal)";
    }

    /**
     * Converts the item to external file string format.
     *
     * @return Name and calorie of the item in string
     */
    public String toFileTextString() {
        return FILE_TEXT_DELIMITER + this.getName() + FILE_TEXT_DELIMITER + this.getCalories();
    }
}
