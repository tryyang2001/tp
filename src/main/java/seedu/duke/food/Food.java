package seedu.duke.food;

public class Food {
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
     * Prints the food in the format of name plus the food calorie.
     */
    @Override
    public String toString() {
        return this.getName() + " (" + this.getCalories() + " cal)";
    }
}
