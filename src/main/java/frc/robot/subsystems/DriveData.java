package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DriveData {


    public final WPI_VictorSPX frontLeftDrive = new WPI_VictorSPX(Constants.DRIVE_FRONT_LEFT_PORT);
    public final WPI_VictorSPX frontRightDrive = new WPI_VictorSPX(Constants.DRIVE_FRONT_RIGHT_PORT);
    public final WPI_VictorSPX backLeftDrive = new WPI_VictorSPX(Constants.DRIVE_BACK_LEFT_PORT);
    public final WPI_VictorSPX backRightDrive = new WPI_VictorSPX(Constants.DRIVE_BACK_RIGHT_PORT);

    public DriveData() {
        this.frontLeftDrive.setInverted(Constants.Drive.FRONT_LEFT_FLIPPED);
        this.frontRightDrive.setInverted(Constants.Drive.FRONT_RIGHT_FLIPPED);
        this.backLeftDrive.setInverted(Constants.Drive.BACK_LEFT_FLIPPED);
        this.backRightDrive.setInverted(Constants.Drive.BACK_RIGHT_FLIPPED);


    }

    public void sendTelemetry() {
        SmartDashboard.putNumber("FL Pwr", frontLeftDrive.get());
        SmartDashboard.putNumber("FR Pwr", frontRightDrive.get());
        SmartDashboard.putNumber("BL Pwr", backLeftDrive.get());
        SmartDashboard.putNumber("BR Pwr", backRightDrive.get());

    }
}
