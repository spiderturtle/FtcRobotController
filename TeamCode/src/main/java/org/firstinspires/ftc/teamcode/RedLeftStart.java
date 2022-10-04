/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="RedLeftStartAuto", group="Linear Opmode")
public class RedLeftStart extends LinearOpMode {
    ArmMechanics armMechanics;
    Claw clawMechanics;
    DuckSpinner duckSpinner;
    DriveFunctions driveFunctions;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        armMechanics = new ArmMechanics(hardwareMap, telemetry);
        clawMechanics = new Claw(hardwareMap, telemetry, false);
        duckSpinner = new DuckSpinner(hardwareMap);
        driveFunctions = new DriveFunctions(hardwareMap);
        driveFunctions.SetDriveSpeed(.5);
        double strafePower = .5;
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        if (opModeIsActive()) {
            //strafe Right
            driveFunctions.StrafeRight(strafePower);
            sleep(730);
            driveFunctions.StopWheels();
            // raise arm
            clawMechanics.MoveClaw(false);
            armMechanics.ArmDrive(.207,true);
            sleep(4000);
            driveFunctions.MoveForward();
            sleep(500);
            driveFunctions.StopWheels();
            sleep(500);
            clawMechanics.MoveClaw(true);
            sleep(500);
            armMechanics.ArmDrive(0,false);
            sleep(2000);
            //move backwards
            driveFunctions.MoveBackward();
            sleep(200);
            driveFunctions.StopWheels();
            armMechanics.ArmDrive(0,false);
            sleep(1000);
            //strafe right to carousel
            driveFunctions.Spin(true);
            sleep(625);
            driveFunctions.MoveBackward();
            sleep(1780);
            driveFunctions.StopWheels();
            driveFunctions.StrafeRight(strafePower);
            sleep(400);
            driveFunctions.StopWheels();
            driveFunctions.Spin(true);
            sleep(150);
            driveFunctions.StopWheels();
            driveFunctions.StrafeRight(strafePower);
            sleep(100);
            driveFunctions.StopWheels();


            //spin duckspinner
            duckSpinner.SpinClockwise(.2);
            sleep(6490);
            driveFunctions.StrafeLeft(strafePower);
            sleep(100);
            driveFunctions.StopWheels();
            driveFunctions.Spin(false);
            sleep(150);
            driveFunctions.StopWheels();

            driveFunctions.StrafeLeft(strafePower);
            sleep(875);
            driveFunctions.StopWheels();
            driveFunctions.MoveBackward();
            sleep(275);
            driveFunctions.StopWheels();

        }
    }
}