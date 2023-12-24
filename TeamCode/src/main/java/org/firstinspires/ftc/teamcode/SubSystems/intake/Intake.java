package org.firstinspires.ftc.teamcode.SubSystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    private static Servo claw;

    private static double wantedPosition = 0;

    public static void init(HardwareMap hardwareMap) {
        claw = hardwareMap.servo.get("claw");
    }

    public static void operate(IntakeState state, Gamepad gamepad, Telemetry telemetry) {

        switch (state) {
            case OPEN:
                wantedPosition =0;
                break;
            case CLOSE:
                wantedPosition = 1;
                break;
        }
//      wantedPosition = -gamepad.right_stick_y;
        claw.setPosition(wantedPosition);
      telemetry.addData("joystick: ", -gamepad.right_stick_y);


    }
}
