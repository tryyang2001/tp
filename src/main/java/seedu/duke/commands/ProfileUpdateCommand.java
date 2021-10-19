package seedu.duke.commands;

import seedu.duke.profile.Profile;
import seedu.duke.profile.exceptions.InvalidCharacteristicException;


/**
 * Represents the command that when executed, changes the value of attributes in the Profile.
 */
public class ProfileUpdateCommand extends Command {
    //TODO: Update this
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_PROFILE
            + " " + COMMAND_PREFIX_NAME + COMMAND_PREFIX_DELIMITER + "W "
            + COMMAND_PREFIX_HEIGHT + COMMAND_PREFIX_DELIMITER + "X "
            + COMMAND_PREFIX_WEIGHT + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_GOAL + COMMAND_PREFIX_DELIMITER + "Z"
            + QUOTATION + " where W is your name, X is your height in CM,"
            + INDENTED_LS + "Y is your weight in KG and Z is your calorie goal.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to create your profile? Use this format:"
            + INDENTED_LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Hello %1$s! Your profile has been updated!"
            + INDENTED_LS + "Your height is %2$scm."
            + INDENTED_LS + "Your weight is %3$skg."
            + INDENTED_LS + "Your calories goal is %4$s cal."
            + INDENTED_LS + "Your gender is %5$s."
            + INDENTED_LS + "Your age is %6$s."
            + INDENTED_LS + "Your activity factor is %7$s.";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_NAME,
            COMMAND_PREFIX_HEIGHT,
            COMMAND_PREFIX_WEIGHT,
            COMMAND_PREFIX_GOAL,
            COMMAND_PREFIX_AGE,
            COMMAND_PREFIX_ACTIVITY_FACTOR,
            COMMAND_PREFIX_GENDER};


    private String name;
    private double weight;
    private double height;
    private int calorieGoal;
    private int age;
    private int activityFactor;
    private char gender;


    public ProfileUpdateCommand(String name, double height, double weight, int calorieGoal, int age,
                                int activityFactor, char gender) {
        this.name = name;
        this.height = height;
        this.weight =  weight;
        this.calorieGoal = calorieGoal;
        this.gender = gender;
        this.age = age;
        this.activityFactor = activityFactor;
    }

    @Override
    public CommandResult execute() {
        try {
            this.name = name.equals(NULL_STRING)? super.profile.getName() : name;
            this.height = height == NULL_DOUBLE ? super.profile.getHeight() : height;
            this.weight = weight == NULL_DOUBLE ? super.profile.getWeight() : weight;
            this.calorieGoal = calorieGoal == NULL_INT ? super.profile.getCalorieGoal() : calorieGoal;
            this.gender = gender == NULL_CHAR ? super.profile.getGender() : gender;
            this.age = age == NULL_INT ? super.profile.getAge() : age;
            this.activityFactor = activityFactor == NULL_INT ? super.profile.getActivityFactor() : activityFactor;

            Profile tempProfile = new Profile(this.name, this.height, this.weight, this.calorieGoal);
            super.profile.setProfile(this.name, this.height, this.weight,
                    this.calorieGoal);

            //TODO: After storage of profile is updated
//            Profile tempProfile = new Profile(this.name, this.height, this.weight, this.calorieGoal,
//                    this.gender, this.age, this.activityFactor);
//            super.profile.setProfile(this.name, this.height, this.weight,
//                    this.calorieGoal, this.gender, this.age, this.activityFactor);

            return new CommandResult(String.format(
                    MESSAGE_SUCCESS,
                    super.profile.getName(),
                    super.profile.getHeight(),
                    super.profile.getWeight(),
                    super.profile.getCalorieGoal(),
                    super.profile.getGender(),
                    super.profile.getAge(),
                    super.profile.getActivityFactor()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
