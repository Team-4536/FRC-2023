package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DriveData {

    public final PWMSparkMax frontLeftDrive = new PWMSparkMax(Constants.DRIVE_FRONT_LEFT_PORT);
    public final PWMSparkMax frontRightDrive = new PWMSparkMax(Constants.DRIVE_FRONT_RIGHT_PORT);
    public final PWMSparkMax backLeftDrive = new PWMSparkMax(Constants.DRIVE_BACK_LEFT_PORT);
    public final PWMSparkMax backRightDrive = new PWMSparkMax(Constants.DRIVE_BACK_RIGHT_PORT);

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
