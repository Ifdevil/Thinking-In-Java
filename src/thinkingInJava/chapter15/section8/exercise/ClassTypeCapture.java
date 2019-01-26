package thinkingInJava.chapter15.section8;

import java.util.Map;

/**
 * 擦除的补偿
 * @param <T>
 */
public class ClassTypeCapture<T> {
    Class<T> kind;

    /**
     *  exercise21
     */
    Map<String,Class<?>> map;

    public void addType(String typename,Class<T> kind){
        if(map.get(typename)==null){
            map.put(typename,kind);
        }
    }

    public T creatNew(String typeName) throws IllegalAccessException, InstantiationException {
        if(map.containsKey(typeName)){
            return (T) map.get(typeName).newInstance();
        }
        System.out.println(typeName+"class not available");
        return null;
    }

    public ClassTypeCapture(Class<T> type){
        this.kind = type;
    }
    public boolean f(Object arg){
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));

        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);
        System.out.println(ctt2.f(new Building()));
        System.out.println(ctt2.f(new House()));
    }
}
class Building{};
class House extends Building{};
