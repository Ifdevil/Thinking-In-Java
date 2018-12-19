package thinkingInJava.chapter15.exercise24;

import java.util.Map;

public class ClassTypeCapture<T> {

    Class<T> kind;
    Map<String,Factory> map;
    public ClassTypeCapture(Class<T> type, Map<String,Factory> map){
        this.kind = type;
        this.map = map;
    }

    public void addType(String typename,Factory factory){
        if(map.get(typename)==null){
            map.put(typename,factory);
        }
    }

    public Object creatNew(String typeName){
        if(map.containsKey(typeName)){
            return map.get(typeName).create();
        }
        System.out.println(typeName+"class not available");
        return null;
    }
}
class HouseFactory implements Factory<House>{

    @Override
    public House create() {
        return new House();
    }
}
class BuildingFactory implements Factory<Building>{

    @Override
    public Building create() {
        return new Building();
    }
}
class Building{};

class House extends Building {};