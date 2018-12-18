package thinkingInJava.chapter15.exercise21;

import java.util.HashMap;
import java.util.Map;

/**
 * 擦除的补偿 exercise21
 * @param <T>
 */
public class ClassTypeCapture<T> {
    Class<T> kind;
    /**
     *exercise21
     */
    Map<String,Class<?>> map;

    public ClassTypeCapture(Class<T> type){
        this.kind = type;
    }
    public ClassTypeCapture(Class<T> type,Map<String,Class<?>> map){
        this.kind = type;
        this.map = map;
    }
    public void addType(String typename,Class<?> kind){
        if(map.get(typename)==null){
            map.put(typename,kind);
        }
    }

    public Object creatNew(String typeName) throws IllegalAccessException, InstantiationException {
        if(map.containsKey(typeName)){
            return map.get(typeName).newInstance();
        }
        System.out.println(typeName+"class not available");
        return null;
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

        ClassTypeCapture<Building> ctt3 = new ClassTypeCapture<>(Building.class,new HashMap<String,Class<?>>());
        ctt3.addType("Building",Building.class);
        ctt3.addType("House",House.class);
        try {
            Building building = (Building)ctt3.creatNew("Building");
            House house = (House)ctt3.creatNew("House");
            System.out.print("b.getClass().getName(): ");
            System.out.println(building.getClass().getName());
            System.out.print("h.getClass().getName(): ");
            System.out.println(house.getClass().getName());
            System.out.print("House h is instance House: ");
            System.out.println(house instanceof House);
            System.out.print("House h is instance of Building: ");
            System.out.println(house instanceof Building);
            System.out.print("Building b is instance of House: ");
            System.out.println(building instanceof House);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}

class Building{};

class House extends Building {};
