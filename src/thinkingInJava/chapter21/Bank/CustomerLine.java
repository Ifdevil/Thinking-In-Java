package thinkingInJava.chapter21.Bank;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 客户队列
 */
public class CustomerLine extends ArrayBlockingQueue<Customer> {

    public CustomerLine(int maxLineSize){
        super(maxLineSize);
    }
    public String toString(){
        if(this.size()==0){
            return "[Empty]";
        }
        StringBuilder result = new StringBuilder();
        for (Customer consumer:this){
            result.append(consumer);
        }
        return result.toString();
    }

}
