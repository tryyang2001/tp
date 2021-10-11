package seedu.duke.exercise;

public class Exercise {
    private String name;
    private int calorie;
    public static final String EXERCISE_TYPE = "E";
    public static final String FILE_TEXT_DELIMITER = "|";

    /**
     * Constructor for exercise object.
     *
     * @param name    Description of the exercise.
     * @param calorie Calorie burnt from the exercise.
     */
    public Exercise(String name, int calorie) {
        this.name = name;
        this.calorie = calorie;
    }

    /**
     * Returns the name of the exercise.
     *
     * @return Name of the exercise.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the calorie burnt from the exercise.
     *
     * @return Calorie burnt from the exercise.
     */
    public int getCalorie() {
        return calorie;
    }

    /**
     * Sets or updates the name of the exercise.
     *
     * @param name New name of the exercise to be changed to.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets or updates the calorie burnt from the exercise.
     *
     * @param calorie New calorie burnt from the exercise.
     */
    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    /**
     * Prints the name of exercise along with its calorie.
     *
     * @return Name and calorie of exercise in display format.
     */
    @Override
    public String toString() {
        return this.getName() + " (" + this.getCalorie() + " cal)";
    }

    /**
     * Prints exercise to external file format.
     *
     * @return Name and calorie of exercise in file format.
     */
    public String toFileTextString(){
        return EXERCISE_TYPE + FILE_TEXT_DELIMITER + this.getName() + FILE_TEXT_DELIMITER + this.getCalorie();
    }

    //todo: handle negative calorie exception
}
