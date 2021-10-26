
package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.Telemetry.Log.*;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.Telemetry.Log;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Nav", group="Iterative Opmode")
public class Nav extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftRearMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftFrontMotor = null;
    private DcMotor rightRearMotor = null;
    private Servo   clawServo  = null ;
    
    private DcMotor leftArm = null;
    private DcMotor rightArm = null;

    public Servo duckSpinner = null;
    boolean duckSpinnerSpinning = false;

    public final static double CLAW_SERVO_MIN_RANGE = 0.1;
    public final static double CLAW_SERVO_MAX_RANGE = 0.4;

    public final static double CLAW_SERVO_HOME = 0.4;
    private double clawPosition = CLAW_SERVO_HOME;

    final double clawServoSpeed = 0.1;
    //private int armStartPosition = 0;
    static final double MOTOR_TICK_COUNT =1120; //2786;


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        //telemetry.setAutoClear(false);

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftRearMotor = hardwareMap.get(DcMotor.class, "LeftRearMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "RightFrontMotor");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "LeftFrontMotor");
        rightRearMotor = hardwareMap.get(DcMotor.class, "RightRearMotor");

        leftArm = hardwareMap.get(DcMotor.class, "LeftArm");
        rightArm = hardwareMap.get(DcMotor.class, "RightArm");

        leftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //leftArm.setTargetPosition(armStartPosition);
        //rightArm.setTargetPosition(armStartPosition);

        duckSpinner = hardwareMap.servo.get("DuckSpinner");
        clawServo = hardwareMap.servo.get("ClawServo");
        clawServo.setPosition(CLAW_SERVO_HOME);
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.FORWARD);
        leftArm.setDirection(DcMotor.Direction.FORWARD);
        rightArm.setDirection(DcMotor.Direction.REVERSE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry

        boolean rightBumperPressed = gamepad1.right_bumper;
        boolean leftBumperPressed = gamepad1.left_bumper;
        boolean rightTriggerPressed = gamepad1.right_trigger > 0;
        boolean leftTriggerPressed = gamepad1.left_trigger > 0;
        boolean g2rightTriggerPressed = gamepad2.right_trigger > 0;
        boolean g2leftTriggerPressed  = gamepad2.left_trigger > 0;


        if (rightBumperPressed) {
            SetPower (-1,-1,1,1);
        }
        else if (leftBumperPressed) {
            SetPower (1,1,-1,-1);
        }
        else if (rightTriggerPressed) {
            SetPower (-.5,-.5,.5,.5);
        }
        else if (leftTriggerPressed) {
            SetPower (.5,.5,-.5,-.5);
        }
        else if(gamepad2.y){
            //MoveArm(leftArm);
            MoveArm(rightArm);
        }
        else if(gamepad2.x)
        {
            ToggleDuckSpinner();
        }
         else if(g2rightTriggerPressed){
             clawPosition -= clawServoSpeed;
             MoveClaw();
         }
         else if(g2leftTriggerPressed){
             clawPosition += clawServoSpeed;
             MoveClaw();
        }
         else {
             ArmDrive();
             PovDrive();
         }
    }

    private void MoveClaw(){
        clawPosition = Range.clip(clawPosition, CLAW_SERVO_MIN_RANGE, CLAW_SERVO_MAX_RANGE);
        clawServo.setPosition(clawPosition);
    }

    private void ToggleDuckSpinner(){
        if(gamepad2.x)
        {
            if(duckSpinnerSpinning)
            {
                //stop
                duckSpinnerSpinning = false;
                duckSpinner.setPosition(.5);
            }
            else
            {
                //spin
                duckSpinnerSpinning = true;
                duckSpinner.setPosition(1);
            }
        }
    }

    private void MoveArm(DcMotor motor){
        double quarterTurn = 50;
        int currentPosition = motor.getTargetPosition();
        int newTarget = currentPosition + (int)quarterTurn;
        telemetry.log().add("Current position, Target Position", "currentPosition %.1f, newTarget %.1f",currentPosition ,newTarget);
        telemetry.update();
        motor.setTargetPosition(newTarget);
        motor.setPower(1);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //while(motor.isBusy()){
            //telemetry.addData("Status", "Running the motor to a quarter turn.");
            //telemetry.update();
        //}
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
private void PovDrive(){

    double leftFrontPower;
    double rightFrontPower;
    double leftRearPower;
    double rightRearPower;
    // POV Mode uses left stick to go forward, and right stick to turn.
    // - This uses basic math to combine motions and is easier to drive straight.
    double drive = -gamepad1.left_stick_y;
    double turn  =  gamepad1.right_stick_x;
    leftFrontPower = Range.clip(drive + turn, -1.0, 1.0) ;
    rightFrontPower = Range.clip(drive - turn, -1.0, 1.0) ;
    rightRearPower = Range.clip(drive - turn, -1.0, 1.0) ;
    leftRearPower = Range.clip(drive + turn, -1.0, 1.0) ;

    // Send calculated power to wheels
    SetPower(leftRearPower,rightFrontPower,leftFrontPower,rightRearPower);

    // Show the elapsed game time and wheel power.
    //telemetry.addData("Status", "Run Time: " + runtime.toString());
    //telemetry.addData("Motors", "leftFront (%.2f), rightFront (%.2f), leftRear (%.2f), rightRear (%.2f)", leftFrontPower, rightFrontPower,leftRearPower,rightRearPower);
}

    private void ArmDrive(){

        double rightArmPower;
        double leftArmPower;

        double drive = -gamepad2.left_stick_y;
        double power = Range.clip(drive, -.5, .5);


        // Send calculated power to wheels
        SetArmPower(power);

        // Show the elapsed game time and wheel power.
        //telemetry.addData("Status", "Run Time: " + runtime.toString());
        //telemetry.addData("Motors", "leftFront (%.2f), rightFront (%.2f), leftRear (%.2f), rightRear (%.2f)", leftFrontPower, rightFrontPower,leftRearPower,rightRearPower);
    }

    private void SetPower(double leftRearPower,double rightFrontPower, double leftFrontPower, double rightRearPower){
        leftRearMotor.setPower(leftRearPower);
        rightFrontMotor.setPower(rightFrontPower);
        leftFrontMotor.setPower(leftFrontPower);
        rightRearMotor.setPower(rightRearPower);
    }


    private void SetArmPower(double power){
        leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftArm.setPower(power);
        rightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightArm.setPower(power);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        leftArm.setPower(0);
        rightArm.setPower(0);
    }

}
