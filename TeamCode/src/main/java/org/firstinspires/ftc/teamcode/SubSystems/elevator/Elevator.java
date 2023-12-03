package org.firstinspires.ftc.teamcode.SubSystems.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.PID;

public class Elevator {
    private static DcMotor elevatorMotorLeft;
    private static DcMotor elevatorMotorRight;
    private static double wantedPos = 0;

    private  static PID pid = new PID(0.01,0
            ,0,0,0);
    public static void init(HardwareMap hardwareMap){
        elevatorMotorLeft = hardwareMap.dcMotor.get("elevatorLeft");
        elevatorMotorRight = hardwareMap.dcMotor.get("elevatorRight");
        elevatorMotorRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public static void operate (ElevatorState state, Telemetry telemetry){
        switch (state){
            case GROUND: wantedPos = 0;
            break;
            case LOW: wantedPos = 600 ;
            break;
            case MID: wantedPos = 1300 ;
                break;
            case HIGH: wantedPos = 2000;
                break;
        }
        pid.setWanted(wantedPos);
        elevatorMotorLeft.setPower(pid.update(elevatorMotorLeft.getCurrentPosition()));
        elevatorMotorRight.setPower(pid.update(elevatorMotorRight.getCurrentPosition()));
        telemetry.addData("left pos: ", elevatorMotorLeft.getCurrentPosition());
        telemetry.addData("left w: ", pid.update(elevatorMotorLeft.getCurrentPosition()));
        telemetry.addData("right pos: ", elevatorMotorRight.getCurrentPosition());

    }
}
