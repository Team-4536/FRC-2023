package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;


public class FinalBehaviour {

    public static Consumer<Robot> robotInit = r -> {
        Robot.TELEOP_PER_FUNC = FinalBehaviour.teleOpPeriodic;

        Robot.DISABLED_INIT_FUNC = BehaviourUtil.stopDrive;
        Robot.DISABLED_PER_FUNC = BehaviourUtil.stopDrive;
    };

    public static Consumer<Robot> teleOpPeriodic = r -> {
        driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());
    };
}
