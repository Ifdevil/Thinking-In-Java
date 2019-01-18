package thinkingInJava.chapter21.RestaurantPak;

import java.util.concurrent.SynchronousQueue;

public class Customer implements Runnable{
    private static int counter = 0 ;
    private final  int id = counter++;
    private final WaitPerson waitPerson;
    private SynchronousQueue<Plate> plates = new SynchronousQueue<Plate>();
    public void deliver(Plate p) throws InterruptedException {
        plates.put(p);
    }
    public Customer(WaitPerson wp){
        waitPerson = wp;
    }
    @Override
    public void run() {
        for (Course course:Course.values()){
            Food food = course.randomSelection();
            try {
                waitPerson.placeOrder(this,food);
                System.out.println(this+" eating "+plates.take());
            }catch (InterruptedException e){
                System.out.println(this + "waiting for "+course+" interrupted");
                break;
            }
        }
    }
    public String toString(){
        return "Customer "+id+" ";
    }
}
