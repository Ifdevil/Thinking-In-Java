package thinkingInJava.chapter21.Car;

import java.util.HashSet;
import java.util.Set;

public class RobotPool {

    private Set<Robot> pool = new HashSet<Robot>();
    public synchronized void add(Robot r){
        pool.add(r);
        notifyAll();
    }
    public synchronized void hire(Class<? extends Robot> robotType,Assembler d) throws InterruptedException {
        for (Robot robot:pool){
            if(robot.getClass().equals(robotType)){
                pool.remove(robot);
                robot.assignAssimbler(d);
                robot.engage();
                return;
            }
        }
        wait();
        hire(robotType,d);
    }
    public synchronized void release(Robot r){
        add(r);
    }
}
