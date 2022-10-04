package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DuckSpinner {
    public DcMotor duckSpinner = null;
    boolean duckSpinnerSpinning = false;


    public DuckSpinner(HardwareMap hardwareMap) {
        duckSpinner = hardwareMap.dcMotor.get("DuckSpinner");
    }

    public void Stop() {
        duckSpinnerSpinning = false;
        duckSpinner.setPower(0);
    }

    public void SpinClockwise() {
        duckSpinnerSpinning = true;
        duckSpinner.setPower(.3);
    }

    public void SpinClockwise(double speed) {
        duckSpinnerSpinning = true;
        duckSpinner.setPower(speed);
    }

    public void SpinCounterClockwise() {
        duckSpinnerSpinning = true;
        duckSpinner.setPower(-.3);
    }

    public void SpinCounterClockwise(double speed) {
        duckSpinnerSpinning = true;
        duckSpinner.setPower(-speed);
    }

}
