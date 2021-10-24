package seedu.duke.storage.profile;

import seedu.duke.data.profile.Profile;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.utilities.FileChecker;
import seedu.duke.storage.utilities.FileSaver;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Storage class that handles the saving and loading of the Profile.
 */
public class ProfileStorage implements ProfileStorageInterface {

    private final String filePath;
    private final String fileName;

    private final Logger logger = Logger.getLogger(ProfileStorage.class.getName());

    /**
     * Constructs the profile storage handler with its respective path.
     *
     * @param path the directory to save the profile file
     */
    public ProfileStorage(String path) {
        this.filePath = path;
        this.fileName = getFileName(path);
    }

    private String getFileName(String path) {
        return path.split("/")[2];
    }

    @Override
    public Profile loadProfile() throws UnableToReadFileException {
        FileChecker.createFileIfMissing(filePath);
        return readFromProfileFile();
    }

    private Profile readFromProfileFile() throws UnableToReadFileException {
        try {
            return ProfileDecoder.getProfileFromData(filePath);
        } catch (FileNotFoundException e) {
            throw new UnableToReadFileException(fileName);
        }
    }

    @Override
    public void saveProfile(Profile profile) throws UnableToWriteFileException {
        FileSaver.saveToFile(filePath, ProfileEncoder.encode(profile));
        logger.log(Level.INFO, "Saved profile.");
    }

}
