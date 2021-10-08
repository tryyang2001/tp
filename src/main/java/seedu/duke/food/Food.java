package seedu.duke.food;

public class Food {
    private String name;
    private int calorie;

    public Food(String name, int calorie) {
        this.name = name;
        this.calorie = calorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie() {
        return calorie;
    }

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
