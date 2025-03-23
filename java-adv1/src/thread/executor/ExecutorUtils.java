package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize(); // 스레드 총 갯수
            int active = poolExecutor.getActiveCount(); // 실제 작업을 하고 있는 스레드
            int queuedTasks = poolExecutor.getQueue().size(); // 큐에 작업이 몇개 들어가있는지
            long completedTask = poolExecutor.getCompletedTaskCount(); // 완료된 작업이 몇개인지
            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks + ", completedTasks=" + completedTask + "]");
        } else {
            log(executorService);
        }
    }

    public static void printState(ExecutorService executorService, String taskName) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize();
            int active = poolExecutor.getActiveCount();
            int queued = poolExecutor.getQueue().size();
            long completedTask = poolExecutor.getCompletedTaskCount();
            log(taskName + " -> [pool=" + pool + ", active=" + active + ", queuedTasks=" + queued + ", completedTasks=" + completedTask + "]");
        } else {
            log(taskName + " -> " + executorService);
        }
    }
}