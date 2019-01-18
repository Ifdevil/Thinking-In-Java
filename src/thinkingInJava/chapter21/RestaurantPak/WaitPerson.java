package thinkingInJava.chapter21.RestaurantPak;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WaitPerson implements Runnable{

    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant restaurant;
    BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<Plate>();
    public WaitPerson(Restaurant r){
        restaurant = r;
    }
    public void placeOrder(Customer c,Food f){
        try {
            restaurant.orders.put(new Order(c,this,f));
        } catch (InterruptedException e) {
            System.out.println(this+" placeOrder interrupted");
        }
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Plate plate = filledOrders.take();
                System.out.println(this+"received "+plate+" delivering to "+plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        }catch (InterruptedException e){
            System.out.println(this + "interrupted");
        }
    }
    public String toString(){
        return "WaitingPerson "+id+" ";
    }
}
