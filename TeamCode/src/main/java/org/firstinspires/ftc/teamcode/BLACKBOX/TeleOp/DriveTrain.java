package org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.BLACKBOX.HardwareRobot;


public class DriveTrain {

    HardwareRobot robot;

    public DriveTrain(HardwareRobot r) {
        robot = r;
    }


        public void Update(Gamepad gamepad1, Gamepad gamepad2) {

            double y = gamepad1.left_stick_x;
            double x = -gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            if (denominator == 0) {
                robot.frontLeft.setPower(0);
                robot.frontRight.setPower(0);
                robot.backLeft.setPower(0);
                robot.backRight.setPower(0);
            }

            else {
                double frontLeftPower = (y + x + rx) / denominator;
                double backLeftPower = (y - x - rx) / denominator;
                double frontRightPower = (y - x + rx) / denominator;
                double backRightPower = (y + x - rx) / denominator;

                robot.frontLeft.setPower(frontLeftPower);
                robot.frontRight.setPower(frontRightPower);
                robot.backLeft.setPower(backLeftPower);
                robot.backRight.setPower(backRightPower);
            }

        }
    }

