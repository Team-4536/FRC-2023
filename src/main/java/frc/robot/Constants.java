// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

    public static final String MASTER_CONFIG_NAME = "robotConfig.cnf";

    public static final int TICKS_PER_REVOLUTION = 2048;

    public static final int WHEEL_DIAMETER_INCHES = 7;

    public static final double TICKS_PER_INCH = TICKS_PER_REVOLUTION/(Math.PI * WHEEL_DIAMETER_INCHES);


    public static final double POWER_SCALE_UPPER_BOUND = 1.0;
    public static final double POWER_SCALE_LOWER_BOUND = 0.2;




    public static final int DRIVE_FRONT_LEFT_PORT = 2;
    public static final int DRIVE_FRONT_RIGHT_PORT = 1;
    public static final int DRIVE_BACK_LEFT_PORT = 3;
    public static final int DRIVE_BACK_RIGHT_PORT = 4;

    public static final int ENCODER_FRONT_LEFT_PORT = 0;
    public static final int ENCODER_FRONT_RIGHT_PORT = 0;
    public static final int ENCODER_BACK_LEFT_PORT = 0;
    public static final int ENCODER_BACK_RIGHT_PORT = 0;

    public static final boolean DRIVE_FRONT_LEFT_FLIPPED = true;
    public static final boolean DRIVE_FRONT_RIGHT_FLIPPED = false;
    public static final boolean DRIVE_BACK_LEFT_FLIPPED = true;
    public static final boolean DRIVE_BACK_RIGHT_FLIPPED = false;



    public static final int CONTROLLER_PORT = 0;
    public static final int JOYSTICK_PORT = 1;
}
