package thinkingInJava.chapter16;

import java.lang.reflect.Array;

public class Generated {

    public static <T> T[] array(T[] a,Generator<T> gen){
        return new CollectionData<T>(gen,a.length).toArray(a);
    }
    public static <T> T[] array(Class<T> type,Generator<T> gen,int size){
        T[] a = (T[])Array.newInstance(type,size);
        return new CollectionData<T>(gen,size).toArray(a);
    }
 }
