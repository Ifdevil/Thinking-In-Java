package thinkingInJava.chapter21;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Notify Vs NotifyAll
 */
public class NotifyVsNotifyAll {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Task1());
        }
        exec.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private boolean prod = true;
            @Override
            public void run() {
                if(prod){
                    System.out.println("\nnotify()");
                    Task1.blocker.prod();
                    prod = false;
                }else{
                    System.out.println("\nnotifyAll()");
                    Task1.blocker.prodAll();
                    prod = true;
                }
            }
        },400,400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("\nTimeer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Task2.blockeer.prodALL()");
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        exec.shutdownNow();
    }


}
class Blocker{

    synchronized void waitingCall(){
        try {
            while (!Thread.interrupted()){
                wait();
                System.out.println(Thread.currentThread()+" ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized void prod(){
        notify();
    }
    synchronized void prodAll(){
        notifyAll();
    }
}
class Task1 implements Runnable{
    static Blocker blocker = new Blocker();
    @Override
    public void run() {
        blocker.waitingCall();
    }
}
class Task2 implements Runnable{
    static Blocker blocker = new Blocker();
    @Override
    public void run() {
        blocker.waitingCall();
    }
}
