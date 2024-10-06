package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous
public class TicksAndEncodersPractice extends LinearOpMode {

    public DcMotor motorBL;
    public DcMotor motorBR;
    public DcMotor motorFL;
    public DcMotor motorFR;



    @Override
    public void runOpMode() {

        motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");
        motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");


        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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
            driveForward(motorBL, numTicks, -1);
            driveForward(motorBR, numTicks, 1);
            driveForward(motorFL, numTicks, -1);
            driveForward(motorFR, numTicks, 1);
        }
    }
   public void driveForward(DcMotor motor, int targetTicks, double power){

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
