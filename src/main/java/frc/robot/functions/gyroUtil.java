package frc.robot.functions;

import frc.robot.subsystems.GyroData;

public final class gyroUtil {

    public static void resetLocalGyro(GyroData gyro){

        gyro.localGyroscope.reset();

    }

    public static void resetGlobalGyro(GyroData gyro){

        gyro.globGyroscope.reset();
        
    }
    
}
