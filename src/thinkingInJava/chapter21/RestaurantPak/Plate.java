package thinkingInJava.chapter21.RestaurantPak;

public class Plate {

    private final Order order;
    private final Food food;
    public Plate(Order od,Food f){
        order = od;
        food = f;
    }
    public Order getOrder(){
        return order;
    }
    public Food getFood(){
        return food;
    }
    public String toString(){
        return food.toString();
    }
}
