package thinkingInJava.chapter17;

import thinkingInJava.chapter16.Generator;

import java.time.format.TextStyle;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class QueueBehavior {

    private static int count =10;
    static <T> void test(Queue<T> queue, Generator<T> generator){
        for (int i = 0;i<count;i++){
            queue.offer(generator.next());
        }
        while (queue.peek()!=null){
            System.out.println(queue.remove()+"");
        }
        System.out.println();
    }

    static class Gen implements Generator<String>{
        String[] s = "one two three four five six seven eight nine ten".split(" ");
        int i ;
        @Override
        public String next() {
            return s[i++];
        }
    }

    public static void main(String[] args) {
        test(new LinkedList<String>(),new Gen());
        test(new PriorityQueue<String>(),new Gen());
        test(new ArrayBlockingQueue<String>(count),new Gen());
        test(new ConcurrentLinkedQueue<>(),new Gen());
        test(new LinkedBlockingQueue<>(),new Gen());
        test(new PriorityBlockingQueue<>(),new Gen());

    }
}
