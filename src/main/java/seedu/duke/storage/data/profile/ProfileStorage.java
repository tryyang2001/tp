package seedu.duke.storage.data.profile;

import seedu.duke.data.profile.Profile;
import seedu.duke.storage.Storage;
import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;
import seedu.duke.storage.utilities.FileChecker;
import seedu.duke.storage.utilities.FileSaver;

import java.io.FileNotFoundException;
import java.util.logging.Level;

/**
 * A Storage class that handles the saving and loading of the Profile.
 */
public class ProfileStorage extends Storage implements ProfileStorageInterface {

    /**
     * Constructs the profile storage handler with its respective path.
     *
     * @param path the directory to save the profile file
     */
    public ProfileStorage(String path) {
        this.filePath = path;
        this.fileName = getFileName(path);
    }

    @Override
    public Profile loadProfile() throws UnableToReadFileException {
        FileChecker.createFileIfMissing(filePath);
        try {
            return ProfileDecoder.retrieveProfileFromData(filePath);
        } catch (FileNotFoundException e) {
            throw new UnableToReadFileException(fileName);
        }
    }

    @Override
    public void saveProfile(Profile profile) throws UnableToWriteFileException {
        FileSaver.saveTo(filePath, ProfileEncoder.encode(profile));
        logger.log(Level.FINE, "Saved profile.");
    }

}
