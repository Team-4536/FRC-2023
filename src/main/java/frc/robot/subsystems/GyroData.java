package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroData {

    public final AHRS gyroscope = new AHRS();

    public void sendTelemetry(){

        SmartDashboard.putNumber("Angle", gyroscope.getAngle());

    }
    
}
