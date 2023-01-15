package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.controllers.PIDController;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;

public class TestingBehaviour {

    static PIDController controller = new PIDController();
    static double vel = 0;


    public static Consumer<Robot> periodic = r -> {

        double d = r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), d, r.input.controller.getRightTriggerAxis()*2 - 1);

    };

    public static Consumer<Robot> testLog = r -> {
        telemetryUtil.debugLog("log test");
    };

}
