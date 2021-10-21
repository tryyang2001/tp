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
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_PROFILE
            + " " + COMMAND_PREFIX_NAME + COMMAND_PREFIX_DELIMITER + "NAME "
            + COMMAND_PREFIX_HEIGHT + COMMAND_PREFIX_DELIMITER + "HEIGHT(CM) "
            + COMMAND_PREFIX_WEIGHT + COMMAND_PREFIX_DELIMITER + "WEIGHT(KG) "
            + COMMAND_PREFIX_AGE + COMMAND_PREFIX_DELIMITER + "AGE "
            + COMMAND_PREFIX_GOAL + COMMAND_PREFIX_DELIMITER + "CALORIEGOAL "
            + COMMAND_PREFIX_GENDER + COMMAND_PREFIX_DELIMITER + "GENDER(M/F) "
            + COMMAND_PREFIX_ACTIVITY_FACTOR + COMMAND_PREFIX_DELIMITER + "ACTIVITYFACTOR(1-5)"
            + QUOTATION;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Trying to update your profile? "
            + "Use the following format:" + INDENTED_LS + MESSAGE_COMMAND_FORMAT;


    public static final String[] EXPECTED_PREFIXES = {
            COMMAND_PREFIX_NAME,
            COMMAND_PREFIX_HEIGHT,
            COMMAND_PREFIX_WEIGHT,
            COMMAND_PREFIX_GOAL,
            COMMAND_PREFIX_AGE,
            COMMAND_PREFIX_ACTIVITY_FACTOR,
            COMMAND_PREFIX_GENDER
    };

    public static final String MESSAGE_SUCCESS = "Your profile has been updated!" + LS + "%s";

    private Name name;
    private Weight weight;
    private Height height;
    private CalorieGoal calorieGoal;
    private Age age;
    private ActivityFactor activityFactor;
    private Gender gender;

    public ProfileUpdateCommand(String name, double height, double weight, int calorieGoal, int age,
                                int activityFactor, char gender) {
        this.name = new Name(name);
        this.height = new Height(height);
        this.weight = new Weight(weight);
        this.calorieGoal = new CalorieGoal(calorieGoal);
        this.gender = new Gender(gender);
        this.age = new Age(age);
        this.activityFactor = new ActivityFactor(activityFactor);
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
            super.profile.setProfile(this.name, this.height, this.weight,
                    this.gender, this.age, this.calorieGoal, this.activityFactor);
            return new CommandResult(String.format(
                    MESSAGE_SUCCESS, super.profile.convertToString()));
        } catch (InvalidCharacteristicException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
