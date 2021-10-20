package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.item.ItemBank;
import seedu.duke.item.exercise.ExerciseList;
import seedu.duke.item.exercise.FutureExerciseList;
import seedu.duke.item.food.FoodList;
import seedu.duke.parser.Parser;
import seedu.duke.parser.exceptions.ParamMissingException;
import seedu.duke.profile.Profile;
import seedu.duke.profile.attributes.ActivityFactor;
import seedu.duke.profile.attributes.Age;
import seedu.duke.profile.attributes.CalorieGoal;
import seedu.duke.profile.attributes.Gender;
import seedu.duke.profile.attributes.Height;
import seedu.duke.profile.attributes.Name;
import seedu.duke.profile.attributes.Weight;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.ui.Ui;


/**
 * Main class of Fitbot.
 * Initialises the application and starts interaction with user.
 */
public class Main {

    public static final String MESSAGE_INPUT_IS_SUCCESSFUL = "Input %s is successful.";
    private ExerciseList exerciseItems;
    private FutureExerciseList futureExerciseItems;
    private FoodList foodItems;
    private ItemBank exerciseBank;
    private ItemBank foodBank;
    private Profile profile;
    private Ui ui;
    private Storage storage;

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        new Main().run(args);
    }

    /**
     * Runs the application until command is given to exit it.
     **/
    private void run(String[] args) {
        start();
        checkAndCreateProfile();
        enterTaskModeUntilByeCommand();
        exit();
    }

    /**
     * Initialises the application by creating the required objects and loading data from the
     * storage file, then showing the welcome message.
     */
    private void start() {
        this.storage = new Storage();
        this.ui = new Ui();
        //TODO: replace these with ones from storage
        this.exerciseBank = new ItemBank();
        this.foodBank = new ItemBank();
        this.futureExerciseItems = new FutureExerciseList();
        try {
            this.profile = storage.loadProfileFile();
            this.foodItems = storage.loadFoodListFile();
            this.exerciseItems = storage.loadExerciseListFile();
        } catch (UnableToReadFileException e) {
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
        ui.printStartMessage(profile.checkProfileComplete(),profile.checkProfilePresent());
    }

    private void checkAndCreateProfile() {

        if (profile.checkProfileComplete()) {

            return;
        }
        System.out.println(profile.checkProfilePresent());
        if (profile.checkProfilePresent()){
            assert !profile.checkProfileComplete(): "profile is incomplete";
            repairProfile();
        } else {
            createNewProfile();
        }
        ui.formatMessageFramedWithDivider("Profile created successfully");
    }

    private void repairProfile() {
        while (!profile.checkProfileComplete()) {
            try {
                if(!profile.getProfileName().isValid()){
                    createNewProfileName(profile); // if user just enter and exit, it will cause his name to be null
                } else if (!profile.getProfileHeight().isValid()){
                    createNewProfileHeight(profile);
                } else if (!profile.getProfileWeight().isValid()) {
                    createNewProfileWeight(profile);
                } else if (!profile.getProfileGender().isValid()) {
                    createNewProfileGender(profile);
                } else if (!profile.getProfileAge().isValid()) {
                    createNewProfileAge(profile);

                } else if (!profile.getProfileActivityFactor().isValid()) {
                    createNewProfileActivityFactor(profile);
                }
                if (profile.checkProfileComplete()){
                    createNewProfileCalorieGoal(profile);
                }
                storage.saveProfile(this.profile);
            } catch (ParamMissingException e) {
                System.out.println(e.getMessage());
            } catch (UnableToWriteFileException e) {
                ui.formatMessageFramedWithDivider(e.getMessage());
            }
        }
    }

    private void createNewProfile() {
        Profile newProfile = new Profile();
        // Welcome to fitbot. Let's set up a profile so that we can help you manage your calories.
        while (!newProfile.checkProfileComplete()) {
            try {
                createNewProfileName(newProfile);
                createNewProfileHeight(newProfile);
                createNewProfileWeight(newProfile);
                createNewProfileGender(newProfile);
                createNewProfileAge(newProfile);
                createNewProfileCalorieGoal(newProfile); // need to check validity
                createNewProfileActivityFactor(newProfile);
            } catch (ParamMissingException e) {
                System.out.println(e.getMessage());
            }
        }
        this.profile = newProfile;
        try {
            storage.saveProfile(this.profile);
        } catch (UnableToWriteFileException e) {
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
    }

    private void createNewProfileActivityFactor(Profile newProfile) throws ParamMissingException {
        while(!newProfile.getProfileActivityFactor().isValid()){
            try {
                ui.formatMessageFramedWithDivider("Please input your activity factor.\n" +
                        "Below are the activity factor and the corresponding description you can consider:"
                        ,MESSAGE_ACTIVITY_FACTOR);
                String userInput = ui.getUserInput();
                checkEmptyUserInput(userInput);
                int activityFactorInput = Integer.parseInt(userInput);
                newProfile.setProfileActivityFactor(new ActivityFactor(activityFactorInput));
                //TODO: add a print statement to tell user input is incorrect
                String messageValidAttribute = newProfile.getProfileActivityFactor().isValid() ?
                        printMessage("activity factor"):profile.ERROR_ACTIVITY_FACTOR;
                ui.formatMessageFramedWithDivider(messageValidAttribute);
            } catch(NumberFormatException e){
                ui.formatMessageFramedWithDivider("Invalid input, please input a valid positive number");
            }
        }
    }
    public static final String MESSAGE_ACTIVITY_FACTOR = "1 -> Sedentary - Little or no exercise\n" +
            "2 -> Lightly Active - Light exercise or sports, around 1-3 days a week\n" +
            "3 -> Moderately Active - Regular exercise or sports, around 3-5 days a week\n" +
            "4 -> Very Active - Frequent exercise or sports, around 6-7 days a week\n" +
            "5 -> If you are extra active - Sports or exercising is your passion and a physical jobscope.";
    private void createNewProfileCalorieGoal(Profile newProfile) throws ParamMissingException {
//        while(!newProfile.getProfileCalorieGoal().isValid()){
        boolean check_input = false;// check whether calorie goal has the correct input
            do {
                try {
                    ui.formatMessageFramedWithDivider("Please input your calorie goal.");
                    String userInput = ui.getUserInput();
                    checkEmptyUserInput(userInput);
                    int calorieGoalInput = Integer.parseInt(userInput);
                    newProfile.setProfileCalorieGoal(new CalorieGoal(calorieGoalInput));
                    //TODO: add a print statement to tell user input is incorrect
                    String messageValidAttribute = newProfile.getProfileCalorieGoal().isValid() ?
                            printMessage("calorie goal") :
                            String.format(profile.ERROR_CALORIE_GOAL,calorieGoalInput);
                    ui.formatMessageFramedWithDivider(messageValidAttribute);
                    check_input = true;
                } catch (NumberFormatException e) {
                    ui.formatMessageFramedWithDivider("Invalid input, please input a valid positive number");
                }
            }while (!check_input || !newProfile.getProfileCalorieGoal().isValid());
    }

    private void createNewProfileAge(Profile newProfile) throws ParamMissingException {
        while(!newProfile.getProfileAge().isValid()){
            try {
                ui.formatMessageFramedWithDivider("Please input your age below.");
                String userInput = ui.getUserInput();
                checkEmptyUserInput(userInput);
                int ageInput = Integer.parseInt(userInput);
                newProfile.setProfileAge(new Age(ageInput));
                //TODO: add a print statement to tell user input is incorrect
                ui.formatMessageFramedWithDivider("Please input your age.");
                String messageValidAttribute = newProfile.getProfileAge().isValid() ?
                        printMessage("Age"):profile.ERROR_AGE;
                ui.formatMessageFramedWithDivider(messageValidAttribute);
            } catch(NumberFormatException e){
                ui.formatMessageFramedWithDivider("Invalid input, please input a valid positive whole number");
            }
        }
    }

    private void createNewProfileGender(Profile newProfile) {
        while(!newProfile.getProfileGender().isValid()){
            ui.formatMessageFramedWithDivider("Please input your gender below.");
                String userInput = ui.getUserInput();
                if(userInput.length() == 1 ) {
                    char genderInput = userInput.charAt(0);
                    newProfile.setProfileGender(new Gender(genderInput));
                }
                //TODO: add a print statement to tell user input is incorrect
            String messageValidAttribute = newProfile.getProfileGender().isValid() ?
                    printMessage("gender"):profile.ERROR_GENDER;
            ui.formatMessageFramedWithDivider(messageValidAttribute);
        }
    }

    private void createNewProfileWeight(Profile newProfile) throws ParamMissingException {
        while(!newProfile.getProfileWeight().isValid()){
            try {
                ui.formatMessageFramedWithDivider("Please input your weight(in kg) below.");
                String userInput = ui.getUserInput();
                checkEmptyUserInput(userInput);
                double weightInput = Double.parseDouble(userInput);
                newProfile.setProfileWeight(new Weight(weightInput));
                String messageValidAttribute = newProfile.getProfileWeight().isValid() ?
                        printMessage("weight"):profile.ERROR_WEIGHT;
                ui.formatMessageFramedWithDivider(messageValidAttribute);
            } catch(NumberFormatException e){
                ui.formatMessageFramedWithDivider("Invalid input, please input a valid positive number");
            }
        }
    }

    private void createNewProfileHeight(Profile newProfile) throws ParamMissingException {
        while(!newProfile.getProfileHeight().isValid()){
            try {
                ui.formatMessageFramedWithDivider("Please input your height(in cm).");
                String userInput = ui.getUserInput();
                checkEmptyUserInput(userInput);
                double heightInput = Double.parseDouble(userInput);
                newProfile.setProfileHeight(new Height(heightInput));
                String messageValidAttribute = newProfile.getProfileHeight().isValid() ?
                        printMessage("height"):profile.ERROR_HEIGHT;
                ui.formatMessageFramedWithDivider(messageValidAttribute);
            } catch(NumberFormatException e){
                ui.formatMessageFramedWithDivider("Invalid input, please input a valid positive number");
            }
        }
    }

    private void createNewProfileName(Profile newProfile) throws ParamMissingException {
        while(!newProfile.getProfileName().isValid()){
            ui.formatMessageFramedWithDivider("Please input your name.","Note that '/' and '|' inside name are invalid.");
            String userInput = ui.getUserInput();
            checkEmptyUserInput(userInput);
            newProfile.setProfileName(new Name(userInput));
            String messageValidAttribute = newProfile.getProfileName().isValid() ?
                    printMessage("name"):profile.ERROR_NAME;
            ui.formatMessageFramedWithDivider(messageValidAttribute);
        }
    }

    private void checkEmptyUserInput(String userInput) throws ParamMissingException {
        if (userInput.length() == 0) {
            throw new ParamMissingException("Input cannot be empty");
        }
    }

    private String printMessage(String attribute) {
       return String.format(MESSAGE_INPUT_IS_SUCCESSFUL, attribute);
    }

//

    /**
     * Reads the user input and executes appropriate command.
     * Runs indefinitely until user inputs the Bye command.
     */
    private void enterTaskModeUntilByeCommand() {
        Command command;
        do {
            String userInput = ui.getUserInput();
            command = new Parser().parseCommand(userInput);
            CommandResult result = executeCommand(command);
            ui.formatMessageFramedWithDivider(result.toString());
        } while (!ByeCommand.isBye(command));
    }

    /**
     * Executes the given Command and (to be implemented) calls for storage operation if required.
     *
     * @param command Command to be executed
     * @return CommandResult representing result of execution of the command
     */
    private CommandResult executeCommand(Command command) {

        command.setData(this.profile, this.exerciseItems, this.futureExerciseItems,
                this.foodItems, this.exerciseBank, this.foodBank);
        CommandResult result = command.execute();
        try {
            if (ByeCommand.isBye(command)) {
                storage.saveAll(this.profile, this.exerciseItems, this.foodItems);
            }
            if (Command.requiresProfileStorageRewrite(command)) {
                storage.saveProfile(this.profile);
            }
            if (Command.requiresExerciseListStorageRewrite(command)) {
                storage.saveExercises(this.exerciseItems);
            }
            if (Command.requiresFoodListStorageRewrite(command)) {
                storage.saveFoodList(this.foodItems);
            }
            if (Command.requiresFutureExerciseListStorageRewrite(command)) {
                //TODO
                //storage.saveFutureExercises(this.futureExerciseItems);
            }
        } catch (UnableToWriteFileException e) {
            ui.formatMessageFramedWithDivider(e.getMessage());
        }
        return result;
    }

    /**
     * Exits the application.
     */
    private void exit() {
        System.exit(0);
    }

}
