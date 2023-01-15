package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;


public class FinalBehaviour {

    public static Consumer<Robot> teleOpPeriodic = r -> {

        driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());

    };
}

