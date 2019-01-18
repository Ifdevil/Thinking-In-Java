package thinkingInJava.chapter21.RestaurantPak;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Chef implements Runnable{

    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant restaurant;
    private static Random random = new Random(47);
    public Chef(Restaurant r){
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Order order = restaurant.orders.take();
                Food requestItem = order.item();
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                Plate plate = new Plate(order,requestItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        }catch (InterruptedException e){
            System.out.println(this+"interrupted");
        }
        System.out.println(this+" off duty");
    }
    public String toString(){
        return "Chef "+id+" ";
    }
}
