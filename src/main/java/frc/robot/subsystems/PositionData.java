package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.V2d;
import frc.robot.functions.gyroUtil;

public class PositionData {


    public int FLPrev = 0;
    public int FRPrev = 0;
    public int BLPrev = 0;
    public int BRPrev = 0;

    public Vector2d prevPosition;
    public Vector2d prevVelocity;

    public double angularVelocity;
    public double prevAngle;


    public void updateBasic(double dt, DriveData drive, GyroData gyro) {

        double angle = gyro.localGyroscope.getAngle();
        angularVelocity = gyroUtil.wrapAngle(gyro.globGyroscope.getAngle() - prevAngle) * dt;


        V2d flAcc = new V2d(-1, 1).normalized().rotateDegrees(angle);
        V2d frAcc = new V2d(1, 1).normalized().rotateDegrees(angle);

        // adds distance moved by each motor, in the direction of the bot + mechanum wheel mods
        //      (Ex. front left exerts force to the front left, not just the front)
        V2d deltaP = getMotorPosDeltaBasic(drive.FLEncoder, FLPrev, flAcc);
        deltaP = deltaP.add(getMotorPosDeltaBasic(drive.FREncoder, FRPrev, frAcc));
        deltaP = deltaP.add(getMotorPosDeltaBasic(drive.BLEncoder, BLPrev, frAcc));
        deltaP = deltaP.add(getMotorPosDeltaBasic(drive.BREncoder, BRPrev, flAcc));


        angularVelocity = gyro.globGyroscope.getAngle() - prevAngle;
        prevAngle = gyro.globGyroscope.getAngle();
        FLPrev = drive.FLEncoder.get();
        FRPrev = drive.FREncoder.get();
        BLPrev = drive.BLEncoder.get();
        BRPrev = drive.BREncoder.get();
    }

    V2d getMotorPosDeltaBasic(Encoder e, int prevTicks, V2d motorDirVector) {

        return motorDirVector.multiply(
            (wrapEncoderVal(e.get() - prevTicks) / Constants.ENCODER_TICKS_PER_REV)
            * Constants.WHEEL_CIRCUMFERENCE_INCHES);
    }






    public void update(double dt, DriveData drive, GyroData gyro) {

        double angle = gyro.localGyroscope.getAngle();
        angularVelocity = gyroUtil.wrapAngle(gyro.globGyroscope.getAngle() - prevAngle) * dt;


        V2d flAcc = new V2d(-1, 1).normalized().rotateDegrees(angle);
        V2d frAcc = new V2d(1, 1).normalized().rotateDegrees(angle);

        V2d deltaP = getMotorPosDeltaBasic(drive.FLEncoder, FLPrev, flAcc);
        deltaP = deltaP.add(getMotorPosDeltaBasic(drive.FREncoder, FRPrev, frAcc));
        deltaP = deltaP.add(getMotorPosDeltaBasic(drive.BLEncoder, BLPrev, frAcc));
        deltaP = deltaP.add(getMotorPosDeltaBasic(drive.BREncoder, BRPrev, flAcc));


        angularVelocity = gyro.globGyroscope.getAngle() - prevAngle;
        prevAngle = gyro.globGyroscope.getAngle();
        FLPrev = drive.FLEncoder.get();
        FRPrev = drive.FREncoder.get();
        BLPrev = drive.BLEncoder.get();
        BRPrev = drive.BREncoder.get();
    }

    //motor force dir = what force is exerted on the groud when rotating forward
    // FL dir would be pointing back in/out
    V2d getMotorForce(GyroData gyro, V2d motorPos, Encoder e, int prevTicks, V2d motorForceDir,
        V2d angularVelocity, V2d linearVelocity, double dt) {

        double encoderVelocity = wrapEncoderVal(
                                    e.get()/Constants.ENCODER_TICKS_PER_REV) -
                                    (prevTicks/Constants.ENCODER_TICKS_PER_REV) * dt * (2*Math.PI);

        // assuming that encoder values go up with forward motion, could be wrong, and could flip w/ the side of the bot
        V2d rollerVelocity = new V2d(0, 1).rotateDegrees(gyro.globGyroscope.getAngle()).multiply(-encoderVelocity * Constants.WHEEL_RADIUS);
        V2d motorVelocity = ((V2d.cross(motorPos.length(), angularVelocity)).add(linearVelocity));
        V2d groundSpeed = motorVelocity.add(rollerVelocity);

        V2d forceNormal = motorForceDir.rotateDegrees(gyro.globGyroscope.getAngle());
        double velocityAlongNormal = groundSpeed.dot(forceNormal);


        double mu = (velocityAlongNormal < Constants.staticFriction)? Constants.staticFriction : Constants.dynamicFriction;

        return new V2d().sub(forceNormal.multiply(velocityAlongNormal).multiply(mu));
    }










    public int wrapEncoderVal(int x) {
        int r = x;
        while(r < -(Constants.ENCODER_TICKS_PER_REV/2)) { r += Constants.ENCODER_TICKS_PER_REV; }
        while(r > (Constants.ENCODER_TICKS_PER_REV/2)) { r -= Constants.ENCODER_TICKS_PER_REV; }
        return r;
    }




    public void sendTelemetry() {
        SmartDashboard.putString("Position Esitmate", String.valueOf(this.prevPosition));
        SmartDashboard.putString("Velocity Esitmate", String.valueOf(this.prevVelocity));
    }


}
