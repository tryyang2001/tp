package seedu.duke.storage;

import java.util.logging.Logger;

public abstract class Storage {

    public static final String FILEPATH = "./data/";

    protected static final Logger logger = Logger.getLogger(Storage.class.getName());

    protected String filePath;
    protected String fileName;

    protected String getFileName(String path) {
        return path.split("/")[2];
    }
}
