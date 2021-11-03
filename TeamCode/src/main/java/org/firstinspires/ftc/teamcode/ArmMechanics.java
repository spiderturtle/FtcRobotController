package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class ArmMechanics {
    private static final double gravityAdjustment  = .19;
    private DcMotor leftArm = null;
    private DcMotor rightArm = null;

    public ArmMechanics(HardwareMap hwMap){
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

    public void ArmDrive(double drivePower){
        double power = Range.clip(drivePower, -.5, .5);
        // Send calculated power to both arm motors
        SetArmPower(power == 0 ? gravityAdjustment : power); //keep the arm from falling if set to 0
    }
    private void SetArmPower(double power){
        leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftArm.setPower(power);
        rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightArm.setPower(power);
    }


//TODO: set arm to positions
    //private void MoveArm(DcMotor motor){
        //double quarterTurn = 50;
        //int currentPosition = motor.getTargetPosition();
        //int newTarget = currentPosition + (int)quarterTurn;
        //telemetry.log().add("Current position, Target Position", "currentPosition %.1f, newTarget %.1f",currentPosition ,newTarget);
        //telemetry.update();
        //motor.setTargetPosition(newTarget);
        //motor.setPower(.75);
        //motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //while(motor.isBusy()){
        //telemetry.addData("Status", "Running the motor to a quarter turn.");
        //telemetry.update();
        //}
        //motor.setPower(0);
        //motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //}
}
