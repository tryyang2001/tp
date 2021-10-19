package seedu.duke.commands;

import seedu.duke.profile.Profile;
import seedu.duke.profile.attributes.ActivityFactor;
import seedu.duke.profile.attributes.Age;
import seedu.duke.profile.attributes.CalorieGoal;

import seedu.duke.profile.attributes.Gender;
import seedu.duke.profile.attributes.Height;
import seedu.duke.profile.attributes.Name;
import seedu.duke.profile.attributes.Weight;
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
    public static final String MESSAGE_SUCCESS = "Hello %1$s! Your profile has been updated!"
            + INDENTED_LS + "Your height is %2$scm."
            + INDENTED_LS + "Your weight is %3$skg."
            + INDENTED_LS + "Your gender is %4$s."
            + INDENTED_LS + "Your age is %5$s."
            + INDENTED_LS + "Your calories goal is %6$s cal."
            + INDENTED_LS + "Your activity factor is %7$s.";
    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_NAME,
            COMMAND_PREFIX_HEIGHT,
            COMMAND_PREFIX_WEIGHT,
            COMMAND_PREFIX_GOAL,
            COMMAND_PREFIX_AGE,
            COMMAND_PREFIX_ACTIVITY_FACTOR,
            COMMAND_PREFIX_GENDER};


    private Name name = new Name();
    private Weight weight = new Weight();
    private Height height = new Height();
    private CalorieGoal calorieGoal = new CalorieGoal();
    private Age age = new Age();
    private ActivityFactor activityFactor = new ActivityFactor();
    private Gender gender = new Gender();


    public ProfileUpdateCommand(String name, double height, double weight, int calorieGoal, int age,
                                int activityFactor, char gender) {
        this.name.setName(name);
        this.height.setHeight(height);
        this.weight.setWeight(weight);
        this.calorieGoal.setCalorieGoal(calorieGoal);
        this.gender.setGender(gender);
        this.age.setAge(age);
        this.activityFactor.setActivityFactor(activityFactor);
    }

    private void checkIfCommandShouldExecute() throws InvalidCharacteristicException {
        if (!height.isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_HEIGHT);
        }
        if (!weight.isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_WEIGHT);
        }
        if (!gender.isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_GENDER);
        }
        if (!age.isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_AGE);
        }
        if (!calorieGoal.isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_CALORIE_GOAL);
        }
        if (!activityFactor.isValid()) {
            throw new InvalidCharacteristicException(Profile.ERROR_ACTIVITY_FACTOR);
        }
    }

    @Override
    public CommandResult execute() {
        try {
            this.name = name.getName().equals(NULL_STRING) ? super.profile.getProfileName() : name;
            this.height = height.getHeight() == NULL_DOUBLE ? super.profile.getProfileHeight() : height;
            this.weight = weight.getWeight() == NULL_DOUBLE ? super.profile.getProfileWeight() : weight;
            this.gender = gender.getGender() == NULL_CHAR ? super.profile.getProfileGender() : gender;
            this.age = age.getAge() == NULL_INT ? super.profile.getProfileAge() : age;
            this.calorieGoal = calorieGoal.getCalorieGoal() == NULL_INT
                    ? super.profile.getProfileCalorieGoal()
                    : calorieGoal;
            this.activityFactor = activityFactor.getActivityFactor() == NULL_INT
                    ? super.profile.getProfileActivityFactor()
                    : activityFactor;

            checkIfCommandShouldExecute();
            super.profile = new Profile(this.name, this.height, this.weight,
                    this.gender, this.age, this.calorieGoal, this.activityFactor);


            return new CommandResult(String.format(
                    MESSAGE_SUCCESS,
                    name.getName(),
                    height.getHeight(),
                    weight.getWeight(),
                    gender.getGender(),
                    age.getAge(),
                    calorieGoal.getCalorieGoal(),
                    activityFactor.getActivityFactor()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
