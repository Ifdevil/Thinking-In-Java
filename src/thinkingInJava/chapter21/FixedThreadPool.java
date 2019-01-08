package thinkingInJava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用有限的线程池
 */
public class FixedThreadPool {

    public static void main(String[] args) {

//        ExecutorService exec = Executors.newFixedThreadPool(5);
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
