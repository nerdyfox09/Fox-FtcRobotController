package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TicksAndEncodersPractice extends LinearOpMode {

    public DcMotor motor;


    public void runOpMode() {

        motor = hardwareMap.get(DcMotor.class, "MotorName");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Ready to Start");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            double wheelDiameter = 96.0;
            double circumference = Math.PI * wheelDiameter;
            int ticksPerRevolution = 1440;
            double millimetersPerTick = circumference / ticksPerRevolution;
            double targetDistance = 914.4;
            int numTicks = (int) (targetDistance / millimetersPerTick);
            driveForward(numTicks, 1);
        }
    }
   public void driveForward(int targetTicks, double power){

            motor.setTargetPosition(targetTicks);

            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motor.setPower(power);

            while (motor.isBusy()){
                telemetry.addData("Target Ticks: ", targetTicks);
                telemetry.update();
        }
            motor.setPower(0);

    }
}
