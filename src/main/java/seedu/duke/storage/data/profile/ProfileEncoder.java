package seedu.duke.storage.data.profile;

import seedu.duke.data.profile.Profile;

import java.util.ArrayList;

/**
 * Encodes the profile attributes into an ArrayList to be saved.
 */
public class ProfileEncoder {
    public static ArrayList<String> encode(Profile profile) {
        return new ArrayList<String>() {
            {
                add(profile.toFileTextString());
            }
        };
    }
}
