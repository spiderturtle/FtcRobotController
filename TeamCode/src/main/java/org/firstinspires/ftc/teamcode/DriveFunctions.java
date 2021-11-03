package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class DriveFunctions
{
    private DcMotor leftRearMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftFrontMotor = null;
    private DcMotor rightRearMotor = null;

    public DriveFunctions(HardwareMap hwMap){
        leftRearMotor = hwMap.get(DcMotor.class, "LeftRearMotor");
        rightFrontMotor = hwMap.get(DcMotor.class, "RightFrontMotor");
        leftFrontMotor = hwMap.get(DcMotor.class, "LeftFrontMotor");
        rightRearMotor = hwMap.get(DcMotor.class, "RightRearMotor");

        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void StrafeLeft(double speed){
        SetPower (speed,speed,-(speed),-(speed));
    }

    public void StrafeRight(double speed){
        SetPower (-(speed),-(speed),speed,speed);
    }



    public void Drive(double drivePower, double turn, boolean halfPower){

    double leftFrontPower;
    double rightFrontPower;
    double leftRearPower;
    double rightRearPower;
    double leftFrontHalfPower=0.0;
    double rightFrontHalfPower=0.0;
    double leftRearHalfPower=0.0;
    double rightRearHalfPower=0.0;
    // POV Mode uses left stick to go forward, and right stick to turn.
    // - This uses basic math to combine motions and is easier to drive straight.
    leftFrontPower = Range.clip(drivePower + turn, -.5, .5) ;
    rightFrontPower = Range.clip(drivePower - turn, -.5, .5) ;
    rightRearPower = Range.clip(drivePower - turn, -.5, .5) ;
    leftRearPower = Range.clip(drivePower + turn, -.5, .5) ;
    if (leftFrontPower!=0)
        leftFrontHalfPower= leftFrontPower /2;
    if (rightFrontPower!=0)
        rightFrontHalfPower= rightFrontPower /2;
    if (rightRearPower!=0)
        rightRearHalfPower= rightRearPower /2;
    if (leftRearPower!=0)
        leftRearHalfPower= leftRearPower /2;


    if(halfPower){
        SetPower(leftRearHalfPower,rightFrontHalfPower,leftFrontHalfPower,rightRearHalfPower);
    }
    else {
        SetPower(leftRearPower,rightFrontPower,leftFrontPower,rightRearPower);

    }
 }
    private void SetPower(double leftRearPower,double rightFrontPower, double leftFrontPower, double rightRearPower){
        leftRearMotor.setPower(leftRearPower);
        rightFrontMotor.setPower(rightFrontPower);
        leftFrontMotor.setPower(leftFrontPower);
        rightRearMotor.setPower(rightRearPower);
    }
    public void MoveForward(){
        SetPower(.5,.5,.5,.5);
    }
    public void MoveBackward(){
        SetPower(-.5,-.5,-.5,-.5);
    }
    public void StopWheels(){
        SetPower(0,0,0,0);
    }
}


