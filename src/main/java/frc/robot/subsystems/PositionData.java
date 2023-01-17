package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.*;
import frc.robot.V2d;
import frc.robot.functions.gyroUtil;

public class PositionData {


    public int FLPrev = 0;
    public int FRPrev = 0;
    public int BLPrev = 0;
    public int BRPrev = 0;

    public V2d prevPosition = new V2d();
    public V2d prevVelocity = new V2d();
    public double angularVelocity;
    public double prevAngle;


    public void updateBasic(double dt, DriveData drive, GyroData gyro) {

        double angle = gyro.localGyroscope.getAngle();
        angularVelocity = gyroUtil.wrapAngle(gyro.globGyroscope.getAngle() - prevAngle) * dt;


        V2d flAcc = new V2d(-1, 1).normalized().rotateDegrees(angle);
        V2d frAcc = new V2d(1, 1).normalized().rotateDegrees(angle);

        V2d deltaP = getMotorPosDeltaBasic(drive.FLEncoder, FLPrev, flAcc);
        deltaP = deltaP.add(getMotorPosDeltaBasic(drive.FREncoder, FRPrev, frAcc));
        deltaP = deltaP.add(getMotorPosDeltaBasic(drive.BLEncoder, BLPrev, frAcc));
        deltaP = deltaP.add(getMotorPosDeltaBasic(drive.BREncoder, BRPrev, flAcc));

        deltaP = deltaP.divide(4);

        this.prevVelocity = deltaP;
        this.prevPosition = this.prevPosition.add(deltaP.multiply(Robot.dt));


        angularVelocity = gyro.globGyroscope.getAngle() - prevAngle;
        prevAngle = gyro.globGyroscope.getAngle();
        FLPrev = drive.FLEncoder.get();
        FRPrev = drive.FREncoder.get();
        BLPrev = drive.BLEncoder.get();
        BRPrev = drive.BREncoder.get();

    }

    V2d getMotorPosDeltaBasic(Encoder e, int prevTicks, V2d motorDirVector) {

        return motorDirVector.multiply(
            (wrapEncoderVal(e.get() - prevTicks) / (double)Constants.ENCODER_TICKS_PER_REV) * 2*Math.PI
            * Constants.WHEEL_CIRCUMFERENCE_INCHES);
    }






    public void update(double dt, DriveData drive, GyroData gyro) {

        double angle = gyro.localGyroscope.getAngle();
        angularVelocity = gyroUtil.wrapAngle(gyro.globGyroscope.getAngle() - prevAngle) * dt;


        V2d flAcc = new V2d(-1, 1).normalized().rotateDegrees(angle);
        V2d frAcc = new V2d(1, 1).normalized().rotateDegrees(angle);

        V2d deltaP = getMotorForce(angle, drive.FLEncoder, FLPrev, flAcc, Constants.FL_MOTOR_POS, angularVelocity, prevVelocity, Robot.dt);
        // deltaP = deltaP.add(getMotorForce(angle, drive.FREncoder, FRPrev, frAcc, Constants.FL_MOTOR_POS, angularVelocity, prevVelocity, Robot.dt));
        // deltaP = deltaP.add(getMotorForce(angle, drive.BLEncoder, BLPrev, frAcc, Constants.FL_MOTOR_POS, angularVelocity, prevVelocity, Robot.dt));
        // deltaP = deltaP.add(getMotorForce(angle, drive.BREncoder, BRPrev, flAcc, Constants.FL_MOTOR_POS, angularVelocity, prevVelocity, Robot.dt));

        deltaP = deltaP.divide(1);

        this.prevVelocity = deltaP;
        this.prevPosition = this.prevPosition.add(deltaP.multiply(Robot.dt));


        angularVelocity = gyro.globGyroscope.getAngle() - prevAngle;
        prevAngle = gyro.globGyroscope.getAngle();
        FLPrev = drive.FLEncoder.get();
        FRPrev = drive.FREncoder.get();
        BLPrev = drive.BLEncoder.get();
        BRPrev = drive.BREncoder.get();
    }

    //motor force dir = what force is exerted on the groud when rotating forward
    // FL dir would be pointing back in/out
    V2d getMotorForce(double angle, Encoder e, int prevTicks, V2d motorForceDir, V2d motorPos,
        double angularVelocity, V2d linearVelocity, double dt) {

        double encoderVelocity = (wrapEncoderVal(e.get() - prevTicks) / (float)Constants.ENCODER_TICKS_PER_REV) *
            dt * (2*Math.PI);

        // assuming that encoder values go up with forward motion, could be wrong, and could flip w/ the side of the bot
        V2d rollerVelocity = new V2d(0, 1).rotateDegrees(angle).multiply(-encoderVelocity * Constants.WHEEL_RADIUS);
        V2d motorVelocity = ((V2d.cross(motorPos, angularVelocity)).add(linearVelocity));
        V2d groundSpeed = motorVelocity.add(rollerVelocity);

        V2d forceNormal = motorForceDir.rotateDegrees(angle);
        double velocityAlongNormal = groundSpeed.dot(forceNormal);

        SmartDashboard.putNumber("FL.x", groundSpeed.x);
        SmartDashboard.putNumber("Encoder", encoderVelocity);
        SmartDashboard.putNumber("FL.y", groundSpeed.y);

        double mu = (velocityAlongNormal < Constants.STATIC_FRICTION)? Constants.STATIC_FRICTION : Constants.DYNAMIC_FRICTION;

        return new V2d().sub(forceNormal.multiply(velocityAlongNormal).multiply(mu));
    }










    public int wrapEncoderVal(int x) {
        int r = x;
        while(r < -(Constants.ENCODER_TICKS_PER_REV/2)) { r += Constants.ENCODER_TICKS_PER_REV; }
        while(r > (Constants.ENCODER_TICKS_PER_REV/2)) { r -= Constants.ENCODER_TICKS_PER_REV; }
        return r;
    }




    public void sendTelemetry() {
        SmartDashboard.putNumber("Position Estimate X", this.prevPosition.x);
        SmartDashboard.putNumber("Position Estimate Y", this.prevPosition.y);
        SmartDashboard.putNumber("Velocity Estimate X", this.prevVelocity.x);
        SmartDashboard.putNumber("Velocity Estimate Y", this.prevVelocity.y);
    }


}
