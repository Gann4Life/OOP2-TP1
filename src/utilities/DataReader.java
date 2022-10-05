package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataReader {

    // PRE: A file has to be found in the given path.
    // POS: Returns each line into an array of strings.
    public static String[] getFileLines(String path) throws IOException {
        return Files.readAllLines(Path.of(path)).toArray(new String[0]);
    }
}
