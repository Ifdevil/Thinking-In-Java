package thinkingInJava.chapter21.Car;

public class DriveTrainRobot extends Robot {

    public DriveTrainRobot(RobotPool p) {
        super(p);
    }

    @Override
    protected void performService() {
        System.out.println(this+" installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}
