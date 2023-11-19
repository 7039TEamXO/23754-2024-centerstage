package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.SubSystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.SubSystems.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.SubSystems.intake.Intake;
import org.firstinspires.ftc.teamcode.SubSystems.intake.IntakeState;

public class SubSystemManager {

    private static RobotState state = RobotState.TRAVEL;
    private static RobotState lastState = RobotState.TRAVEL;
    private  static ElevatorState elevatorState = ElevatorState.GROUND;
    private static IntakeState intakeState = IntakeState.STOP;
    public static RobotState getRobotState(Gamepad gamepad1){

        return gamepad1.b ? RobotState.TRAVEL : gamepad1.a ? RobotState.INTAKE : gamepad1.x ? RobotState.LOW :gamepad1.y ? RobotState.MID : gamepad1.right_bumper ? RobotState.HIGH : lastState;
    }
    public static void operate (Gamepad gamepad1){
        state = getRobotState(gamepad1);

        switch (state){
            case TRAVEL:
                intakeState = IntakeState.STOP;
                elevatorState = ElevatorState.GROUND;
            break;
            case INTAKE:
                intakeState = IntakeState.COLLECT;
                elevatorState = ElevatorState.GROUND;
                break;
            case LOW:
             intakeState = gamepad1.left_bumper ? IntakeState.DEPLETE : IntakeState.STOP;
                elevatorState = ElevatorState.LOW;
             break;
            case MID:
                intakeState = gamepad1.left_bumper ? IntakeState.DEPLETE : IntakeState.STOP;
                elevatorState = ElevatorState.MID;
                break;
            case HIGH:
                intakeState = gamepad1.left_bumper ? IntakeState.DEPLETE : IntakeState.STOP;
                elevatorState = ElevatorState.HIGH;
                break;
        }
        Intake.operate(intakeState);
        Elevator.operate(elevatorState);
        lastState = state;
    }
}
