package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DriveData {

    public final Spark frontLeftDrive = new Spark(Constants.DRIVE_FRONT_LEFT_PORT);
    public final Spark frontRightDrive = new Spark(Constants.DRIVE_FRONT_RIGHT_PORT);
    public final Spark backLeftDrive = new Spark(Constants.DRIVE_BACK_LEFT_PORT);
    public final Spark backRightDrive = new Spark(Constants.DRIVE_BACK_RIGHT_PORT);

    public DriveData() {
        this.frontLeftDrive.setInverted(Constants.DRIVE_FRONT_LEFT_FLIPPED);
        this.frontRightDrive.setInverted(Constants.DRIVE_FRONT_RIGHT_FLIPPED);
        this.backLeftDrive.setInverted(Constants.DRIVE_BACK_LEFT_FLIPPED);
        this.backRightDrive.setInverted(Constants.DRIVE_BACK_RIGHT_FLIPPED);


    }

    public void sendTelemetry() {
        SmartDashboard.putNumber("FL Pwr", frontLeftDrive.get());
        SmartDashboard.putNumber("FR Pwr", frontRightDrive.get());
        SmartDashboard.putNumber("BL Pwr", backLeftDrive.get());
        SmartDashboard.putNumber("BR Pwr", backRightDrive.get());

    }
}
