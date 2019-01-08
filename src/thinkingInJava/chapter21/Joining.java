package thinkingInJava.chapter21;

/**
 * 加入一个线程
 */
public class Joining {

    public static void main(String[] args) {
        Sleeper
                sleepey = new Sleeper("Sleepy",1500),
                grumpy = new Sleeper("Grumpy",1000);
        Joiner
                dopey = new Joiner("Dopey",sleepey),
                doc = new Joiner("Doc",grumpy);
        grumpy.interrupt();
    }
}
class Sleeper extends Thread{
    private int duration;
    public Sleeper(String name,int sleepTime){
        super(name);
        this.duration = sleepTime;
        start();
    }
    public void run(){
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName()+" was interrupted. "+"isInterrupted() "+isInterrupted() );
            e.printStackTrace();
            return;
        }
        System.out.println(getName()+" has awakened");
    }
}
class Joiner extends Thread{
    private Sleeper sleeper;
    public Joiner(String name,Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }
    public void run(){
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println(getName()+" join completed");
    }
}
