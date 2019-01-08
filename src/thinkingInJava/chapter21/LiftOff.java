package thinkingInJava.chapter21;

import java.util.List;

/**
 * 定义任务
 */
public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff(){}
    public LiftOff(int countDown){
        this.countDown = countDown;
    }
    public String status(){
        return "#"+id+"("+(countDown>0?countDown:"Liftoff!")+") ";
    }
    @Override
    public void run() {
        while (countDown-->0){
            System.out.print(status());
            Thread.yield();
        }
    }

    public static void main(String[] args) {
//        LiftOff launch = new LiftOff();
//        launch.run();
        //Thread类
//        Thread thread = new Thread(launch);
//        thread.start();
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
    }
}
