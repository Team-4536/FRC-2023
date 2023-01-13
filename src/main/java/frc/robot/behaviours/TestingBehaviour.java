package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;

public class TestingBehaviour {


    public static Consumer<Robot> botInit = new Consumer<Robot>() {
        @Override public void accept(Robot r) {

            telemetryUtil.initChoosers();

            Robot.TELEOP_PER_FUNC = FinalBehaviour.teleOpPeriodic;

            Robot.DISABLED_INIT_FUNC = BehaviourUtil.stopDrive;
            Robot.DISABLED_PER_FUNC = BehaviourUtil.stopDrive;
        }
    };



    public static Consumer<Robot> teleOpPeriodic = new Consumer<Robot>(){
        @Override public void accept(Robot r) {

            driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());
            telemetryUtil.grabChoosers();
        }
    };
}
