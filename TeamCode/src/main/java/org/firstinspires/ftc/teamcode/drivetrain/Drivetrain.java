package org.firstinspires.ftc.teamcode.drivetrain;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.util.Vector;


public class Drivetrain {

    public static final DcMotor[] motors = new DcMotor[4];

    public static void init(HardwareMap hardwareMap) {
        motors[0] = hardwareMap.get(DcMotor.class, "lf");
        motors[1] = hardwareMap.get(DcMotor.class, "rf");
        motors[2] = hardwareMap.get(DcMotor.class, "lb");
        motors[3] = hardwareMap.get(DcMotor.class, "rb");

        motors[0].setDirection(DcMotorSimple.Direction.REVERSE);
        motors[2].setDirection(DcMotorSimple.Direction.REVERSE);

        // TODO if your initial robot position is not 0,0,0 make sure to fix the
        // position (look for the function in the documentry). might be setPoseEstimate

        for (final DcMotor motor : motors) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

    }

    public static void operate(final Vector velocity_W, float omega) {
        final float robotAngle = (float) Math.toRadians(Gyro.getAngle());
        final Vector velocity_RobotCS_W = velocity_W.rotate(-robotAngle);
        if(velocity_RobotCS_W.norm() <= Math.sqrt(0.005) && Math.abs(omega) == 0) stop();
        else drive(velocity_RobotCS_W, omega);
    }
    // did field centric



    public static void stop() {
        for (DcMotor motor : motors) {
            motor.setPower(0);
        }
    }

    public static void drive(Vector drive, double r) {
        final double lfPower = drive.y + drive.x + r;
        final double rfPower = drive.y - drive.x - r;
        final double lbPower = drive.y - drive.x + r;
        final double rbPower = drive.y + drive.x - r;
        double highestPower = 1;
        final double max = Math.max(Math.abs(lfPower),
                Math.max(Math.abs(lbPower), Math.max(Math.abs(rfPower), Math.abs(rbPower))));
        if (max > 1)
            highestPower = max;
        motors[0].setPower(0.8 * (lfPower / highestPower));
        motors[1].setPower(0.8 * (rfPower / highestPower));
        motors[2].setPower(0.8 * (lbPower / highestPower));
        motors[3].setPower(0.8 * (rbPower / highestPower));
    }

    public static void testMotors(Gamepad gamepad, Telemetry telemetry){
        if (gamepad.dpad_down){motors[0].setPower(0.2);}
        else if (gamepad.dpad_left){motors[1].setPower(0.2);}
        else if (gamepad.dpad_up){motors[2].setPower(0.2);}
        else if (gamepad.dpad_right){motors[3].setPower(0.2);}
        telemetry.addData("lf", motors[0].getCurrentPosition());
        telemetry.addData("rf", motors[1].getCurrentPosition());
        telemetry.addData("lb", motors[2].getCurrentPosition());
        telemetry.addData("rb", motors[3].getCurrentPosition());
    }


}