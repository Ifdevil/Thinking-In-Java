package thinkingInJava.chapter21.Car;

import java.util.concurrent.BrokenBarrierException;

public abstract class Robot implements Runnable{

    private RobotPool pool;
    public Robot(RobotPool p){
        pool = p;
    }
    protected Assembler assembler;

    //分配装配工
    public Robot assignAssimbler(Assembler assembler){
        this.assembler = assembler;
        return this;
    }
    private boolean engage =false;
    public synchronized void engage(){
        engage = true;
        notifyAll();
    }
    abstract protected void performService();
    public void run(){
        try {
            powerDown();
            while (!Thread.interrupted()){
                performService();
                assembler.getBarrier().await();
                powerDown();
            }
        }catch (InterruptedException e){

        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(this + " off");
    }
    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;
        pool.release(this);
        while (engage==false){
            wait();
        }
    }
    public String toString(){
        return getClass().getName();
    }
}
