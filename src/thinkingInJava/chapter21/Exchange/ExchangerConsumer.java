package thinkingInJava.chapter21.Exchange;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;
    ExchangerConsumer(Exchanger<List<T>> exg,List<T> hold){
        exchanger = exg;
        holder = hold;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                holder = exchanger.exchange(holder);
                for (T x:holder){
                    value = x;
                    holder.remove(x);
                }
            }
        }catch (InterruptedException e){
        }
        System.out.println("Final value: "+value);
    }
}
