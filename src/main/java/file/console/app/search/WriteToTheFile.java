package file.console.app.search;

import java.io.*;

public class WriteToTheFile {

    public String write(File output) {
        String path = output.getPath();
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            return "Successfully wrote to the file.";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "An error occurred.";
    }

    public String writeOutputStream(File path, String text) {
        try (FileOutputStream outputStream =
                     new FileOutputStream(path)) {
            byte[] strToBytes = text.getBytes();
            outputStream.write(strToBytes);
            return "Successfully wrote to the file";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "An error occurs when writing to the file";
    }
}
