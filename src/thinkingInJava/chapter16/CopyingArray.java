package thinkingInJava.chapter16;

import java.io.ObjectInputStream;
import java.util.Arrays;

/**
 * 数组的复制
 */
public class CopyingArray {

    public static void main(String[] args) {

        int[] a = new int[7];
        int[] b = new int[10];
        System.out.println(new int[]{1,2});
        Arrays.fill(a,47);
        Arrays.fill(b,99);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.arraycopy(a,0,b,0,a.length);
        System.out.println(Arrays.toString(b));

        Object c = "1";
        Object d = "1";
        System.out.println(c.equals(d));
        //Arrays.equals(c,d);

    }
}
