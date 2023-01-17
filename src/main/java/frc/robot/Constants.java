// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

    public static final long NANOS_PER_SECOND = 1000000000;


    public static final double POWER_SCALE_UPPER_BOUND = 1.0;
    public static final double POWER_SCALE_LOWER_BOUND = 0.2;




    public static final int DRIVE_FRONT_LEFT_PORT = 2;
    public static final int DRIVE_FRONT_RIGHT_PORT = 1;
    public static final int DRIVE_BACK_LEFT_PORT = 3;
    public static final int DRIVE_BACK_RIGHT_PORT = 4;

    public static final boolean DRIVE_FRONT_LEFT_FLIPPED = true;
    public static final boolean DRIVE_FRONT_RIGHT_FLIPPED = false;
    public static final boolean DRIVE_BACK_LEFT_FLIPPED = true;
    public static final boolean DRIVE_BACK_RIGHT_FLIPPED = false;



    public static final int CONTROLLER_PORT = 0;
    public static final int JOYSTICK_PORT = 1;

    public static final class VisionInfo{

        public static final double TAG_SIZE_IN2 = 36;

        public static final double LIME_TOP_LIM_DEGREE = 20.5;
        public static final double LIME_BOT_LIM_DEGREE = -20.5;

        public static final double LIME_RIGHT_LIM_DEGREE = 27;
        public static final double LIME_LEFT_LIM_DEGREE = -27;

        public static final double LIMELIGHT_FOV_ASPECT = Math.sin(Math.toRadians(27))/Math.sin(Math.toRadians(20.5));


    }
}
