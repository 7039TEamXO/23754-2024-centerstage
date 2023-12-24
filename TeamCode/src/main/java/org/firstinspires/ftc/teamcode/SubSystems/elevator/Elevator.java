package org.firstinspires.ftc.teamcode.SubSystems.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.PID;

public class Elevator {
    private static DcMotor elevatorMotorLeft;
    private static Servo arm;
    private static DcMotor elevatorMotorRight;
    private static double wantedPos = 0;
    private static double wantedArmPos = 0;

    private static PID pid = new PID(0.01, 0
            , 0, 0, 0);

    public static void init(HardwareMap hardwareMap) {
        elevatorMotorLeft = hardwareMap.dcMotor.get("elevatorLeft");
        elevatorMotorRight = hardwareMap.dcMotor.get("elevatorRight");
        arm = hardwareMap.get(Servo.class, "arm");
        elevatorMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public static void operate(ElevatorState state, Telemetry telemetry, Gamepad gamepad) {
        switch (state) {
            case GROUND:
                wantedPos = 35;
                wantedArmPos = 0.21;
                break;
            case LOW:
                wantedPos = 1100;
                wantedArmPos = 0.4;
                break;
            case MID:
                wantedPos = 2200;
                wantedArmPos = 0.4;

                break;
            case HIGH:
                wantedPos = 2900;
                wantedArmPos = 0.4;
                break;
            case CLIMB:
                wantedPos = 2500;
                wantedArmPos = 0.15;
                    break;
        }

        pid.setWanted(wantedPos);
        elevatorMotorLeft.setPower(pid.update(elevatorMotorLeft.getCurrentPosition()));
        elevatorMotorRight.setPower(pid.update(elevatorMotorRight.getCurrentPosition()));
        telemetry.addData("w: ", wantedArmPos);
        arm.setPosition(wantedArmPos);


    }
}
