package frc.robot.functions;

import java.util.ArrayList;
import java.util.Set;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class telemetryUtil {


    public static void warnOn(boolean condition, String title) {
        if(condition){
            sysLog.add("[WARNING] " + title);
        }
    }

    public static void logError(String msg) {
        sysLog.add("[ERROR] " + msg);
    }

    public static void log(String msg) {
        debugLog.add(msg);
    }


    public static ArrayList<String> sysLog = new ArrayList<String>();
    public static ArrayList<String> debugLog = new ArrayList<String>();








    public static void updateDashboard() {

        String built;

        built = "";
        for(String s : debugLog) {
            built += "\t - " + s + "\n"; }
        SmartDashboard.putString("** Debug Log", built);

        built = "";
        for(String s : sysLog) {
            built += "\t - " + s + "\n"; }
        SmartDashboard.putString("** Systems Log", built);
    }



    public static void clearDashboard() {
        Set<String> keys = SmartDashboard.getKeys();

        for(String s : keys) {
            SmartDashboard.delete(s);
        }
    }


}
