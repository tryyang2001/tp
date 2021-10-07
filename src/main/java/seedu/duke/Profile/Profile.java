package seedu.duke.Profile;

public class Profile {
    private int height;
    private int weight;
    private int calorieGoal;

    public Profile(int height, int weight) {
        setHeight(height);
        setWeight(weight);
        setCalorieGoal(0); //Initialize to 0 first
    }

    public Profile() {
        setHeight(0);
        setWeight(0);
        setCalorieGoal(0);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCalorieGoal(int calorieGoal) {
        this.calorieGoal = calorieGoal;
    }

    public double calculateBMI() {
        double heightInM = ((double) height) / 100.0;
        double bmi = Math.round((weight/(Math.pow(heightInM,2)))*10) / 10.0;
        return bmi;
    }

    public static double calculateBMI(int height, int weight) {
        double heightInM = ((double) height) / 100.0;
        double bmi = Math.round((weight/(Math.pow(heightInM,2)))*10) / 10.0;
        return bmi;
    }

    public int calculateNetCalories(int foodCalories, int exerciseCalories) {
        return foodCalories - exerciseCalories;
    }

}
