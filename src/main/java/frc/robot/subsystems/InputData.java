package frc.robot.subsystems;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.functions.telemetryUtil;

public final class InputData {

    public XboxController controller = new XboxController(Constants.CONTROLLER_PORT);
    public Joystick joystick = new Joystick(Constants.JOYSTICK_PORT);





    public void sendTelemetry() {

        telemetryUtil.warnOn(!controller.isConnected(), "Controller A is disconnected");
        telemetryUtil.warnOn(!joystick.isConnected(), "Controller B is disconnected");

    }
}
