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
    private double driveSpeed;

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

    public void SetDriveSpeed(double power){
        driveSpeed = power;
    }

    public void Drive(double drivePower, double turn){

    double leftFrontPower;
    double rightFrontPower;
    double leftRearPower;
    double rightRearPower;

    // POV Mode uses left stick to go forward, and right stick to turn.
    // - This uses basic math to combine motions and is easier to drive straight.
    leftFrontPower = Range.clip(drivePower + turn, -driveSpeed, driveSpeed) ;
    rightFrontPower = Range.clip(drivePower - turn, -driveSpeed, driveSpeed) ;
    rightRearPower = Range.clip(drivePower - turn, -driveSpeed, driveSpeed) ;
    leftRearPower = Range.clip(drivePower + turn, -driveSpeed, driveSpeed) ;
    SetPower(leftRearPower,rightFrontPower,leftFrontPower,rightRearPower);

 }

    public void Spin(boolean right){
        if(right)
            Drive(0,.5);
        else
            Drive(0,-.5);

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


