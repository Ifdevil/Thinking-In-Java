package thinkingInJava.chapter17;

/**
 * 容器性能测试
 * @param <C>
 */
public abstract class TestContainer<C> {

    String name;
    TestContainer(String name){
        this.name = name;
    }
    abstract int test(C container,TestParam tp);
}
