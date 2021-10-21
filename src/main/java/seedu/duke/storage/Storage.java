package seedu.duke.storage;

import seedu.duke.storage.exceptions.UnableToReadFileException;
import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Storage {

    public static final String FILEPATH = "./data/";

    protected static final Logger logger = Logger.getLogger(Storage.class.getName());

    protected void checkForFile(String filePath) throws UnableToReadFileException {
        File f = new File(filePath);
        String[] paths = filePath.split("/");
        String fileName = paths[2];
        try {
            Files.createDirectories(Paths.get(FILEPATH));
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "There is an error accessing the file ", fileName);
            throw new UnableToReadFileException(fileName);
        }
    }

    protected void writeToFile(ArrayList<String> itemList, String filePath) throws UnableToWriteFileException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (String item : itemList) {
                fw.write(item + System.lineSeparator());
                logger.log(Level.INFO, "Logging " + item);
            }
            fw.close();
        } catch (IOException e) {
            throw new UnableToWriteFileException();
        }
    }
}
