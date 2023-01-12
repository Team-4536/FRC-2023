package frc.robot.subsystems;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Configured;
import frc.robot.functions.telemetryUtil;

public final class InputData {

    public XboxController controller;
    public Joystick joystick;



    public InputData() {
        controller = new XboxController(Configured.CONTROLLER_PORT);
        joystick = new Joystick(Configured.JOYSTICK_PORT);
    }


    public void sendTelemetry() {

        telemetryUtil.warnOn(!controller.isConnected(), "Controller A is disconnected");
        telemetryUtil.warnOn(!joystick.isConnected(), "Controller B is disconnected");

    }
}
