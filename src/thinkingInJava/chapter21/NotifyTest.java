package thinkingInJava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程协作：wait And NotifyAll
 * 这两种方法是实现线程协作的一种方式：
 *          两种方法只能放在同步语句块中执行
 *          例如：两个线程想要协作，则通过一个Object调用wait或者notify实现互相牵制执行
 *          wait代表当前线程需要等待另一个条件满足才能继续执行，而通常这个条件由另一个线程
 *          来改变，这就是线程协作。
 *
 */
public class NotifyTest {

    public static void main(String[] args) throws InterruptedException {
        Car1 car = new Car1();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.MILLISECONDS.sleep(500);
        exec.shutdownNow();
    }
}

class Car1{
    private boolean waxOn = false;
    public synchronized void waxed(){
        waxOn = true;
        notifyAll();
    }
    public synchronized void buffed(){
        waxOn = false;
        notifyAll();
    }
    public synchronized void waitintWaxing() throws InterruptedException {
        while (waxOn==false){
            wait();
        }
    }
    public synchronized void waitingBuffing() throws InterruptedException {
        while (waxOn==true){
            wait();
        }
    }
}
class WaxOn implements Runnable{
    private Car1 car;
    public WaxOn(Car1 c){
        this.car = c;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                System.out.println("Wax On!");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitingBuffing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ending Wax On task");
        }
    }
}
class WaxOff implements Runnable{
    private Car1 car;
    public WaxOff(Car1 c){
        this.car = c;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                car.waitintWaxing();
                System.out.println("Wax Off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ending Wax Off task");
        }
    }
}

