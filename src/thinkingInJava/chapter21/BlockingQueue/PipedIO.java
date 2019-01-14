package thinkingInJava.chapter21.BlockingQueue;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 任务间使用管道进行 输入/ 输出
 */
public class PipedIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
class Sender implements Runnable{
    private PipedWriter out = new PipedWriter();
    private Random random = new Random(47);
    public PipedWriter getPipedWriter(){
        return out;
    }
    @Override
    public void run() {
        try {
            while (true){
                for (char c = 'A';c <= 'z'; c++){
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                }
            }

        } catch (IOException e) {
            System.out.println(e+" Sender write interrupted");
        } catch (InterruptedException e) {
            System.out.println(e+" Sender sleep interrupted");
        }
    }
}
class Receiver implements Runnable{
    private PipedReader in;
    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }
    @Override
    public void run() {
        try {
            while (true){
                System.out.println("Read: "+(char)in.read()+",");
            }
        }catch (IOException e){
            System.out.println(e+" Reveiver read exception");
        }
    }
}
