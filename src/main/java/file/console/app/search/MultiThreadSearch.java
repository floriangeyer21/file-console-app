package file.console.app.search;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.requireNonNull;

public class MultiThreadSearch implements Runnable {
    private String name;
    private List<File> files;
    private Thread thread;
    private List<String> result;
    private File file;

    public MultiThreadSearch(String name, File file, List<String> result) {
        this.name = name;
        this.file = file;
        this.result = result;
        thread = new Thread(this, name);
        thread.start();
    }

    public MultiThreadSearch(String name, List<File> files) {
        result = new CopyOnWriteArrayList<>();
        this.files = files;
        this.name = name;
        thread = new Thread(this, name);
        thread.start();
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public void run() {
    /*  try {
          for (final File f : Objects.requireNonNull(folder.listFiles())) {
              Thread.sleep(200);
              if (f.isDirectory()) {
                  Thread.sleep(200);
                  System.out.println(f.getAbsolutePath() + " it's directory in thread " + name);
                  new MultiThreadSearch(f.getName(), f);
              }

             *//* if (f.isFile()) {
                  Thread.sleep(200);
                  System.out.println(f.getAbsolutePath() + " " +
                          "it's file in thread " + name);
              }*//*
          }
      } catch (Exception e) {
          e.printStackTrace();
      }*/
      /* try {
           List<File> list = new CopyOnWriteArrayList<>(Objects.requireNonNull(folder.listFiles()));
           list.stream()
                   .filter(File::isDirectory)
                   .peek(f -> System.out.println(f.getAbsolutePath() + " it's directory in thread " + name))
                   .peek(t -> {
                       try {
                           Thread.sleep(2000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   })
                   .forEach(f -> new MultiThreadSearch(f.getName(), f, result));
       } catch (Exception e) {
           e.printStackTrace();
       }*/

        try {
        /*    List<File> list = new CopyOnWriteArrayList<>(files);*/
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(file.getAbsolutePath() + " it's directory in thread " + name);
                    Thread.sleep(2000);
                    result.add(file.getAbsolutePath() + " it's directory in thread " + name);
                    new MultiThreadSearch(file.getName(), file, result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
