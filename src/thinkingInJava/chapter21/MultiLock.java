package thinkingInJava.chapter21;

/**
 * 同一个任务获取同一个对象锁
 */
public class MultiLock {

    public synchronized void f1(int count){
        if(--count>0){
            System.out.println("f1 call f2 "+count);
            f2(count);
        }
    }
    public synchronized void f2(int count){
        if(--count>0){
            System.out.println("f2 call f1"+count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        MultiLock multiLock = new MultiLock();
        new Thread(){
            public void run(){
                multiLock.f1(10);
            }
        }.start();
    }
}
