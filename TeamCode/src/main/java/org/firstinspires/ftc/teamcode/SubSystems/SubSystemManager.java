package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.SubSystems.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.SubSystems.intake.Intake;
import org.firstinspires.ftc.teamcode.SubSystems.intake.IntakeState;

public class SubSystemManager {

    private static RobotState state = RobotState.TRAVEL;
    private static RobotState lastState = RobotState.TRAVEL;
    private  static ElevatorState elevatorState = ElevatorState.GROUND;
    private static IntakeState intakeState = IntakeState.CLOSE;
    public static RobotState getRobotState(Gamepad gamepad1){

        return gamepad1.b ? RobotState.TRAVEL : gamepad1.a ? RobotState.INTAKE : gamepad1.x ? RobotState.LOW :gamepad1.y ? RobotState.MID : gamepad1.right_bumper ? RobotState.HIGH : gamepad1.dpad_down ? RobotState.CLIMB : lastState;
    }
    public static void operate (Gamepad gamepad1, Telemetry telemetry){
        state = getRobotState(gamepad1);

        switch (state){
            case TRAVEL:
                intakeState = IntakeState.CLOSE;
                elevatorState = ElevatorState.GROUND;
            break;
            case INTAKE:
                intakeState = IntakeState.OPEN;
                elevatorState = ElevatorState.GROUND;
                break;
            case LOW:
             intakeState = gamepad1.left_bumper ? IntakeState.OPEN : IntakeState.CLOSE;
                elevatorState = ElevatorState.LOW;
             break;
            case MID:
                intakeState = gamepad1.left_bumper ? IntakeState.OPEN : IntakeState.CLOSE;
                elevatorState = ElevatorState.MID;
                break;
            case HIGH:
                intakeState = gamepad1.left_bumper ? IntakeState.OPEN : IntakeState.CLOSE;
                elevatorState = ElevatorState.HIGH;
                break;
            case CLIMB:
                intakeState = IntakeState.CLOSE;
                elevatorState = ElevatorState.CLIMB;
        }
        Intake.operate(intakeState, gamepad1, telemetry);
        Elevator.operate(elevatorState,telemetry, gamepad1);
        lastState = state;
        telemetry.addData("claw state", intakeState);
    }
}
