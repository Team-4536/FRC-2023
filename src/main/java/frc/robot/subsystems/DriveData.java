package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DriveData {

    public final PWMSparkMax frontLeftDrive = new PWMSparkMax(Constants.Drive.FRONT_LEFT_PORT);
    public final PWMSparkMax frontRightDrive = new PWMSparkMax(Constants.Drive.FRONT_RIGHT_PORT);
    public final PWMSparkMax backLeftDrive = new PWMSparkMax(Constants.Drive.BACK_LEFT_PORT);
    public final PWMSparkMax backRightDrive = new PWMSparkMax(Constants.Drive.BACK_RIGHT_PORT);

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
