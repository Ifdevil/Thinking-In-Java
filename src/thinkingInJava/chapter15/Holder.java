package thinkingInJava.chapter15;

public class Holder<T> {
    private T value;
    public Holder(){};
    public Holder(T val){value = val;}
    public void set(T val){
        value = val;
    }
    public T get(){
        return value;
    }
    public boolean equals(Object ob){
        return value.equals(ob);
    }

}
