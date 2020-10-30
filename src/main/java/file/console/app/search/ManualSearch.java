package file.console.app.search;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class ManualSearch {

    public static void search(final File folder, List<String> result) throws InterruptedException {
        for (final File f : Objects.requireNonNull(folder.listFiles())) {
            Thread.sleep(200);
            if (f.isDirectory()) {
                Thread.sleep(200);
                result.add(f.getAbsolutePath() + " it's directory");
                search(f, result);
            }

            if (f.isFile()) {
                Thread.sleep(200);
                result.add(f.getAbsolutePath() + " " +
                        "it's file");
            }
        }
    }

    public static void printSearch(final File folder) throws InterruptedException {
        for (final File f : Objects.requireNonNull(folder.listFiles())) {
            Thread.sleep(200);
            if (f.isDirectory()) {
                Thread.sleep(200);
                System.out.println(f.getAbsolutePath() + " it's directory");
                printSearch(f);
            }

            if (f.isFile()) {
                Thread.sleep(200);
                System.out.println(f.getAbsolutePath() + " " +
                        "it's file");
            }
        }
    }
}
