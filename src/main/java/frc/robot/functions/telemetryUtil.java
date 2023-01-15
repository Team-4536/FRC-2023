package frc.robot.functions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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













    public static Set<Class<?>> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
            .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
            .filter(line -> line.endsWith(".class"))
            .map(line -> getClass(line, packageName))
            .collect(Collectors.toSet());
    }

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }













    public static void initChoosers() {


        for(DashFuncChooser d : funcChoosers) {

            SmartDashboard.putData(d.name, d.chooser);
            d.chooser.setDefaultOption("nothing", "");

            Set<Class<?>> classes = findAllClassesUsingClassLoader("frc.robot.behaviours");
            for(Class<?> c : classes) {
                for(Field m : c.getDeclaredFields()){

                    if(m.getType().isAssignableFrom(Consumer.class)){
                        String name = c.getSimpleName() + "." + m.getName();
                        d.chooser.addOption(name, name);
                    }
                }
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
                    continue;
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
