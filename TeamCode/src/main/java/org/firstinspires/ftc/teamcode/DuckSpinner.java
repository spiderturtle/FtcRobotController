package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DuckSpinner {
    public Servo duckSpinner = null;
    boolean duckSpinnerSpinning = false;


    public DuckSpinner(HardwareMap hardwareMap) {
        duckSpinner = hardwareMap.servo.get("DuckSpinner");
    }

    public void Stop() {
        duckSpinnerSpinning = false;
        duckSpinner.setPosition(.5);
    }

    public void SpinClockwise() {
        duckSpinnerSpinning = true;
        duckSpinner.setPosition(1);
    }

    public void SpinCounterClockwise() {
        duckSpinnerSpinning = true;
        duckSpinner.setPosition(0);
    }

}
