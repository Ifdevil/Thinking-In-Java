package thinkingInJava.chapter15;

import org.omg.CORBA.PUBLIC_MEMBER;

public class TwoTuple<A,B> {
    public final A first;
    public final B second;

    TwoTuple(A a,B b){
        this.first = a;
        this.second = b;
    }

}
