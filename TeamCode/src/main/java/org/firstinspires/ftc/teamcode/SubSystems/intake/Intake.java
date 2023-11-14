package org.firstinspires.ftc.teamcode.SubSystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private static DcMotor intakeMotor;
    private static double wantedPower = 0;
  public static void init(HardwareMap hardwareMap){
      intakeMotor = hardwareMap.dcMotor.get("intake");
  }
  public static void operate (IntakeState state){

      switch (state){
          case STOP: wantedPower = 0;
          break;
          case COLLECT: wantedPower = 0.5;
          break;
          case DEPLETE: wantedPower = -0.3;
      }
      intakeMotor.setPower(wantedPower);
  }
}
