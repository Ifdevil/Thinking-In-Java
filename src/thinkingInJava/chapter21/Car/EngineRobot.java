package thinkingInJava.chapter21.Car;

public class EngineRobot extends Robot {

    public EngineRobot(RobotPool p) {
        super(p);
    }

    @Override
    protected void performService() {
        System.out.println(this+" installing engine");
        assembler.car().addEngine();
    }
}
