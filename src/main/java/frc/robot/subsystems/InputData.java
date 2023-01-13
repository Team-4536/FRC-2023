package frc.robot.subsystems;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.functions.TelemetryUtil;
import frc.robot.Constants;

public final class InputData {

    public XboxController controller;
    public Joystick joystick;



    public InputData() {
        controller = new XboxController(Constants.CONTROLLER_PORT);
        joystick = new Joystick(Constants.JOYSTICK_PORT);
    }


    public void sendTelemetry() {

        TelemetryUtil.warnOn(!controller.isConnected(), "Controller A is disconnected");
        TelemetryUtil.warnOn(!joystick.isConnected(), "Controller B is disconnected");

    }
}
