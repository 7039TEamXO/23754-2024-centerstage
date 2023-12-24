package org.firstinspires.ftc.teamcode.SubSystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private static Servo servoRight;
    private static Servo servoLeft;

    private static double wantedPosition = 0;

    public static void init(HardwareMap hardwareMap) {
        servoRight = hardwareMap.servo.get("servoRight");
        servoLeft = hardwareMap.servo.get("servoLeft");
    }

    public static void operate(IntakeState state, Gamepad gamepad, Telemetry telemetry) {

        switch (state) {
            case DEPLETE:
                wantedPosition =0;
                break;
            case COLLECT:
                wantedPosition = 1;
                break;
            case STOP:
                wantedPosition = 0.5;
                break;
        }
//      wantedPosition = -gamepad.right_stick_y;
        servoLeft.setPosition(wantedPosition);
        servoRight.setPosition(-wantedPosition);

      telemetry.addData("joystick: ", -gamepad.right_stick_y);


    }
}
