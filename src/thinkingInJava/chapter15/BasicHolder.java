package thinkingInJava.chapter15;

/**
 * 自限定类型
 *      古怪的循环泛型:Java泛型关乎返回值和参数，BasicHolder可以产生使用导出类作为
 *                     参数和返回值得基类
 *                     基类用导出类替代其参数
 * @param <T>
 */
public class BasicHolder<T> {

    T element;
    void set(T arg){
        this.element = arg;
    }
    T get(){
        return element;
    }
    void f(){
        System.out.println(element.getClass().getSimpleName());
    }

}

class Subtype extends BasicHolder<Subtype>{
    public static void main(String[] args) {
        Subtype t1 = new Subtype(),t2 = new Subtype();
        t1.set(t2);
        Subtype t3 = t1.get();
        t1.f();

    }
}


/**
 * 自限定
 * @param <T>
 */
class SelfBounded<T extends SelfBounded<T>>{}

class A extends SelfBounded<A>{}

class B extends SelfBounded<A>{}

class D{}

//class E extends SelfBounded<D>{}
