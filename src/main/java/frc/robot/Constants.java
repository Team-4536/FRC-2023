// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.lang.reflect.Field;
import java.util.Scanner;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;









/* TESTING CHECKLIST:
    file open
    file reading
    prop get
    correct split
    props set without exceptions (final may be an issue)
    use works properly
    all types
    all types of funky inputs

    comments!


    also you should make some documentation on this
*/
public final class Constants {


    // root config, please do not change from within another config
    public static final String MASTER_CONFIG_NAME = "robotConfig.cnf";


    //
    // NOTE: please do not add sublasses to this because the config system cannot parse into those
    // if you really want it ask rob :)
    // RN constants are prefixed with the subsys.
    //


    public static final int DRIVE_FRONT_LEFT_PORT = 0;
    public static final int DRIVE_FRONT_RIGHT_PORT = 0;
    public static final int DRIVE_BACK_LEFT_PORT = 0;
    public static final int DRIVE_BACK_RIGHT_PORT = 0;

    public static final boolean DRIVE_FRONT_LEFT_FLIPPED = false;
    public static final boolean DRIVE_FRONT_RIGHT_FLIPPED = false;
    public static final boolean DRIVE_BACK_LEFT_FLIPPED = false;
    public static final boolean DRIVE_BACK_RIGHT_FLIPPED = false;




    public static final int CONTROLLER_A_PORT = 0;
    public static final int CONTROLLER_B_PORT = 0;



    











    public void load() {
        load(Constants.MASTER_CONFIG_NAME);
    }

    private void load(String fileName) {

        try (Scanner s = new Scanner(Filesystem.getDeployDirectory().toString() + fileName)) {
            int lineNmb = 0;
            String line = "";
            try{

                while(s.hasNextLine()){

                    //clean the line
                    line = s.nextLine();
                    line = line.replaceAll("\\ ", "");
                    line = line.replaceAll("\t", "");


                    if(line.indexOf("//") == 0){
                        continue; }


                    //buy insurance
                    String[] split = line.split("\\:");
                    if(split.length != 2) {
                        throw new Exception("String split failed: wrong arg count"); }



                    // recurse to other files
                    if(split[0] == "use"){
                        load(split[1]); }




                    //fucking die from cringe
                    Field f = getClass().getDeclaredField(split[0]);

                    if(f.getType().toGenericString() == "int"){
                        f.setInt(null, Integer.valueOf(split[1]));
                    }
                    else if (f.getType().toGenericString() == "boolean"){
                        f.setBoolean(null, Boolean.valueOf(split[1]));
                    }
                    else if (f.getType().toGenericString() == "double"){
                        f.setDouble(null, Double.valueOf(split[1]));
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
