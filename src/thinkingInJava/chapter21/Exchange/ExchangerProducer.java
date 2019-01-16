package thinkingInJava.chapter21.Exchange;

import thinkingInJava.chapter16.Generator;

import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    ExchangerProducer(Exchanger<List<T>> exchg,Generator<T> gen,List<T> hold){
        exchanger = exchg;
        generator = gen;
        holder = hold;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                for (int i = 0; i < ExchangerDemo.size; i++) {
                    holder.add(generator.next());
                }
                holder = exchanger.exchange(holder);
            }
        }catch (InterruptedException e){

        }
    }
}
