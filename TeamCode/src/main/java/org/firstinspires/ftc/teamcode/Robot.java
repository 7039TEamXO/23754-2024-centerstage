package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.SubSystems.SubSystemManager;
import org.firstinspires.ftc.teamcode.SubSystems.elevator.Elevator;
import org.firstinspires.ftc.teamcode.SubSystems.elevator.ElevatorState;
import org.firstinspires.ftc.teamcode.SubSystems.intake.Intake;
import org.firstinspires.ftc.teamcode.drivetrain.Drivetrain;

@TeleOp(name = "main")
public class Robot extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain.init(hardwareMap);
       // Intake.init(hardwareMap);
        Elevator.init(hardwareMap);

        ElapsedTime robotTime = new ElapsedTime();
        robotTime.reset();




        telemetry.update();
        waitForStart();

        while (!isStopRequested()) {
            Drivetrain.operate(gamepad1);
            telemetry.update();
//            Elevator.operate(ElevatorState.HIGH, gamepad1, telemetry);
            SubSystemManager.operate(gamepad1, telemetry);

        }
    }
}