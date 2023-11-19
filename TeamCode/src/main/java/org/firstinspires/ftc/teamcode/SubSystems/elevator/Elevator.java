package org.firstinspires.ftc.teamcode.SubSystems.elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.PID;

public class Elevator {
    private static DcMotor elevatorMotorLeft;
    private static DcMotor elevatorMotorRight;
    private static double wantedPos = 0;

    private  static PID pid = new PID(0,0,0,0,0);
    public static void init(HardwareMap hardwareMap){
        elevatorMotorLeft = hardwareMap.dcMotor.get("elevatorLeft");
        elevatorMotorRight = hardwareMap.dcMotor.get("elevatorRight");
    }
    public static void operate (ElevatorState state){
        switch (state){
            case GROUND: wantedPos = 0;
            break;
            case LOW: wantedPos = 0 ;
            break;
            case MID: wantedPos = 0 ;
                break;
            case HIGH: wantedPos = 0 ;
                break;
        }
        pid.setWanted(wantedPos);
        elevatorMotorLeft.setPower(pid.update(elevatorMotorLeft.getCurrentPosition()));
        elevatorMotorRight.setPower(pid.update(elevatorMotorRight.getCurrentPosition()));
    }
}
