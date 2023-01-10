// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.function.Consumer;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.functions.telemetryUtil;









public final class Constants {

    // root config, please do not change from within another config
    public static final String MASTER_CONFIG_NAME = "robotConfig.cnf";



    //
    // NOTE: please do not add sublasses to this because the config system cannot parse into those,
    // if you really want it ask rob :)
    //
    // RN constants are prefixed with the subsys.
    //



    public static int DRIVE_FRONT_LEFT_PORT = 3;
    public static int DRIVE_FRONT_RIGHT_PORT = 0;
    public static int DRIVE_BACK_LEFT_PORT = 2;
    public static int DRIVE_BACK_RIGHT_PORT = 1;

    public static boolean DRIVE_FRONT_LEFT_FLIPPED = false;
    public static boolean DRIVE_FRONT_RIGHT_FLIPPED = true;
    public static boolean DRIVE_BACK_LEFT_FLIPPED = false;
    public static boolean DRIVE_BACK_RIGHT_FLIPPED = true;


    public static int CONTROLLER_A_PORT = 0;
    public static int CONTROLLER_B_PORT = 0;

    public static final double POWER_SCALE_UPPER_BOUND = 1.0;
    public static final double POWER_SCALE_LOWER_BOUND = 0.2;





    private static final Consumer<Robot> NULL_FUNC = new Consumer<Robot>() {
        @Override public void accept(Robot r) {
            telemetryUtil.warnOn(true, "null function in behaviour");
        }
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

        SmartDashboard.putString("Config loaded", MASTER_CONFIG_NAME);
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

                        SmartDashboard.putString(f.getName(), split[1]);
					} 
                    else {
                        throw new Exception("Type " + f.getType().toGenericString() + " not implemented! (ask rob)");
                    }

                    lineNmb++;
                }








            } catch(Exception e) {
                SmartDashboard.putString("Error parsing config file", e.getMessage() + "\n Line number: " + lineNmb + ", Line: " + line); 
            };

            s.close();
        }
        catch(Exception e) {
            SmartDashboard.putString("Error loading config", e.getMessage());
        }


    }










}
