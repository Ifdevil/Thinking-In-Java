package thinkingInJava.chapter16.exercise1;

import java.util.Arrays;

public class Ex1 {

    public static <T> void test(T[] t){
        System.out.println(Arrays.asList(t));
    }

    public static void main(String[] args) {
        test(new Integer[5]);
    }
}
