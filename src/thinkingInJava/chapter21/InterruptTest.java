package thinkingInJava.chapter21;

import java.util.concurrent.TimeUnit;

public class InterruptTest {

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
//        try {
//            Thread.sleep(20);//modify 2000 to 20
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        thread.interrupt();

    }
}
class MyThread extends Thread{
    public void run(){
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            Thread.currentThread().interrupt();
            //e.printStackTrace();
        }
    }
}
