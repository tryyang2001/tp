package seedu.duke.storage.data.profile;

import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.storage.StorageManager;
import seedu.duke.storage.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProfileDecoder {

    /**
     * Retrieves profile data from profile.txt
     *
     * @return The profile object with its corresponding characteristics
     * @throws FileNotFoundException          If the file is misplaced/missing
     * @throws InvalidCharacteristicException When the data is corrupted in the file.
     */
    public static Profile retrieveProfileFromData(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner in = new Scanner(file);

        try {
            if (in.hasNext()) {
                return decodeProfileDataFromString(in.nextLine());
            }
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }

        return new Profile();
    }

    private static Profile decodeProfileDataFromString(String input) throws InvalidDataException {
        try {
            Profile profile = new Profile();
            final String[] profileDetails = input.split(StorageManager.FILE_TEXT_DELIMITER);
            final String name = profileDetails[0];
            final double height = Double.parseDouble(profileDetails[1]);
            final double weight = Double.parseDouble(profileDetails[2]);
            final char gender = profileDetails[3].charAt(0);
            final int age = Integer.parseInt(profileDetails[4]);
            final int calorieGoal = Integer.parseInt(profileDetails[5]);
            final int activityFactor = Integer.parseInt(profileDetails[6]);
            profile.setProfileWithRawInputs(name, height, weight, gender, age, calorieGoal, activityFactor);
            return profile;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidDataException(StorageManager.FILENAME_PROFILE, input);
        }
    }

}
