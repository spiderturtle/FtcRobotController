
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftRearMotor = hardwareMap.get(DcMotor.class, "LeftRearMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "RightFrontMotor");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "LeftFrontMotor");
        rightRearMotor = hardwareMap.get(DcMotor.class, "RightRearMotor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);


        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
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

        if (rightBumperPressed) {
            SetPower (-1,1,-1,1);
        }
        else if (leftBumperPressed) {
            SetPower (1,-1,1,-1);
        }
        else {
            PovDrive();
        }






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
    telemetry.addData("Status", "Run Time: " + runtime.toString());
    telemetry.addData("Motors", "leftFront (%.2f), rightFront (%.2f), leftRear (%.2f), rightRear (%.2f)", leftFrontPower, rightFrontPower,leftRearPower,rightRearPower);
}

private void SetPower(double leftRearPower,double rightFrontPower, double leftFrontPower, double rightRearPower){
        leftRearMotor.setPower(leftRearPower);
        rightFrontMotor.setPower(rightFrontPower);
        leftFrontMotor.setPower(leftFrontPower);
        rightRearMotor.setPower(rightRearPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
