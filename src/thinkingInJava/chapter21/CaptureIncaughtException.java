package thinkingInJava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 捕获异常
 */

//thinkingInJava.chapter21.HandlerThreadFactory@677327b6 creating new Thread
//created Thread[Thread-0,5,main]
//eh = thinkingInJava.chapter21.MyUncaughtExceptionHandler@14ae5a5
//run() by Thread[Thread-0,5,main]
//eh = thinkingInJava.chapter21.MyUncaughtExceptionHandler@14ae5a5
//thinkingInJava.chapter21.HandlerThreadFactory@677327b6 creating new Thread
//created Thread[Thread-1,5,main]
//eh = thinkingInJava.chapter21.MyUncaughtExceptionHandler@33db6245
//caught java.lang.RuntimeException
/**
 * 为什么会生成两个线程？？？
 */
public class CaptureIncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread2());
    }

}
class ExceptionThread2 implements Runnable{
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by "+t);
        System.out.println("eh = "+ t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught "+e);
    }
}
class HandlerThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this+" creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created "+t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}
