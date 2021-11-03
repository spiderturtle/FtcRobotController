package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Driver Controlled", group="Iterative Opmode")
public class PovDriveTeleOp extends OpMode {
    ArmMechanics armMechanics;
    Claw clawMechanics;
    DuckSpinner duckSpinner;
    DriveFunctions driveFunctions;

    @Override
    public void init() {
        armMechanics = new ArmMechanics(hardwareMap);
        clawMechanics = new Claw(hardwareMap);
        duckSpinner = new DuckSpinner(hardwareMap);
        driveFunctions = new DriveFunctions(hardwareMap);
    }

    @Override
    public void loop() {
        boolean rightBumperPressed = gamepad1.right_bumper;
        boolean leftBumperPressed = gamepad1.left_bumper;
        boolean rightTriggerPressed = gamepad1.right_trigger > 0;
        boolean leftTriggerPressed = gamepad1.left_trigger > 0;
        boolean g2rightTriggerPressed = gamepad2.right_trigger > 0;
        boolean g2leftTriggerPressed  = gamepad2.left_trigger > 0;

        if (rightBumperPressed) {
            driveFunctions.StrafeRight (1);
        }
        else if (leftBumperPressed) {
            driveFunctions.StrafeLeft (1);
        }
        else if (rightTriggerPressed) {
            driveFunctions.StrafeRight (.5);
        }
        else if (leftTriggerPressed) {
            driveFunctions.StrafeLeft (.5);
        }
        else if(gamepad2.y){
            duckSpinner.SpinCounterClockwise();
        }
        else if(gamepad2.x)
        {
            duckSpinner.SpinClockwise();
        }
        else if(gamepad2.a){
            duckSpinner.Stop();
        }
        else if(g2rightTriggerPressed){

            clawMechanics.MoveClaw(false);
        }
        else if(g2leftTriggerPressed){

            clawMechanics.MoveClaw(true);
        }
        else {
            armMechanics.ArmDrive(-gamepad2.left_stick_y);
            driveFunctions.Drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.a);
        }
    }
}
