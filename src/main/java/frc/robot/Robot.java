// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import java.util.function.Consumer;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.behaviours.BehaviourUtil;
import frc.robot.behaviours.FinalBehaviour;
import frc.robot.functions.telemetryUtil;
import frc.robot.subsystems.DriveData;
import frc.robot.subsystems.InputData;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    //#region fns!
    private static final Consumer<Robot> NULL_FUNC = r -> { };

    public static Consumer<Robot> ROBOT_INIT_FUNC = FinalBehaviour.robotInit;
    public static Consumer<Robot> ROBOT_PER_FUNC = NULL_FUNC;

    public static Consumer<Robot> TELEOP_INIT_FUNC = NULL_FUNC;
    public static Consumer<Robot> TELEOP_PER_FUNC = NULL_FUNC;

    public static Consumer<Robot> AUTO_INIT_FUNC = NULL_FUNC;
    public static Consumer<Robot> AUTO_PER_FUNC = NULL_FUNC;

    public static Consumer<Robot> TEST_INIT_FUNC = NULL_FUNC;
    public static Consumer<Robot> TEST_PER_FUNC = NULL_FUNC;

    public static Consumer<Robot> DISABLED_INIT_FUNC = BehaviourUtil.stopDrive;
    public static Consumer<Robot> DISABLED_PER_FUNC = BehaviourUtil.stopDrive;
    //#endregion


    public static int tickMod = 0;

    public DriveData drive;
    public InputData input;







    // runs once when the robot is turned on
    @Override
    public void robotInit() {

        telemetryUtil.initChoosers();

        this.drive = new DriveData();
        this.input = new InputData();

        ROBOT_INIT_FUNC.accept(this);
    }

    // runs constantly, no matter the mode
    // don't put motor control stuff in here lol
    @Override
    public void robotPeriodic() {

        tickMod = (++tickMod)%10;

        telemetryUtil.grabChoosers();

        this.drive.sendTelemetry();
        this.input.sendTelemetry();

        ROBOT_PER_FUNC.accept(this);
    }




    // runs once when autos start
    @Override
    public void autonomousInit() { AUTO_INIT_FUNC.accept(this);  }

    // runs repeatedly during autos
    @Override
    public void autonomousPeriodic() { AUTO_PER_FUNC.accept(this); }








    // runs once on teleop start
    @Override
    public void teleopInit() {  TELEOP_INIT_FUNC.accept(this); }

    // runs repeatedly during teleop
    @Override
    public void teleopPeriodic() { TELEOP_PER_FUNC.accept(this);  }






    // when you stop the robot, this gets called
    @Override
    public void disabledInit() { DISABLED_INIT_FUNC.accept(this);  }

    // while the robot is disabled, this is repeatedly running
    // no driving in here
    @Override
    public void disabledPeriodic() { DISABLED_PER_FUNC.accept(this);  }








    // runs once at the begining of test mode
    // intended for testing unvalidaded code
    @Override
    public void testInit() { TEST_INIT_FUNC.accept(this); }

    // runs repeatedly during test mode
    @Override
    public void testPeriodic() { TEST_PER_FUNC.accept(this); }
}
