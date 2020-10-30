package file.console.app.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MyCollection {
    private static Collection<String> queue = Collections.synchronizedCollection(new ArrayList<>());


    public MyCollection() {
    }

    public synchronized Collection<String> getQueue() {
        return queue;
    }
}
