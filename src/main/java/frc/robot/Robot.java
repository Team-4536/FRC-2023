// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.functions.driveUtil;
import frc.robot.subsystems.DriveData;
import frc.robot.subsystems.InputData;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {



    DriveData drive = new DriveData();
    InputData input = new InputData();







    // runs once when the robot is turned on
    @Override
    public void robotInit() { }

    // runs constantly, no matter the mode
    // don't put motor control stuff in here lol
    @Override
    public void robotPeriodic() { 
        drive.sendTelemetry();
        input.sendTelemetry();
    }






    // runs once when autos start
    @Override
    public void autonomousInit() { }

    // runs repeatedly during autos
    @Override
    public void autonomousPeriodic() {}








    // runs once on teleop start
    @Override
    public void teleopInit() { }

    // runs repeatedly during teleop
    @Override
    public void teleopPeriodic() {
        driveUtil.setPowerMechanum(drive, input.controllerA.getLeftX(), input.controllerA.getLeftY(), input.controllerA.getRightX());
    }








    // when you stop the robot, this gets called
    @Override
    public void disabledInit() {
        driveUtil.stop(drive);
    }

    // while the robot is disabled, this is repeatedly running
    // no driving in here
    @Override
    public void disabledPeriodic() {
        driveUtil.stop(drive);
    }








    // runs once at the begining of test mode
    // intended for testing unvalidaded code
    @Override
    public void testInit() { }

    // runs repeatedly during test mode
    @Override
    public void testPeriodic() {}
}
