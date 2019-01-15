package thinkingInJava.chapter21.Lock;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 哲学家就餐问题
 */
public class FixedDiningPhilosophers {

    public static void main(String[] args) throws InterruptedException, IOException {
        int poner = 5;
        if(args.length>0){
            poner = Integer.parseInt(args[0]);
        }
        int size = 5;
        if(args.length>1){
            size = Integer.parseInt(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[5];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            if(i<size-1){
                exec.execute(new Philosopher(sticks[i],sticks[(i+1)],i,poner));
            }else{
                exec.execute(new Philosopher(sticks[0],sticks[i],i,poner));
            }
        }
        if(args.length==3 && args[0].equals("timeout")){
            TimeUnit.SECONDS.sleep(5);
        }else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
