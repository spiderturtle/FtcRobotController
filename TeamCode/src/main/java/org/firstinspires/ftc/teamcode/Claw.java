package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
    private Servo clawServo  = null ;
    public boolean isClawClosed = false;
    public final static double CLAW_SERVO_OPEN = .5;
    public final static double CLAW_SERVO_CLOSED = 0.19;
    private Telemetry log = null;
    public Claw(HardwareMap hwMap, Telemetry telemetry, boolean open){
        log = telemetry;
        clawServo = hwMap.servo.get("ClawServo");
        if(open)
            clawServo.setPosition(CLAW_SERVO_OPEN);
        else
            clawServo.setPosition(CLAW_SERVO_CLOSED);
    }

    public void MoveClaw(boolean open){
        if(open){
            clawServo.setPosition(CLAW_SERVO_OPEN);
            log.addData("CLAW", "Setting Claw Open");
            isClawClosed = false;
        }else {
            clawServo.setPosition(CLAW_SERVO_CLOSED);
            log.addData("CLAW", "Setting Claw Closed");
            isClawClosed = true;
        }
        log.update();

    }
}
