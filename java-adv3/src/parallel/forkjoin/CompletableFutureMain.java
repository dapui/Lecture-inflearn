package parallel.forkjoin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.log;

public class CompletableFutureMain {

    public static void main(String[] args) {
        // !!!!! CompletableFuture` 에 스레드 풀을 지정하지 않으면 Fork/Join 공용 풀이 사용되는 것을 확인할 수 있다. !!!!!
        CompletableFuture.runAsync(() -> log("Fork/Join")); // Fork/Join 공용 풀

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // CompletableFuture 를 생성할 때는 별도의 스레드 풀을 반드시 지정해야 한다.
        // 그렇지 않으면 **Fork/Join 공용 풀**이 대신 사용된다. 이 때문에 많은 장애가 발생한다.
        // CompletableFuture 를 사용할 때는 **반드시! 커스 텀 풀을 지정해서 사용**하자!
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ExecutorService es = Executors.newFixedThreadPool(100);
        CompletableFuture.runAsync(() -> log("Custom Pool"), es); // 별도의 풀
        es.close();
    }
}
