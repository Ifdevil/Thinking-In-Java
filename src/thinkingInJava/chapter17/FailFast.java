package thinkingInJava.chapter17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FailFast {

    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        c.add("one");
        Iterator<String> ite = c.iterator();

        try {
            String next = ite.next();
            System.out.println(next);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
