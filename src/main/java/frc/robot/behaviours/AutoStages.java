package frc.robot.behaviours;

import java.util.function.*;

import frc.robot.Robot;
import frc.robot.functions.driveUtil;
import frc.robot.functions.gyroUtil;

public class AutoStages {

    // stages return T/F, is the stage done, T indicates end
    public Function<Robot, Boolean> turnTo90 = r -> {

        driveUtil.setPowerTank(r.drive, 0, 0.1f, 1.0);
        return Math.abs(gyroUtil.wrapAngle(r.gyro.globGyroscope.getAngle() - 90)) < 5;
    };

}
