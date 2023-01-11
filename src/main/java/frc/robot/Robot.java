// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.functions.TelemetryUtil;
import frc.robot.subsystems.DriveData;
import frc.robot.subsystems.InputData;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {




    public DriveData drive;
    public InputData input;





    // runs once when the robot is turned on
    @Override
    public void robotInit() {
        new Constants().load();

        this.drive = new DriveData();
        this.input = new InputData();

        Constants.ROBOT_INIT_FUNC.accept(this);
        TelemetryUtil.clearDashboard();
    }

    // runs constantly, no matter the mode
    // don't put motor control stuff in here lol
    @Override
    public void robotPeriodic() { Constants.ROBOT_PER_FUNC.accept(this); TelemetryUtil.updateDashboard(); }




    // runs once when autos start
    @Override
    public void autonomousInit() { Constants.AUTO_INIT_FUNC.accept(this);  }

    // runs repeatedly during autos
    @Override
    public void autonomousPeriodic() { Constants.AUTO_PER_FUNC.accept(this); }








    // runs once on teleop start
    @Override
    public void teleopInit() {  Constants.TELEOP_INIT_FUNC.accept(this); }

    // runs repeatedly during teleop
    @Override
    public void teleopPeriodic() { Constants.TELEOP_PER_FUNC.accept(this);  }






    // when you stop the robot, this gets called
    @Override
    public void disabledInit() { Constants.DISABLED_INIT_FUNC.accept(this);  }

    // while the robot is disabled, this is repeatedly running
    // no driving in here
    @Override
    public void disabledPeriodic() { Constants.DISABLED_PER_FUNC.accept(this);  }








    // runs once at the begining of test mode
    // intended for testing unvalidaded code
    @Override
    public void testInit() { }

    // runs repeatedly during test mode
    @Override
    public void testPeriodic() {}
}
