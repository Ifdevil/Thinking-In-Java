package thinkingInJava.chapter17;

import java.util.*;

public class SortedSetDemo {

    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<>();
        Collections.addAll(sortedSet,"one two three four five six seven eight".split(" "));
        System.out.println(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        System.out.println(low);
        System.out.println(high);
        Iterator<String> itr = sortedSet.iterator();
        for (int i = 0; i <= 6 ; i++) {
            if (i==3){
                low = itr.next();
            }
            if(i==6){
                high = itr.next();
            }else{
                itr.next();
            }
        }
        System.out.println(low);
        System.out.println(high);


    }
}
