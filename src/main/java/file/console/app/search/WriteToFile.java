package file.console.app.search;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    private File outputFile;

    public WriteToFile() {
    }

    public WriteToFile(String outputFile) {
        this.outputFile = new File(FilenameUtils.separatorsToSystem(outputFile));
    }

    public void createHeader() {
        if (outputFile.exists()) {
            outputFile.delete();
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(outputFile, true))) {

            bufferedWriter.write("Source_path,Count_of_files;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTo(String text) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(outputFile, true))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = new File(FilenameUtils.separatorsToSystem(outputFile));
    }

}
