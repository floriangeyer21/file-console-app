package file.console.app.search;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InputFileParser {

    public List<File> parseInput(String filename) throws FileNotFoundException {
        List<File> result = new CopyOnWriteArrayList<>();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(FilenameUtils.separatorsToSystem(filename)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(new File(FilenameUtils.separatorsToSystem(line)));
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
