package seedu.duke.storage.profile;

import seedu.duke.data.profile.Profile;
import seedu.duke.storage.Encoder;

import java.util.ArrayList;

public class ProfileEncoder extends Encoder {

    /**
     * Encodes the profile details into strings for storage.
     *
     * @param profile The profile to be encoded
     * @return An ArrayList of the profile details to be stored
     */
    public ArrayList<String> encodeProfileDetails(Profile profile) {
        detailsToSave.add(profile.toFileTextString());
        return detailsToSave;
    }
}
