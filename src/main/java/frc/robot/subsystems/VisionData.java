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

      SmartDashboard.putBoolean("Target Found", getTargets());
      SmartDashboard.putNumber("Target X", getX());
      SmartDashboard.putNumber("Target Y", getY());
      SmartDashboard.putNumber("Target Area", getArea());

    }

    public boolean getTargets(){

      targetFound = (!(tv.getDouble(0.0) == 0));

      return targetFound;
    }

    public double getX(){

      targetX = tx.getDouble(0.0);
      return targetX;

    }

    public double getY(){

      targetY = ty.getDouble(0.0);
      return targetY;

    }
    
    public double getArea(){

      targetArea = ta.getDouble(0.0);
      return targetArea;

    }
    
}
