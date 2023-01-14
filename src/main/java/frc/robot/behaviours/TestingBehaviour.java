package frc.robot.behaviours;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;

public class TestingBehaviour {

    public static Consumer<Robot> botPeriodic = new Consumer<Robot>() {
        @Override public void accept(Robot r) {
            r.drive.sendTelemetry();
            r.input.sendTelemetry();
            r.vision.sendTelemetry();
        }
    };

    public static Consumer<Robot> teleOpPeriodic = new Consumer<Robot>(){
        @Override public void accept(Robot r) {

            driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());
            String in = SmartDashboard.getString("Log Input", "");
            SmartDashboard.putString("Input return", in);

            SmartDashboard.putNumber("asdfjicadjsf", r.vision.targetX);
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
