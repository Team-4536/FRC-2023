package frc.robot.functions;

import frc.robot.subsystems.DriveData;
import frc.robot.subsystems.GyroData;
import frc.robot.subsystems.VisionData;

public class visionUtil {

    public static void alignHorizontal(DriveData drive, VisionData vision, GyroData gyro){

        driveUtil.setPowerTank(drive, .5, 0, 1);

    }
    
}
