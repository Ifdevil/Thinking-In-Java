package thinkingInJava.chapter16;

import java.util.Random;

public class ArrayOfGenericType<T> {
    T[] array;
    @SuppressWarnings("unchecked")
    ArrayOfGenericType(int size){
        //array = new T[size];
        array = (T[]) new Object[size];  //执行错误 Object can't cast to String
    }

    /**
     *  Illegle
     */
//    public <U> U[] makeArray(){
//        return new U[10];
//    }

    public <U> U[] makeArray(U[] u){
        return u;
    }

    public <U> U[] makeArrays(){
        return (U[]) new Object[10];
    }

    public static void main(String[] args) {
        ArrayOfGenericType<String> a = new ArrayOfGenericType<>(5);
        Object[] b = new Object[10];
        String[] c = new String[10];
        Object[] d = c;
        d[0] = "1";
        String[] e = (String[]) b;//不能执行
    }
}
