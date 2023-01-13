package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.functions.*;



public class BehaviourUtil {

    public static Consumer<Robot> stopDrive = new Consumer<Robot>() {
        @Override public void accept(Robot r) {
            driveUtil.stop(r.drive);
        }
    };

}
