package file.console.app;

import file.console.app.search.*;
import org.apache.commons.io.FilenameUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final File path = new File("D:\\test_tasks\\output.csv");
    private static final String path2 = "D:\\test";
    private static final File folder = new File(path2);
    private static final String input = "D:\\test_tasks\\input.txt";
    private static final BlockingQueue<String> out = new LinkedBlockingDeque<>();

    public static void main(String[] args) throws IOException, NativeHookException {
        /*String text = "Hello";
        WriteToTheFile writer = new WriteToTheFile();
        System.out.println(writer.writeOutputStream(path, text));*/
      /*  PrintSearch printSearch = new PrintSearch();

        try {
            Files.walkFileTree(Path.of(path2), printSearch);
            System.out.println(printSearch.getCount());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        KeyListener keyListener = new KeyListener();
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(keyListener);
        MyCollection myCollection = new MyCollection();
        WriteToFile writeToFile = new WriteToFile();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        TableList tableList = new TableList(3, "ID", "Count", "Path").withUnicode(true);
        FormatOutput formatOutput = new FormatOutput(40);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input1 = scanner.nextLine();
            String[] inputs = input1.split(" ");
            if (inputs[0].equals("consoleapp")) {
                try {
                    formatOutput.printHeader();
                    String inputFile = inputs[1];
                    String outputFile = inputs[2];
                    File out = new File(FilenameUtils.separatorsToSystem(outputFile));
                    writeToFile.setOutputFile(outputFile);
                    writeToFile.createHeader();
                    InputFileParser inputFileParser = new InputFileParser();
                    List<File> inputFiles = inputFileParser.parseInput(inputFile);
                    MultiSearch m = new MultiSearch(
                            String.valueOf(System.identityHashCode(Thread.currentThread())),
                            inputFiles, writeToFile, myCollection, out, atomicInteger, tableList, formatOutput);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Unrecognized command");
            }
           /* StringBuilder sb = new StringBuilder();
            Collection<String> queue = myCollection.getQueue();
            for (String str : queue) {
                sb.append(str).append(";");
            }
            writeToFile.writeTo(sb.toString());*/
        }


    }


}

