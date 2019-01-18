package thinkingInJava.chapter21.Car;

public class WheelRobot extends Robot {

    public WheelRobot(RobotPool p) {
        super(p);
    }

    @Override
    protected void performService() {
        System.out.println(this+" installing Wheel");
        assembler.car().addWheels();
    }
}
