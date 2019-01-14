package thinkingInJava.chapter21.BlockingQueue;

import thinkingInJava.chapter21.LiftOff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestBlockingQueues {
    static void getkey(){
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void getkey(String message){
        System.out.println(message);
        getkey();
    }
    static void test(String msg,BlockingQueue<LiftOff> queue){
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        for (int i = 0; i < 5; i++) {
            runner.add(new LiftOff(5));
        }
        getkey("Press 'Enter' ("+msg+")");
        t.interrupt();
        System.out.println("Finished "+msg+" test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue",new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockingQueue",new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue",new SynchronousQueue<LiftOff>());
    }
}
class LiftOffRunner implements Runnable{

    private BlockingQueue<LiftOff> rockets;
    public LiftOffRunner(BlockingQueue<LiftOff> queue){
        this.rockets = queue;
    }
    public void add(LiftOff lo){
        try {
            rockets.put(lo);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during put()");
        }
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                LiftOff lo = rockets.take();
                lo.run();
            }
        } catch (InterruptedException e) {
            System.out.println("Waking from take()");
        }
    }
}
