package thinkingInJava.chapter21;

public class SimpleThread extends Thread {

    private int countdown = 5;
    private static int threadCount = 0;
    public SimpleThread(){
        super(Integer.toString(++threadCount));
        start();
    }
    public String toString(){
        return "#"+getName()+"("+countdown+")";
    }

    public void run(){
        while (true){
            System.out.println(this);
            if(--countdown == 0){
                return;
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
