package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drivetrain.Drivetrain;
import org.firstinspires.ftc.teamcode.drivetrain.Gyro;
import org.firstinspires.ftc.teamcode.util.Vector;

@TeleOp(name = "main")
public class Robot extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain.init(hardwareMap);
        Gyro.init(hardwareMap);

        ElapsedTime robotTime = new ElapsedTime();
        robotTime.reset();




        telemetry.update();



        waitForStart();

        while (!isStopRequested()) {
          Vector leftStick = new Vector(gamepad1.left_stick_x, -gamepad1.left_stick_y);
          float omega = gamepad1.right_trigger - gamepad1.left_trigger;
          Drivetrain.operate(leftStick,  omega);



            telemetry.update();
        }
    }



}