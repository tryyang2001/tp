package seedu.duke.storage.profile;

import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;

public class ProfileStorage extends Storage {

    public static final String FILENAME_PROFILE = "profile.txt";
    public static final String FILEPATH_PROFILE = FILEPATH + FILENAME_PROFILE;

    private ProfileDecoder decoder = new ProfileDecoder();
    private ProfileEncoder encoder = new ProfileEncoder();

    public Profile loadProfileFile() throws UnableToReadFileException {
        checkForFile(FILEPATH_PROFILE);
        return readFromProfileFile();
    }

    private Profile readFromProfileFile() throws UnableToReadFileException {
        try {
            return new ProfileDecoder().getProfileFromData();
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "The path is missing ", FILEPATH_PROFILE);
            throw new UnableToReadFileException(FILENAME_PROFILE);
        } catch (InvalidCharacteristicException e) {
            logger.log(Level.WARNING, "The profile has a invalid characteristic.");
            throw new UnableToReadFileException(FILENAME_PROFILE);
        }
    }

    /**
     * Saves the profile details into storage.
     * Used when there is an update to a profile attribute.
     *
     * @param profile Profile of the current user
     */
    public void saveProfile(Profile profile) throws UnableToWriteFileException {
        ArrayList<String> profileDetails = new ProfileEncoder().encodeProfileDetails(profile);
        writeToFile(profileDetails, FILEPATH_PROFILE);
        logger.log(Level.INFO, "Saved profile.");
    }

}
