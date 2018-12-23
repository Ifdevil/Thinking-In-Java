package thinkingInJava.chapter15;

/**
 * 参数协变
 * @param <T>
 */
public interface GenericGetter<T extends GenericGetter<T>> {
    T get();
}
interface Getter extends GenericGetter<Getter>{}

class GenericsAndReturnTypes{
    void test(Getter getter){
        Getter result = getter.get();
        GenericGetter gg = getter.get();
    }
}
