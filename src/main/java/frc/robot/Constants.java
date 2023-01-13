// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.Consumer;

import frc.robot.behaviours.TestingBehaviour;

public final class Constants {

    public static final String MASTER_CONFIG_NAME = "robotConfig.cnf";




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








    private static final Consumer<Robot> NULL_FUNC = new Consumer<Robot>() {
        @Override public void accept(Robot r) { } };

    public static final Consumer<Robot> ROBOT_INIT_FUNC = NULL_FUNC;
    public static final Consumer<Robot> ROBOT_PER_FUNC = TestingBehaviour.botPeriodic;

    public static final Consumer<Robot> TELEOP_INIT_FUNC = NULL_FUNC;
    public static final Consumer<Robot> TELEOP_PER_FUNC = TestingBehaviour.teleOpPeriodic;

    public static final Consumer<Robot> AUTO_INIT_FUNC = NULL_FUNC;
    public static final Consumer<Robot> AUTO_PER_FUNC = NULL_FUNC;

    public static final Consumer<Robot> DISABLED_INIT_FUNC = TestingBehaviour.disabledInit;
    public static final Consumer<Robot> DISABLED_PER_FUNC = TestingBehaviour.disabledPeriodic;

}
