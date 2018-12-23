package thinkingInJava.chapter15.exercise38;

/**
 * 使用装饰器模式
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }

    public static void main(String[] args) {
        milkCoffee milk = new milkCoffee(new Coffee());
        System.out.println(milk.toString());
    }
}

class Decorator extends Coffee{
    private Coffee coffee;
    public Decorator(Coffee c){
        this.coffee = c;
    }
    public String toString() {
        return coffee.toString();
    }
}

class milkCoffee extends Decorator{
    private final String steamedMilk = "steamedMilk";
    public milkCoffee(Coffee c) {
        super(c);
    }

    public String toString(){
        return steamedMilk+":"+super.toString();
    }
}
