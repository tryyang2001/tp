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
 * Represents the command that when executed, changes the value of name, height and weight in the Profile.
 */
public class ProfileCreateCommand extends Command {
    public static int COMMAND_EXPECTED_NUM_DELIMITERS = 4;
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD_PROFILE
            + " " + COMMAND_PREFIX_NAME + COMMAND_PREFIX_DELIMITER + "W "
            + COMMAND_PREFIX_HEIGHT + COMMAND_PREFIX_DELIMITER + "X "
            + COMMAND_PREFIX_WEIGHT + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_GOAL + COMMAND_PREFIX_DELIMITER + "Z"
            + QUOTATION + " where W is your name, X is your height in CM,"
            + INDENTED_LS + "Y is your weight in KG and Z is your calorie goal.";
    public static final String MESSAGE_SUCCESS = "Hello %1$s! Your profile has been created!"
            + INDENTED_LS + "Your height is %2$scm."
            + INDENTED_LS + "Your weight is %3$skg."
            + INDENTED_LS + "Your gender is %4$s."
            + INDENTED_LS + "Your age is %5$s."
            + INDENTED_LS + "Your calories goal is %6$s cal."
            + INDENTED_LS + "Your activity factor is %7$s.";

    private Name name = new Name("");
    private Weight weight;
    private Height height;
    private CalorieGoal calorieGoal;
    private Age age;
    private ActivityFactor activityFactor;
    private Gender gender;

    public ProfileCreateCommand(String name, double height, double weight, char gender,
                                int age, int calorieGoal, int activityFactor ) {
        assert name != null : "parser should have ensured name is not null";
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
            super.profile = new Profile(this.name, this.height, this.weight,
                    this.gender, this.age, this.calorieGoal, this.activityFactor);
            checkIfCommandShouldExecute();
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
