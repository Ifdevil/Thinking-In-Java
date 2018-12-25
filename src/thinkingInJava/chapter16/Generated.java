package thinkingInJava.chapter16;

public class Generated {

    public static <T> T[] array(T[] a,Generator<T> gen){
        return new CollectionData<T>(gen,a.length).toArray(a);
    }
}
