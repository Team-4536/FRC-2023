package frc.robot.behaviours;

import java.util.function.Consumer;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.functions.driveUtil;

public class AuoFunctions {

    public static Consumer<Robot> autoTest = robot -> {
    
        int backLeftE = robot.drive.backLeftEncoder.get();



        if(backLeftE >= Constants.TICKS_PER_INCH * 48){
            driveUtil.setPowerUniform(robot.drive, 0);
        } else{
            driveUtil.setPowerUniform(robot.drive, 0.1);
        }
    };
}
