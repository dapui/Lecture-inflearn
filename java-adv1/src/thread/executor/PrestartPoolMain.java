package thread.executor;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.*;
import static util.ThreadUtils.sleep;

public class PrestartPoolMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1000);

        printState(es);
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) es;
        poolExecutor.prestartAllCoreThreads();
        printState(es);
    }
}
