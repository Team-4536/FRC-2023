package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroData {

    public final AHRS globGyroscope = new AHRS();
    public final AHRS localGyroscope = new AHRS();

    public void sendTelemetry(){

        SmartDashboard.putNumber("Global Angle", globGyroscope.getAngle());
        SmartDashboard.putNumber("Local Angle", localGyroscope.getAngle());

    }
    
}