package frc.robot.behaviours;

import java.util.function.Consumer;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;

public class TestingBehaviour {

    public static Consumer<Robot> botPeriodic = new Consumer<Robot>() {
        @Override public void accept(Robot r) {
            r.drive.sendTelemetry();
            r.input.sendTelemetry();
        }
    };

    public static Consumer<Robot> teleOpPeriodic = new Consumer<Robot>(){
        @Override public void accept(Robot r) {

            driveUtil.setPowerMechanum(r.drive,
                r.input.controllerA.getLeftX(),
                r.input.controllerA.getLeftY(),
                r.input.controllerA.getRightX());
        }
    };
    



    public static Consumer<Robot> disabledInit = new Consumer<Robot>() {
        @Override public void accept(Robot r) {
            driveUtil.stop(r.drive);
        }
    };

    public static Consumer<Robot> disabledPeriodic = new Consumer<Robot>() {
        @Override public void accept(Robot r) {
            driveUtil.stop(r.drive);
        }
    };
}
