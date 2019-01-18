package thinkingInJava.chapter21.Car;

public class Reporter implements Runnable {
    private CarQueue carQueue;
    public Reporter(CarQueue cq){
        carQueue = cq;
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println(carQueue.take());
            }
        }catch (InterruptedException e){
            System.out.println("Exiting Reporter via interrupt");
        }
    }
}
