package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    public void put(String data) {
        queue.add(data); // java.lang.IllegalStateException: Queue full
    }

    public String take() {
        return queue.remove(); // java.util.NoSuchElementException
    }
    
    @Override
    public String toString() {
        return queue.toString();
    }
}
