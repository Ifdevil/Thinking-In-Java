package thinkingInJava.chapter14;

public class RealObject implements Interfacle {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("somethingElse"+args);
    }
}
