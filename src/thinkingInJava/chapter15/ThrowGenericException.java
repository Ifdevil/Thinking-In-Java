package thinkingInJava.chapter15;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型：异常    随检查型异常的类型而变化的泛化代码
 */
public class ThrowGenericException {
    public static void main(String[] args) {
        ProcessRunner<String ,Failure1> runner = new ProcessRunner<>();
        for(int i = 0;i<3;i++){
            runner.add(new Processor1());
        }
        try {
            System.out.println(runner.processAll());
        } catch (Failure1 failure1) {
            failure1.printStackTrace();
        }

        ProcessRunner<Integer ,Failure2> runner2 = new ProcessRunner<>();
        for(int i = 0;i<3;i++){
            runner2.add(new Processor2());
        }
        try {
            System.out.println(runner2.processAll());
        } catch (Failure2 failure2) {
            failure2.printStackTrace();
        }
    }
}

interface Processor<T,E extends Exception>{
    void process(List<T> resultCollector) throws E;
}

class ProcessRunner<T,E extends Exception> extends ArrayList<Processor<T,E>>{

    List<T> processAll() throws E {
        List<T> resultCollector = new ArrayList<>();
        for (Processor<T,E> processor:this){
            processor.process(resultCollector);
        }
        System.out.println(resultCollector);
        return resultCollector;
    }
}

class Failure1 extends Exception{}
class Failure2 extends Exception{}

class Processor1 implements Processor<String,Failure1>{
    static int count = 3;
    @Override
    public void process(List<String> resultCollector) throws Failure1 {
        if(count-->1){
            //System.out.println("Help");
            resultCollector.add("Help");
        }else{
            //System.out.println("Ho");
            resultCollector.add("Ho");
        }
        if(count<0){
            throw new Failure1();
        }
    }
}

class Processor2 implements Processor<Integer,Failure2>{
    static int count = 2;

    @Override
    public void process(List<Integer> resultCollector) throws Failure2 {
        if(count--==0){
            //System.out.println("47");
            resultCollector.add(47);
        }else{
            //System.out.println("11");
            resultCollector.add(11);
        }
        if(count<0){
            throw new Failure2();
        }

    }
}

