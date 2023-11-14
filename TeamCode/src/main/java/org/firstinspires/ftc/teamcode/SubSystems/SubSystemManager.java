package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.SubSystems.intake.Intake;
import org.firstinspires.ftc.teamcode.SubSystems.intake.IntakeState;

public class SubSystemManager {

    private static RobotState state = RobotState.TRAVEL;
    private static RobotState lastState = RobotState.TRAVEL;

    private static IntakeState intakeState = IntakeState.STOP;
    public static RobotState getRobotState(Gamepad gamepad1){

        return gamepad1.b ? RobotState.TRAVEL : gamepad1.a ? RobotState.INTAKE : gamepad1.x ? RobotState.LOW :gamepad1.y ? RobotState.MID : gamepad1.right_bumper ? RobotState.HIGH : lastState;
    }
    public static void operate (Gamepad gamepad1){
        state = getRobotState(gamepad1);

        switch (state){
            case TRAVEL:
                intakeState = IntakeState.STOP;
            break;
            case INTAKE:
                intakeState = IntakeState.COLLECT;
                break;
            case LOW:
             intakeState = gamepad1.left_bumper ? IntakeState.DEPLETE : IntakeState.STOP;
             break;
            case MID:
                intakeState = gamepad1.left_bumper ? IntakeState.DEPLETE : IntakeState.STOP;
                break;
            case HIGH:
                intakeState = gamepad1.left_bumper ? IntakeState.DEPLETE : IntakeState.STOP;
                break;
        }
        Intake.operate(intakeState);
        lastState = state;
    }
}
