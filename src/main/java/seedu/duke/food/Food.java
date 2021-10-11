package seedu.duke.food;

public class Food {
    public static final String FOOD_TYPE = "F";
    public static final String FILE_TEXT_DELIMITER = "|";
    private String name;
    private int calories;

    /**
     * Constructor for the food object.
     *
     * @param name The name or description of the food
     * @param calories The calorie of the food consumed
     */
    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    /**
     * Gets the name of the food.
     *
     * @return The name description of the food item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets or updates the name of the food.
     *
     * @param name The new name description of the food item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the amount of calorie of the food.
     *
     * @return The amount of calorie in integer
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Sets or updates the amount of calorie for the food consumed.
     *
     * @param calories The new amount of calorie in integer
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Prints the food item in the format of food name description + (calories consumed).
     * @return The food item with name and calories in string format
     */
    @Override
    public String toString() {
        return this.getName() + " (" + this.getCalories() + " cal)";
    }

    /**
     * Converts the food item to string with attributes separated by delimiter for data storage.
     * @return The food item with food type, name and calories in the string format with delimiters
     */
    public String toFileTextString() {
        return FOOD_TYPE + FILE_TEXT_DELIMITER + this.getName() + FILE_TEXT_DELIMITER + this.getCalories();
    }
}
