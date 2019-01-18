package thinkingInJava.chapter21.RestaurantPak;

public class Order {

    private static int counter = 0;
    private final int id = counter++;
    private final Customer customer;
    private final WaitPerson waitPerson;
    private final Food food;
    public Order(Customer c,WaitPerson w,Food f){
        customer = c;
        waitPerson = w;
        food = f;
    }
    public Food item(){
        return food;
    }
    public Customer getCustomer(){
        return customer;
    }
    public WaitPerson getWaitPerson(){
        return waitPerson;
    }
    public String toString(){
        return "Order: "+id+" item: "+food+" for:"+customer+" served by"+waitPerson;
    }
}
