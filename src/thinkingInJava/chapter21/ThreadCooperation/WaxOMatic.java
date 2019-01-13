package thinkingInJava.chapter21.ThreadCooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxOMatic {

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}
class Car{
    private boolean waxOn =false;
    public synchronized void waxed(){
        waxOn = true;
        notifyAll();
    }
    public synchronized void buffed(){
        waxOn = false;
        notifyAll();
        System.out.println("buffed");
    }
    public synchronized void waitingForWaxing() throws InterruptedException {
        while (waxOn == false){
            wait();
        }
    }
    public synchronized void waitingForBuffing() throws InterruptedException {
        while (waxOn == true){
            System.out.println("waiting for buff");
            TimeUnit.SECONDS.sleep(2);
            wait();
        }
    }
}
class WaxOn implements Runnable{
    private Car car;
    public WaxOn(Car car){
        this.car = car;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                System.out.println("Wax On");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitingForBuffing();
             }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax on task");
    }
}
class WaxOff implements Runnable{
    private Car car;
    public WaxOff(Car car){
        this.car = car;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                car.waitingForWaxing();
                System.out.println("Wax Off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax off task");
    }
}
