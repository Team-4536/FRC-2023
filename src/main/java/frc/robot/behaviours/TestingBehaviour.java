package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;
import frc.robot.subsystems.PositionData;

public class TestingBehaviour {

    static PositionData p = new PositionData();

    public static Consumer<Robot> periodic = r -> {

        double d = r.drive.pidController.tick(r.gyro.globGyroscope.getAngle(), Robot.dt, true);
        driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis()*2 - 1);
        p.updateBasic(Robot.dt, r.drive, r.gyro);
        p.sendTelemetry();

    };

    public static Consumer<Robot> testLog = r -> {
        telemetryUtil.debugLog("log test");
    };

}
