package org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.BLACKBOX.HardwareRobot;

public class Collector {

    HardwareRobot robot;

    public Collector(HardwareRobot r) {

        robot = r;
    }

        public void Update(Gamepad gamepad1, Gamepad gamepad2) {

            // Right or Left Bumpers - Intake and Output
            if (gamepad1.right_bumper || gamepad1.left_bumper) {

                if (gamepad1.right_bumper) {
                    robot.surgicalTubing.setPower(-0.7);
                } else {
                    robot.surgicalTubing.setPower(0.7);
                }
            } else {
                robot.surgicalTubing.setPower(0);
            }

            // Right or Left Triggers - Flip to and From Depositor
            if (gamepad1.right_trigger > 0.1 || gamepad1.left_trigger > 0.1) {

                if (gamepad1.right_trigger > 0) {
                    robot.turnCollector.setPower(-0.7);
//                    robot.turnCollector.getPower();
                } else {
                    robot.turnCollector.setPower(0.7);
                }
            } else {
                robot.turnCollector.setPower(0);
            }
        }
    }
