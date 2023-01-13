package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.telemetryUtil;

public class TestingBehaviour {


    public static Consumer<Robot> init = new Consumer<Robot>() {
        @Override public void accept(Robot r) {

            telemetryUtil.initChoosers();
        }
    };



    public static Consumer<Robot> periodic = new Consumer<Robot>(){
        @Override public void accept(Robot r) {

            driveUtil.setPowerTank(r.drive, r.input.controller.getLeftY(), r.input.controller.getLeftX(), r.input.controller.getRightTriggerAxis());
            telemetryUtil.grabChoosers();
        }
    };
}
