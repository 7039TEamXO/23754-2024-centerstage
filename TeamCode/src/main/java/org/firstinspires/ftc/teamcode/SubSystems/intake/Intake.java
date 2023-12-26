package org.firstinspires.ftc.teamcode.SubSystems.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private static CRServo servoRight;
    private static CRServo servoLeft;

    private static double wantedPosition = 0;

    public static void init(HardwareMap hardwareMap) {
        servoRight = hardwareMap.crservo.get("servoRight");
        servoLeft = hardwareMap.crservo.get("servoLeft");
    }

    public static void operate(IntakeState state, Gamepad gamepad, Telemetry telemetry) {

        switch (state) {
            case DEPLETE:
                wantedPosition =-1;
                break;
            case COLLECT:
                wantedPosition = 1;
                break;
            case STOP:
                wantedPosition = 0;
                break;
        }
//      wantedPosition = -gamepad.right_stick_y;
        servoLeft.setPower(wantedPosition);
        servoRight.setPower(wantedPosition);

      telemetry.addData("joystick: ", -gamepad.right_stick_y);


    }
}
