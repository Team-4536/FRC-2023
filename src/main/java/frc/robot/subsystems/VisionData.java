package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionData {

    NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

    NetworkTableEntry tv = limelight.getEntry("tv");    
    NetworkTableEntry tx = limelight.getEntry("tx");
    NetworkTableEntry ty = limelight.getEntry("ty");
    NetworkTableEntry ta = limelight.getEntry("ta");

    boolean targetFound = (tv.getDouble(0.0) == 1);
    double targetX = tx.getDouble(0.0);
    double targetY = ty.getDouble(0.0);
    double targetArea = ta.getDouble(0.0);

    public void sendTelemetry(){

      SmartDashboard.putBoolean("Target Found", targetFound);
      SmartDashboard.putNumber("Target X", targetX);
      SmartDashboard.putNumber("Target Y", targetY);
      SmartDashboard.putNumber("Target Area", targetArea);

    }

    
    
}
