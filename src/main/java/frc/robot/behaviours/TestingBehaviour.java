package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;

public class TestingBehaviour {
     

    public static Consumer<Robot> periodic = r -> {
        driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());

    };

    public static Consumer<Robot> auto_1 = r -> {
        telemetryUtil.debugLog("auto 1 is executing");
    };

    public static Consumer<Robot> auto_2 = r -> {
        telemetryUtil.debugLog("auto 2 is executing");
    };
}
