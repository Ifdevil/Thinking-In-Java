package thinkingInJava.chapter21.Lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * 哲学家就餐问题
 */
public class Philosopher implements Runnable{
    private Chopstick right;
    private Chopstick left;
    private final int id;
    public final int ponderFactor;
    private Random random = new Random(47);
    private void pauser() throws InterruptedException {
        if(ponderFactor==0){
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor*250));
    }
    public Philosopher(Chopstick left,Chopstick right,int ident,int ponder){
        this.left = left;
        this.right = right;
        this.id = ident;
        this.ponderFactor = ponder;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                System.out.println(this+" thinking");
                pauser();
                System.out.println(this+" grabbing right");
                right.take();
                System.out.println(this+" grabbing left");
                left.take();
                System.out.println(this+ " eating");
                pauser();
                right.drop();
                left.drop();
            }
        }catch (InterruptedException e){
            System.out.println(this+" "+"exiting iva intrrupt");
        }
    }
    public String toString(){
        return "Philosopher"+id;
    }
}
