package seedu.duke.data;

import seedu.duke.data.item.ItemBank;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.exercise.FutureExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;

public class DataManager {

    private ExerciseList exerciseItems;
    private FutureExerciseList futureExerciseItems;
    private FoodList foodItems;
    private ItemBank exerciseBank;
    private ItemBank foodBank;
    private Profile profile;

    /**
     * Handles the different types of data going into and out of the bot.
     * Used for storage purposes.
     *
     * @param exerciseItems       Exercises items to be initialized
     * @param futureExerciseItems Upcoming exercise items to be initialized
     * @param foodItems           Food items to be initialized
     * @param exerciseBank        Exercise bank items to be initialized
     * @param foodBank            Food bank items to be initialized
     * @param profile             Profile to be initialized
     */
    public DataManager(ExerciseList exerciseItems, FutureExerciseList futureExerciseItems, FoodList foodItems,
                       ItemBank exerciseBank, ItemBank foodBank, Profile profile) {
        this.exerciseItems = exerciseItems;
        this.futureExerciseItems = futureExerciseItems;
        this.foodItems = foodItems;
        this.exerciseBank = exerciseBank;
        this.foodBank = foodBank;
        this.profile = profile;
    }

    /**
     * For initialization at the start of the application.
     */
    public DataManager() {
        this.exerciseItems = new ExerciseList();
        this.futureExerciseItems = new FutureExerciseList();
        this.foodItems = new FoodList();
        this.exerciseBank = new ItemBank();
        this.foodBank = new ItemBank();
        this.profile = new Profile();
    }

    //====================ExerciseList methods=========================

    /**
     * Replaces exercise list with data in {@code exerciseItems}.
     *
     * @param exercisesItems Exercise list to be set
     */
    public void setExerciseItems(ExerciseList exercisesItems) {
        this.exerciseItems = exercisesItems;
    }

    /**
     * Returns the exercise list.
     *
     * @return exercise list in DataManager object
     */
    public ExerciseList getExerciseItems() {
        return this.exerciseItems;
    }

    //====================FutureExerciseList methods===================

    /**
     * Replaces upcoming exercises with data in {@code futureExerciseItems}.
     *
     * @param futureExerciseItems Future exercise items to be set
     */
    public void setFutureExerciseItems(FutureExerciseList futureExerciseItems) {
        this.futureExerciseItems = futureExerciseItems;
    }

    /**
     * Returns the future exercise items.
     *
     * @return future exercise list in DataManager object
     */
    public FutureExerciseList getFutureExerciseItems() {
        return this.futureExerciseItems;
    }

    //========================FoodList methods=============================

    /**
     * Replaces food items with data in {@code foodItems}.
     *
     * @param foodItems Food list to be set
     */
    public void setFoodItems(FoodList foodItems) {
        this.foodItems = foodItems;
    }

    /**
     * Returns the food items.
     *
     * @return food items in DataManager object
     */
    public FoodList getFoodItems() {
        return this.foodItems;
    }

    //=====================FoodBank methods============================

    /**
     * Replaces food bank with data in {@code foodBank}.
     *
     * @param foodBank Food bank to be set
     */
    public void setFoodBank(ItemBank foodBank) {
        this.foodBank = foodBank;
    }

    /**
     * Returns food bank items.
     *
     * @return food bank items in DataManager object
     */
    public ItemBank getFoodBank() {
        return this.foodBank;
    }

    //=====================ExerciseBank methods==========================

    /**
     * Replaces exercise bank with data in {@code exerciseBank}.
     *
     * @param exerciseBank Exercise bank to be set
     */
    public void setExerciseBank(ItemBank exerciseBank) {
        this.exerciseBank = exerciseBank;
    }

    /**
     * Returns exercise bank items.
     *
     * @return exercise bank items in DataManager object
     */
    public ItemBank getExerciseBank() {
        return this.exerciseBank;
    }

    //=====================Profile method================================

    /**
     * Replaces profile with data in {@code profile}.
     *
     * @param profile profile to be set
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Returns profile.
     *
     * @return profile in DataManager object
     */
    public Profile getProfile() {
        return this.profile;
    }

}
