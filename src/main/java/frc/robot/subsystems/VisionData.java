package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionData {

    public NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

    public NetworkTableEntry tv = limelight.getEntry("tv");    
    public NetworkTableEntry tx = limelight.getEntry("tx");
    public NetworkTableEntry ty = limelight.getEntry("ty");
    public NetworkTableEntry ta = limelight.getEntry("ta");

    public boolean targetFound = (!(tv.getDouble(0.0) == 0));
    public double targetX = tx.getDouble(0.0);
    public double targetY = ty.getDouble(0.0);
    public double targetArea = ta.getDouble(0.0);

    public void sendTelemetry(){

      SmartDashboard.putBoolean("Target Found", targetFound);
      SmartDashboard.putNumber("Target X", targetX);
      SmartDashboard.putNumber("Target Y", targetY);
      SmartDashboard.putNumber("Target Area", targetArea);

    }

    
    
}
