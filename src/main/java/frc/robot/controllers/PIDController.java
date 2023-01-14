package frc.robot.controllers;

import frc.robot.functions.gyroUtil;

public class PIDController {

    public double Kp;
    public double Ki;
    public double Kd;

    public double integral;
    public double prevErr;

    public double target;



    public PIDController() { };
    public PIDController(double p, double i, double d) {
        this.Kp = p;
        this.Ki = i;
        this.Kd = d; };


    //all PID spec. code yoinked from ctrl alt ftc thanks lol
    // wrap should be true for things that go in a circle, drive especially
    public double tick(double position, double dt, boolean wrap) {

        double error = this.target - position;
        error = wrap? gyroUtil.wrapAngle(error) : error;

        double derivative = (error - this.prevErr) * dt;
        this.integral += error * dt;

        double out = (this.Kp * error) + (this.Ki * this.integral) + (this.Kd * derivative);
        this.prevErr = error;

        return out;
    }
}