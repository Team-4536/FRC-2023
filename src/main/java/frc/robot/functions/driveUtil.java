package frc.robot.functions;

import frc.robot.subsystems.DriveData;

public final class driveUtil {




    public static void setPowerTank(DriveData drive, double vertical, double turning) {
        setPowerMechanum(drive, 0, vertical, turning);
    }




    public static void setPowerMechanum(DriveData drive, double x, double y, double turning) {


        double[] speeds = {
                (x + y - turning),
                (x - y + turning),
                (x - y - turning),
                (x + y + turning),
        };


        double max = Math.abs(speeds[0]);
        for (int i = 1; i < speeds.length; i++) {
            if (max < Math.abs(speeds[i])) {
                max = Math.abs(speeds[i]);
            }
        }


        //scale all sppeds so that the fastest is now going at 1
        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) {
                speeds[i] /= max; } }

        drive.frontLeftDrive.set(speeds[0]);
        drive.frontRightDrive.set(speeds[1]);
        drive.backLeftDrive.set(speeds[2]);
        drive.backRightDrive.set(speeds[3]);

    }




    public static void setPowerUniform(DriveData drive, double power) {
        drive.frontLeftDrive.set(power);
        drive.frontRightDrive.set(power);
        drive.backLeftDrive.set(power);
        drive.backRightDrive.set(power);
    }

    public static void stop(DriveData drive){
        setPowerUniform(drive, 0.0);
    }




}
