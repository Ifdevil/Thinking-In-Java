package thinkingInJava.chapter21;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock
 * @param <T>
 */
public class ReadWriteList<T> {
    private ArrayList<T> lockedList;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    public ReadWriteList(int size,T initialValue){
        lockedList = new ArrayList<T>(Collections.nCopies(size,initialValue));
    }
    public T set(int index,T element){
        Lock wlock = lock.writeLock();
        wlock.lock();
        try {
            return lockedList.set(index,element);
        }finally {
            wlock.unlock();
        }
    }
    public T get(int index){
        Lock rlock = lock.readLock();
        rlock.lock();
        try {
            if(lock.getReadLockCount()>1){
                System.out.println(lock.getReadLockCount());
            }
            return lockedList.get(index);
        }finally {
            rlock.unlock();
        }
    }

    public static void main(String[] args) {
        new ReaderWriterlistTest(30,1);
    }
}
class ReaderWriterlistTest{
    ExecutorService exec = Executors.newCachedThreadPool();
    private final static int SIZE = 100;
    private static Random random = new Random(47);
    private ReadWriteList<Integer> list = new ReadWriteList<Integer>(SIZE,0);
    private class Writer implements Runnable{

        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    list.set(i,random.nextInt());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }catch (InterruptedException e){

            }
            System.out.println("Write finished,shutting down");
            exec.shutdownNow();
        }
    }
    private class Reader implements Runnable{

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    for (int i = 0; i < SIZE; i++) {
                        list.get(i);
                        TimeUnit.MILLISECONDS.sleep(1);
                    }
                }
            }catch (InterruptedException e){

            }
        }
    }
    public ReaderWriterlistTest(int reader,int writer){
        for (int i = 0; i < reader; i++) {
            exec.execute(new Reader());
        }
        for (int i = 0; i < writer; i++) {
            exec.execute(new Writer());
        }
    }
}
