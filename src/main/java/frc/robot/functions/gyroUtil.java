package frc.robot.functions;

import frc.robot.subsystems.GyroData;

public final class gyroUtil {

    public static void resetLocalGyro(GyroData gyro){

        gyro.localGyroscope.reset();

    }

    public static void resetGlobalGyro(GyroData gyro){

        gyro.globGyroscope.reset();

    }


    public static double wrapAngle(double target) {

        while(target < -180){ target += 360; }
        while(target > 180){ target -= 360; }
        return target;
    }
}
