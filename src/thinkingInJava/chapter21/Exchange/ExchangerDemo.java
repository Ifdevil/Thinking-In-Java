package thinkingInJava.chapter21.Exchange;

import thinkingInJava.chapter21.Semaphore.Fat;

import java.util.List;
import java.util.concurrent.*;

public class ExchangerDemo {

    static int size = 10;
    static int delay = 5;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
        List<Fat>
                producerList = new CopyOnWriteArrayList<Fat>(),
                comsumerList = new CopyOnWriteArrayList<Fat>();
        exec.execute(new ExchangerProducer<Fat>(xc, new BasicGenerator<>(Fat.class),producerList));
        exec.execute(new ExchangerConsumer<Fat>(xc,comsumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}
