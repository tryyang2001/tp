package seedu.duke.commands;

import seedu.duke.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.ui.Ui;

//TODO Update this class with ProfileCreateCommand once done modifying it to accommodate extra attributes
/**
 * Represents the command that when executed, changes the value of name, height and weight in the Profile.
 */
public class ProfileUpdateCommand extends Command {
    public static int COMMAND_EXPECTED_NUM_DELIMITERS = 7;
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_PROFILE
            + " " + COMMAND_PREFIX_NAME + COMMAND_PREFIX_DELIMITER + "W "
            + COMMAND_PREFIX_HEIGHT + COMMAND_PREFIX_DELIMITER + "X "
            + COMMAND_PREFIX_WEIGHT + COMMAND_PREFIX_DELIMITER + "Y "
            + COMMAND_PREFIX_GOAL + COMMAND_PREFIX_DELIMITER + "Z"
            + Ui.QUOTATION + " where W is your name, X is your height in CM,"
            + Ui.INDENTED_LS + "Y is your weight in KG and Z is your calorie goal.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to create your profile? Use this format:"
            + Ui.INDENTED_LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "Hello %1$s! Your profile has been created!"
            + Ui.INDENTED_LS + "Your height is %2$scm."
            + Ui.INDENTED_LS + "Your weight is %3$skg."
            + Ui.INDENTED_LS + "Your calories goal is %4$s cal.";

    private final String name;
    private final double weight;
    private final double height;
    private final int calorieGoal;
    private final int age;
    private final int activityFactor;
    private final char gender;


    public ProfileUpdateCommand(String name, double height, double weight, int calorieGoal, int age, int activityFactor, char gender) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.calorieGoal = calorieGoal;
        this.gender = gender;
        this.age = age;
        this.activityFactor = activityFactor;
    }

    @Override
    public CommandResult execute() {
        try {
            if (!this.name.equals("")) {
                super.profile.setName(this.name);
            }

            if (this.height != 0.0) {
                super.profile.setHeight(this.height);
            }

            if (this.weight != 0.0) {
                super.profile.setWeight(this.weight);
            }

            if (this.calorieGoal != 0) {
                super.profile.setCalorieGoal(this.calorieGoal);
            }
            //TODO Depends on what is passed from the parser, placeholder for now
            if (this.gender != Character.MIN_VALUE) {
                super.profile.setGender(this.gender);
            }

            if (this.age != 0) {
                super.profile.setAge(this.age);
            }

            if (this.activityFactor != 0) {
                super.profile.setActivityFactor(activityFactor);
            }

            return new CommandResult(String.format(
                    MESSAGE_SUCCESS,
                    this.name,
                    this.height,
                    this.weight,
                    this.calorieGoal,
                    this.gender,
                    this.age,
                    this.activityFactor));
        } catch (InvalidCharacteristicException e){
            return new CommandResult(e.getMessage());
        }
    }
}
