package file.console.app.search;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiSearch extends Thread {
    private List<File> files;
    private String name;
    private Thread thread;
    private int count;
    private WriteToFile writeToFile;
    private static final BlockingQueue<String> queue = new LinkedBlockingDeque<>();
    private final MyCollection myCollection;
    private File outputFile;
    private AtomicInteger atomicInteger;
    private TableList tableList;
    private FormatOutput formatOutput;

    public MultiSearch(String name, List<File> files, WriteToFile writeToFile,
                       MyCollection myCollection, File outputFile,
                       AtomicInteger atomicInteger, TableList tableList, FormatOutput formatOutput) throws InterruptedException {
        myCollection.getQueue().add("some string ");
        this.formatOutput = formatOutput;
        this.tableList = tableList;
        this.atomicInteger = atomicInteger;
        this.outputFile = outputFile;
        this.myCollection = myCollection;
        this.writeToFile = writeToFile;
        this.name = String.valueOf(System.identityHashCode(name));
        this.files = files;
        thread = new Thread(this, name);
        thread.start();
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                int recordsId = atomicInteger.getAndIncrement();
                if (file.isDirectory()) {
                    Thread.sleep(2000);
                    List<File> newList = List.of(Objects.requireNonNull(file.listFiles()));
                    count = newList.size();
                    writeToFile.writeTo( file.getAbsolutePath() + "," + count + ";");
                   /* System.out.println(Thread.activeCount());
                    System.out.println(recordsId + " " + file.getAbsolutePath() + " count of files = " + count
                            + " it's directory in thread " + name);*/
                    /*tableList.addRow(String.valueOf(recordsId), String.valueOf(count), file.getAbsoluteFile().toString());
                    tableList.print();*/
                    formatOutput.printFormat(recordsId, count, file.getAbsoluteFile().toString());
                    new MultiSearch(String.valueOf(System.identityHashCode(file)), newList,
                            writeToFile, myCollection, outputFile, atomicInteger, tableList, formatOutput);
                }
                if (file.isFile()) {
                    /*System.out.println(recordsId + " " + file.getAbsolutePath() + " count of files = " + count
                            + " it's directory in thread " + name);*/
                    /*tableList.addRow(String.valueOf(recordsId), String.valueOf(count), file.getAbsoluteFile().toString());
                    tableList.print();*/
                    formatOutput.printFormat(recordsId, count, file.getAbsoluteFile().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  /*  public void writeTo(String text) throws IOException {
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(outputFile, true))) {
            bufferedWriter.append(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public BlockingQueue<String> getQueue() {
        return queue;
    }
}
