package frc.robot.functions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
        SmartDashboard.putString("[WARNING] " + title, String.valueOf(Robot.tickMod));
    }

    public static void logError(String msg) {
        SmartDashboard.putString("[ERROR] " + msg, String.valueOf(Robot.tickMod));
    }

    public static void debugLog(String msg) {
        SmartDashboard.putString("[LOG] " + msg, String.valueOf(Robot.tickMod));
    }




    public static SendableChooser<String> autoInitPicker;
    public static SendableChooser<String> autoPerPicker;
    public static SendableChooser<String> testInitPicker;
    public static SendableChooser<String> testPerPicker;

    public static void initChoosers() {


        ArrayList<String> funcNames = new ArrayList<String>(List.of(
            "BehaviourUtil.stopDrive",
            "FinalBehaviour.robotInit",
            "FinalBehaviour.teleOpPeriodic",
            "TestingBehaviour.init",
            "TestingBehaviour.periodic",
            "TestingBehaviour.auto_1",
            "TestingBehaviour.auto_2",
            "AuoFunctions.autoTest"
        ));


        autoInitPicker = new SendableChooser<String>();
        SmartDashboard.putData("Auto init picker", autoInitPicker);
        autoPerPicker = new SendableChooser<String>();
        SmartDashboard.putData("Auto periodic chooser", autoPerPicker);

        testInitPicker = new SendableChooser<String>();
        SmartDashboard.putData("Test init picker", testInitPicker);
        testPerPicker = new SendableChooser<String>();
        SmartDashboard.putData("Test periodic chooser", testPerPicker);

        autoInitPicker.setDefaultOption("nothing", "");
        autoPerPicker.setDefaultOption("nothing", "");
        testInitPicker.setDefaultOption("nothing", "");
        testPerPicker.setDefaultOption("nothing", "");
        telemetryUtil.debugLog("hello :)");

        for(int i = 0; i < funcNames.size(); i++) {
            String s = funcNames.get(i);
            autoInitPicker.addOption(s, s);
            autoPerPicker.addOption(s, s);
            testInitPicker.addOption(s, s);
            testPerPicker.addOption(s, s);
        }

    }


    @SuppressWarnings("unchecked")
    private static void setFuncFromChooser(SendableChooser<String> chooser, Consumer<Consumer<Robot>> target) {

        try{

            String s = chooser.getSelected();

            if(s.equals("")) {
                target.accept( r -> { } );
                return;
            }



            int idx = s.indexOf(".");
            Class<?> c = Class.forName("frc.robot.behaviours." + s.substring(0,idx));
            Field f = c.getField(s.substring(idx+1));

            if(f.get(null) instanceof Consumer<?>) {
                target.accept((Consumer<Robot>)f.get(null)); }

        }
        catch (Exception e) {
            telemetryUtil.logError("getting choosers failed: " + e.toString());
        }
    }

    public static void grabChoosers() {

        setFuncFromChooser(autoInitPicker, x -> Robot.AUTO_INIT_FUNC = x);
        setFuncFromChooser(autoPerPicker, x -> Robot.AUTO_PER_FUNC = x);
        setFuncFromChooser(testInitPicker, x -> Robot.TEST_INIT_FUNC = x);
        setFuncFromChooser(testPerPicker, x -> Robot.TEST_PER_FUNC = x);
    }

}
