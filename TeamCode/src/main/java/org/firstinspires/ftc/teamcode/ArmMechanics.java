package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmMechanics {
    private static final double gravityAdjustment  = .15;
    private DcMotor leftArm = null;
    private DcMotor rightArm = null;
    private Telemetry log = null;

    public ArmMechanics(HardwareMap hwMap, Telemetry telemetry){
        log = telemetry;
        leftArm = hwMap.get(DcMotor.class, "LeftArm");
        rightArm = hwMap.get(DcMotor.class, "RightArm");

        leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftArm.setDirection(DcMotor.Direction.FORWARD);
        rightArm.setDirection(DcMotor.Direction.REVERSE);
        SetArmPower(gravityAdjustment);
    }

    public void ArmDrive(double drivePower, boolean useGravityAdjustment){
        double power = Range.clip(drivePower, -.1, .4);
        // Send calculated power to both arm motors
        SetArmPower(power == 0 && useGravityAdjustment ? gravityAdjustment : power); //keep the arm from falling if set to 0
    }
    private void SetArmPower(double power){
        leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftArm.setPower(power);
        rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightArm.setPower(power);
    }

    //public void MoveArmMotors(){
        //leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //double quarterTurn = 1;
        //int currentPosition = 0;
        //int newTarget = currentPosition + (int)quarterTurn;
        //log.addData("Current position", currentPosition);
        //log.update();
        //leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //leftArm.setTargetPosition(newTarget);
        //rightArm.setTargetPosition(newTarget);
        //leftArm.setPower(.25);
        //rightArm.setPower(.25);
        //while(leftArm.isBusy()){

        //}
        //
        //leftArm.setPower(0);
        //leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //}


}
