package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Configured;

public class DriveData {

    public Spark frontLeftDrive;
    public Spark frontRightDrive;
    public Spark backLeftDrive;
    public Spark backRightDrive;

    public DriveData() {
        this.frontLeftDrive = new Spark(Configured.DRIVE_FRONT_LEFT_PORT);
        this.frontRightDrive = new Spark(Configured.DRIVE_FRONT_RIGHT_PORT);
        this.backLeftDrive = new Spark(Configured.DRIVE_BACK_LEFT_PORT);
        this.backRightDrive = new Spark(Configured.DRIVE_BACK_RIGHT_PORT);

        this.frontLeftDrive.setInverted(Configured.DRIVE_FRONT_LEFT_FLIPPED);
        this.frontRightDrive.setInverted(Configured.DRIVE_FRONT_RIGHT_FLIPPED);
        this.backLeftDrive.setInverted(Configured.DRIVE_BACK_LEFT_FLIPPED);
        this.backRightDrive.setInverted(Configured.DRIVE_BACK_RIGHT_FLIPPED);


    }

    public void sendTelemetry() {
        SmartDashboard.putNumber("FL Pwr", frontLeftDrive.get());
        SmartDashboard.putNumber("FR Pwr", frontRightDrive.get());
        SmartDashboard.putNumber("BL Pwr", backLeftDrive.get());
        SmartDashboard.putNumber("BR Pwr", backRightDrive.get());

    }
}
