package thinkingInJava.chapter18;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM args:  -XX:MaxDirectMemorySize = 10M
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024* 1024;
    private static int i = 1;
    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true){
            System.out.println(i++);
            unsafe.allocateMemory(_1MB);
        }

    }
}
