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

        driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());
        telemetryUtil.debugLog("periodic test");

    };

    public static Consumer<Robot> testLog = r -> {
        telemetryUtil.debugLog("log test");
    };

}
