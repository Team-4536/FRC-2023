package frc.robot.subsystems;


import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.functions.telemetryUtil;

public final class InputData {

    public XboxController controllerA = new XboxController(Constants.CONTROLLER_A_PORT);
    public XboxController controllerB = new XboxController(Constants.CONTROLLER_B_PORT);




    public void sendTelemetry() {

        telemetryUtil.warnOn(!controllerA.isConnected(), "Controller A is disconnected");
        telemetryUtil.warnOn(!controllerB.isConnected(), "Controller B is disconnected");
        
    }
}
