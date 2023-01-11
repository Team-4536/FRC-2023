package frc.robot.functions;

import java.util.ArrayList;
import java.util.Set;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TelemetryUtil {


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








    public static void updateDashboard() {


        for(String s : debugLog) {
            SmartDashboard.putString(s, ""); }
        //SmartDashboard.putString("** Debug Log", built);

        for(String s : sysLog) {
            SmartDashboard.putString(s, ""); }
        //SmartDashboard.putString("** Systems Log", built);

        if(!SmartDashboard.getKeys().contains("Log Input")) {
            SmartDashboard.putString("Log Input", ""); }
    }



    public static void clearDashboard() {
        Set<String> keys = SmartDashboard.getKeys();

        for(String s : keys) {
            SmartDashboard.delete(s);
        }
    }


}
