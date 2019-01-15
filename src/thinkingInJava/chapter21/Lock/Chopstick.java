package thinkingInJava.chapter21.Lock;

/**
 * 哲学家就餐问题
 */
public class Chopstick {

    private boolean tacken = false;
    public synchronized void take() throws InterruptedException {
        while (tacken){
            wait();
        }
        tacken = true;
    }
    public synchronized void drop(){
        tacken = false;
        notifyAll();
    }
}
