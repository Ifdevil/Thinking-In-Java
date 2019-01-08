package thinkingInJava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 休眠
 */
public class SleepingTask extends LiftOff{

    public void run(){

        try{
            while (countDown-- > 0){
                System.out.println(status());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }
}
