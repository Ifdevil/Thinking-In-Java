package thinkingInJava.chapter21;

import java.util.concurrent.TimeUnit;

/**
 * 内部类创建线程
 */
public class ThreadVariations {
    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }

}
class InnerThread1{
    private int countdown = 5;
    private Inner inner;
    private class Inner extends Thread {
        Inner(String name){
            super(name);
            start();
        }
        public void run(){
            try {
                while (true){
                    System.out.println(this);
                    if(--countdown == 0){
                        return;
                    }
                sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        public String toString(){
            return getName()+": "+countdown;
        }
    }
    public InnerThread1(String name){
        inner = new Inner(name);
    }
}
class InnerThread2{
    private int countdown = 5;
    private Thread t;
    public InnerThread2(String name){
        t = new Thread(name){
          public void run(){
              try {
                  while (true){
                      System.out.println(this);
                      if(--countdown == 0){
                          return;
                      }
                      sleep(100);
                  }
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
            public String toString(){
                return getName()+": "+countdown;
            }
        };
        t.start();
    }
}
class InnerRunnable1{
    private int countdown = 5;
    private Inner inner;
    private class Inner implements Runnable{
        Thread t;
        Inner(String name) {
            t = new Thread(this,name);
            t.start();
        }
        @Override
        public void run() {
            try {
                while (true){
                    System.out.println(this);
                    if(--countdown == 0){
                        return;
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public String toString(){
            return t.getName()+": "+countdown;
        }
    }

    public InnerRunnable1(String name) {
        this.inner = new Inner(name);
    }
}
class InnerRunnable2{
    private int countdown = 5;
    private Thread t;
    public InnerRunnable2(String name){
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        System.out.println(this);
                        if(--countdown == 0){
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            public String toString(){
                return t.getName()+": "+countdown;
            }
        },name);
        t.start();
    }
}
class ThreadMethod{
    private int countdown = 5;
    private Thread t;
    private String name;
    public ThreadMethod(String name){
        this.name = name;
    }
    public void runTask(){
        if(t==null){
            t = new Thread(name){
                public void run() {
                    try {
                        while (true){
                            System.out.println(this);
                            if(--countdown == 0){
                                return;
                            }
                            TimeUnit.MILLISECONDS.sleep(100);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                public String toString(){
                    return t.getName()+": "+countdown;
                }
            };
            t.start();
        }
    }
}

