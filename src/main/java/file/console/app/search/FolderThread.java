package file.console.app.search;

public class FolderThread implements Runnable {
    private String name;
    private Thread t;

    public FolderThread(String name) {
        this.name = name;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        System.out.println("%s is running " + name);
    }
}
