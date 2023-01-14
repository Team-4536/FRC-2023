package frc.robot.functions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.reflections.Reflections;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;






class DashFuncChooser {
    SendableChooser<String> chooser;
    String name;
    Consumer<Consumer<Robot>> setTarget;

    public DashFuncChooser() { };
    public DashFuncChooser(SendableChooser<String> c, String n, Consumer<Consumer<Robot>> t) {
        this.chooser = c;
        this.name = n;
        this.setTarget = t; };
}









public class telemetryUtil {


    public static void warnOn(boolean condition, String title) {
        if(condition){
            logWarning(title);
        }
    }

    public static void logWarning(String title) {
        SmartDashboard.putString("[WARNING] " + title, String.valueOf(Robot.timeSinceInit).substring(0, 5));
    }

    public static void logError(String msg) {
        SmartDashboard.putString("[ERROR] " + msg, String.valueOf(Robot.timeSinceInit).substring(0, 5));
    }

    public static void debugLog(String msg) {
        SmartDashboard.putString("[LOG] " + msg, String.valueOf(Robot.timeSinceInit).substring(0, 5));
    }





    public static final List<DashFuncChooser> funcChoosers = List.of(
        new DashFuncChooser(new SendableChooser<String>(), "Auto Init", x -> { Robot.AUTO_INIT_FUNC = x; }),
        new DashFuncChooser(new SendableChooser<String>(), "Auto Periodic", x -> { Robot.AUTO_PER_FUNC = x; }),
        new DashFuncChooser(new SendableChooser<String>(), "Test Init", x -> { Robot.TEST_INIT_FUNC = x; }),
        new DashFuncChooser(new SendableChooser<String>(), "Test Periodic", x -> { Robot.TEST_PER_FUNC = x; })
    );

    public static void initChoosers() {


        ArrayList<String> funcNames = new ArrayList<String>(List.of(
            "BehaviourUtil.stopDrive",
            "FinalBehaviour.robotInit",
            "FinalBehaviour.teleOpPeriodic",
            "TestingBehaviour.init",
            "TestingBehaviour.periodic",
            "TestingBehaviour.testLog",
            "AuoFunctions.autoTest"
        ));


        for(DashFuncChooser d : funcChoosers) {

            SmartDashboard.putData(d.name, d.chooser);
            d.chooser.setDefaultOption("nothing", "");

            for(int i = 0; i < funcNames.size(); i++) {
                String s = funcNames.get(i);
                d.chooser.addOption(s, s);
            }
        }


    }



    @SuppressWarnings("unchecked")
    public static void grabChoosers() {

        for(DashFuncChooser d : funcChoosers) {

            try{

                String s = d.chooser.getSelected();

                if(s.equals("")) {
                    d.setTarget.accept( r -> { } );
                    return;
                }

                int idx = s.indexOf(".");
                Class<?> c = Class.forName("frc.robot.behaviours." + s.substring(0,idx));
                Field f = c.getField(s.substring(idx+1));

                if(f.get(null) instanceof Consumer<?>) {
                    d.setTarget.accept((Consumer<Robot>)f.get(null)); }

            }
            catch (Exception e) {
                telemetryUtil.logError("getting choosers failed: " + e.toString());
            }
        }
    }

}
