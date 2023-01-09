package frc.robot.functions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class telemetryUtil {
    

    public static void warnOn(boolean condition, String title) {
        if(condition){
            SmartDashboard.putString("*WARNING* " + title, "");
        }
    }
}
