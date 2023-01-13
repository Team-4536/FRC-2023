package frc.robot.functions;

import java.util.ArrayList;
import java.util.Set;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class telemetryUtil {


    public static void warnOn(boolean condition, String title) {
        if(condition){
            logWarning(title);
        }
    }

    public static void logWarning(String title) {
        sysLog.add("[WARNING] " + title);
    }

    public static void logError(String msg) {
        sysLog.add("[ERROR] " + msg);
    }

    public static void debugLog(String msg) {
        debugLog.add(msg);
    }


    public static ArrayList<String> sysLog = new ArrayList<String>();
    public static ArrayList<String> debugLog = new ArrayList<String>();
    public static boolean clearInputFlag = false;






    public static SendableChooser<String> AutoInitPicker;
    public static SendableChooser<String> AutoPerPicker;

    public static void initChoosers() {

        AutoInitPicker.setDefaultOption("nothing", "");
        AutoPerPicker.setDefaultOption("nothing", "");
    }

    public static void grabChoosers() {

    }


    public static void clearDashboard() {
        Set<String> keys = SmartDashboard.getKeys();

        for(String s : keys) {
            SmartDashboard.delete(s);
        }
    }


}
