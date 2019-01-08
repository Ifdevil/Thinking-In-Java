package thinkingInJava.chapter21;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 任务返回值
 */
public class CallableDemo{

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(exec.submit(new TaskWishResult(i)));
        }
        for (Future<String> fs:result){
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }
    }

}

class TaskWishResult implements Callable<String>{

    private int id;
    public TaskWishResult(int id){
        this.id = id;
    }
    public String call(){
        return "result of CallableDemo "+id;
    }
}

