package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Claw {
    private Servo clawServo  = null ;
    public final static double CLAW_SERVO_MIN_RANGE = -1;
    public final static double CLAW_SERVO_MAX_RANGE = 1;

    public final static double CLAW_SERVO_HOME = .4;
    private double clawPosition = CLAW_SERVO_HOME;

    final double clawServoSpeed = 0.1;
    public Claw(HardwareMap hwMap){
        clawServo = hwMap.servo.get("ClawServo");
        clawServo.setPosition(CLAW_SERVO_HOME);
    }

    public void MoveClaw(boolean increment){
        if(increment){
            clawPosition += clawServoSpeed;
        }else {
            clawPosition -= clawServoSpeed;
        }
        clawPosition = Range.clip(clawPosition, CLAW_SERVO_MIN_RANGE, CLAW_SERVO_MAX_RANGE);
        clawServo.setPosition(clawPosition);
    }
}
