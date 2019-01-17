package thinkingInJava.chapter21.Bank;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 生成客户
 */
public class CustomerGenerator implements Runnable {
    private CustomerLine consumers;
    private static Random random = new Random(47);
    public CustomerGenerator(CustomerLine cq){
        consumers = cq;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                consumers.put(new Customer(random.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
}
