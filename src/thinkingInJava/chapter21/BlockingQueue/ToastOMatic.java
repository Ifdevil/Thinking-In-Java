package thinkingInJava.chapter21.BlockingQueue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockQueue
 */
public class ToastOMatic {

    public static void main(String[] args) throws InterruptedException {

        ToastQueue dryQueue = new ToastQueue(),
                   butteredQueue = new ToastQueue(),
                   jammedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue,butteredQueue));
        exec.execute(new Jammer(butteredQueue,jammedQueue));
        exec.execute(new Eater(jammedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
class Toast{
    public enum Status{DRY,BUTTERED,JAMMED}
    private Status status = Status.DRY;
    private final int id;
    public Toast(int idn){
        this.id = idn;
    }
    public void butter(){
        status = Status.BUTTERED;
    }
    public void jam(){
        status = Status.JAMMED;
    }
    public Status getStatus(){
        return status;
    }
    public int getId(){
        return id;
    }
    public String toString(){
        return "Toast "+id+": "+status;
    }
}
class ToastQueue extends LinkedBlockingQueue<Toast>{}

class Toaster implements Runnable{
    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(47);
    public Toaster(ToastQueue toastQueue){
        this.toastQueue = toastQueue;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println(t);
                toastQueue.put(t);
            } catch (InterruptedException e) {
                System.out.println("Toaster interrupted");
            }
        }
        System.out.println("Toaster off");
    }
}
class Butterer implements Runnable{
    private ToastQueue dryQueue,butteredQueue;
    public Butterer(ToastQueue dry,ToastQueue buttered){
        this.dryQueue = dry;
        this.butteredQueue = buttered;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}
class Jammer implements Runnable{
    private ToastQueue butterQueue,finishedQueue;
    public Jammer(ToastQueue butterQueue,ToastQueue finishedQueue){
        this.butterQueue = butterQueue;
        this.finishedQueue = finishedQueue;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                Toast t = butterQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            } catch (InterruptedException e) {
                System.out.println("Jammer interrupted");
            }
        }
        System.out.println("Jammer off");
    }
}
class Eater implements Runnable{
    private ToastQueue finishQueue;
    private int counter = 0;
    public Eater(ToastQueue finishQueue){
        this.finishQueue = finishQueue;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Toast t = finishQueue.take();
                if(t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED){
                    System.out.println(">>>Error: "+t);
                    System.exit(1);
                }else {
                    System.out.println("Chomp! "+t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}
