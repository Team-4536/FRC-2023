package frc.robot.functions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import edu.wpi.first.math.Pair;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

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


        ArrayList<String> funcNames = new ArrayList<String>(List.of(
            "BehaviourUtil.stopDrive",
            "FinalBehaviour.robotInit",
            "FinalBehaviour.teleOpPeriodic",
            "TestingBehaviour.init",
            "TestingBehaviour.periodic",
            "TestingBehaviour.auto_1",
            "TestingBehaviour.auto_2"
        ));


        AutoInitPicker = new SendableChooser<String>();
        SmartDashboard.putData(AutoInitPicker);
        AutoPerPicker = new SendableChooser<String>();
        SmartDashboard.putData(AutoPerPicker);

        AutoInitPicker.setDefaultOption("nothing", "");
        AutoPerPicker.setDefaultOption("nothing", "");

        for(int i = 0; i < funcNames.size(); i++) {
            String s = funcNames.get(i);
            AutoInitPicker.addOption(s, s);
            AutoPerPicker.addOption(s, s);
        }

    }

    public static void grabChoosers() {
        try {

            String s = AutoInitPicker.getSelected();
            Class<?> c = Class.forName(s.substring(0,s.indexOf('.')));
            Field f = c.getField(s.substring(s.indexOf('.')+1));
            Object o = f.get(null);
            if(o instanceof Consumer<?>) {
                Robot.AUTO_INIT_FUNC = (Consumer<Robot>)o; }

            s = AutoPerPicker.getSelected();
            c = Class.forName(s.substring(0,s.indexOf('.')));
            f = c.getField(s.substring(s.indexOf('.')+1));
            o = f.get(null);
            if(o instanceof Consumer<?>) {
                Robot.AUTO_PER_FUNC = (Consumer<Robot>)o; }



        } catch(Exception e) {
            telemetryUtil.logError("getting choosers failed: " + e.toString());
        }
    }


    public static void clearDashboard() {
        Set<String> keys = SmartDashboard.getKeys();

        for(String s : keys) {
            SmartDashboard.delete(s);
        }
    }


}
