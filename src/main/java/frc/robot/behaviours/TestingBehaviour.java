package frc.robot.behaviours;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.functions.visionUtil;

public class TestingBehaviour {


    public static Consumer<Robot> periodic = r -> {

        double d = r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        telemetryUtil.debugLog(String.valueOf(r.drive.pidController.integral));
        driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), d, r.input.controller.getRightTriggerAxis()*2 - 1);
        visionUtil.distanceFrom(r.vision.getArea());

    };

    public static Consumer<Robot> testLog = r -> {
        telemetryUtil.debugLog("log test");
    };

}
