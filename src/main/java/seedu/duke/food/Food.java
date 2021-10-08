package seedu.duke.food;

public class Food {
    private String name;
    private int calorie;

    /**
     * Constructor for the food object.
     *
     * @param name The name or description of the food
     * @param calorie The calorie of the food consumed
     */
    public Food(String name, int calorie) {
        this.name = name;
        this.calorie = calorie;
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
    public int getCalorie() {
        return calorie;
    }

    /**
     * Sets or updates the amount of calorie for the food consumed.
     *
     * @param calorie The new amount of calorie in integer
     */
    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    /**
     * Prints the food in the format of name plus the food calorie.
     */
    @Override
    public String toString() {
        return this.getName() + " (" + this.getCalorie() + " cal)";
    }
}
