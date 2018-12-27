package thinkingInJava.chapter17;

import java.util.*;

public class Unsupported {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("A B C D E F G H I J K L M N".split(""));
        Collection c = list;
        Collection<String> sublist = list.subList(1,8);
        Collection<String> c2 = new ArrayList<>(sublist);

        try {
            c.retainAll(c2);
        }catch (Exception e){
            e.printStackTrace();
        }

        Collections.unmodifiableCollection(new ArrayList<>(list));

    }
}
