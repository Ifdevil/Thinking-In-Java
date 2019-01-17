package thinkingInJava.chapter21.Bank;

/**
 * 客户
 */
public class Customer {
    private final int serviceTime;
    public Customer(int tm){
        serviceTime = tm;
    }
    public int getServiceTime(){
        return serviceTime;
    }
    public String toString(){
        return "["+serviceTime+"]";
    }

}
