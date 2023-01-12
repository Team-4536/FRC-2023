package frc.robot;

import frc.robot.functions.TelemetryUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

import edu.wpi.first.wpilibj.Filesystem;

public class Configured {



    public static int DRIVE_FRONT_LEFT_PORT = 0;
    public static int DRIVE_FRONT_RIGHT_PORT = 0;
    public static int DRIVE_BACK_LEFT_PORT = 0;
    public static int DRIVE_BACK_RIGHT_PORT = 0;

    public static boolean DRIVE_FRONT_LEFT_FLIPPED = false;
    public static boolean DRIVE_FRONT_RIGHT_FLIPPED = false;
    public static boolean DRIVE_BACK_LEFT_FLIPPED = false;
    public static boolean DRIVE_BACK_RIGHT_FLIPPED = false;


    public static int CONTROLLER_PORT = 0;
    public static int JOYSTICK_PORT = 1;








    private static final Consumer<Robot> NULL_FUNC = new Consumer<Robot>() {
        @Override public void accept(Robot r) { }
    };

    public static Consumer<Robot> ROBOT_INIT_FUNC = NULL_FUNC;
    public static Consumer<Robot> ROBOT_PER_FUNC = NULL_FUNC;

    public static Consumer<Robot> TELEOP_INIT_FUNC = NULL_FUNC;
    public static Consumer<Robot> TELEOP_PER_FUNC = NULL_FUNC;

    public static Consumer<Robot> AUTO_INIT_FUNC = NULL_FUNC;
    public static Consumer<Robot> AUTO_PER_FUNC = NULL_FUNC;

    public static Consumer<Robot> DISABLED_INIT_FUNC = NULL_FUNC;
    public static Consumer<Robot> DISABLED_PER_FUNC = NULL_FUNC;










    public void load() {
        load(Constants.MASTER_CONFIG_NAME);
        TelemetryUtil.sysLog.add("Config loaded " +  Constants.MASTER_CONFIG_NAME);
    }

    private void load(String fileName) {

        try (Scanner s = new Scanner(new File(Filesystem.getDeployDirectory().toString() + "/" + fileName))) {
            int lineNmb = 0;
            String line = "";
            try{
                while(s.hasNextLine()){

                    //clean the line
                    line = s.nextLine();
                    line = line.replaceAll("\\ ", "");
                    line = line.replaceAll("\t", "");


                    if(line.indexOf("//") == 0 || line.equals("")){
                        lineNmb++;
                        continue; }

                    //buy insurance
                    String[] split = line.split("\\:");
                    if(split.length != 2) {
                        throw new Exception("String split failed: wrong arg count"); }



                    // recurse to other files
                    if(split[0].equals("use")){
                        load(split[1]); 
                        lineNmb++;
                        continue;
                    }




                    //fucking die from cringe
                    Field f = getClass().getDeclaredField(split[0]);
                    f.setAccessible(true);

                    if(f.getType().toGenericString().equals("int")){
                        f.setInt(null, Integer.valueOf(split[1]));
                    }
                    else if (f.getType().toGenericString().equals("boolean")){
                        f.setBoolean(null, Boolean.valueOf(split[1]));
                    }
                    else if (f.getType().toGenericString().equals("double")){
                        f.setDouble(null, Double.valueOf(split[1]));
                    }
                    else if (f.getType().toGenericString().equals("public abstract interface java.util.function.Consumer<T>")) {

						int idx = split[1].lastIndexOf(".");
						String className = split[1].substring(0, idx);
						String funcName = split[1].substring(idx+1);

						Class<?> c = Class.forName(className);

						f.set(null, c.getDeclaredField(funcName).get(null));

					} 
                    else {
                        throw new Exception("Type " + f.getType().toGenericString() + " not implemented! (ask rob)");
                    }

                    // TelemetryUtil.debugLog(f.getName() + " set to " + split[1]);

                    lineNmb++;
                }








            } catch(Exception e) {
                TelemetryUtil.logError("CONFIG PARSE ERR: " + e.getMessage() + "\n Line number: " + lineNmb + ", Line: " + line); 
            };

            s.close();
        }
        catch(Exception e) {
            TelemetryUtil.logError("CONFIG FILE OPEN ERR: " + e.getMessage());
        }





        if(Configured.DISABLED_INIT_FUNC == NULL_FUNC) {
            TelemetryUtil.logWarning("Disabled init function is not set"); }

        if(Configured.DISABLED_PER_FUNC == NULL_FUNC) {
            TelemetryUtil.logWarning("Disabled preriodic function is not set"); }

    }
}
