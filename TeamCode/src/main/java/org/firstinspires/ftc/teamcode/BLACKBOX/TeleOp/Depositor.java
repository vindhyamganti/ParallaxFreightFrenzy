                    package org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BLACKBOX.HardwareRobot;

import java.util.concurrent.TimeUnit;


public class Depositor {

    HardwareRobot robot;
    ElapsedTime time;
    boolean stateServo = false;
    boolean prevPress = false;


    public Depositor(HardwareRobot r) {
        time = new ElapsedTime();

        robot = r;
    }

    public void Update(Gamepad gamepad1, Gamepad gamepad2, LinearOpMode linearOpMode) {

        robot.extendSlide.setPower(0);
        robot.retractSlide.setPower(0);

        while (gamepad2.dpad_up == true) {
            robot.extendSlide.setPower(0.3);
            robot.retractSlide.setPower(0.2);
        }
//        while (gamepad2.dpad_up == false) {
//            robot.extendSlide.setPower(0.0);
//            robot.retractSlide.setPower(0.0);
//        }

        while (gamepad2.dpad_down == true) {
            robot.extendSlide.setPower(-0.3);
            robot.retractSlide.setPower(-0.2);
        }
//        while (gamepad2.dpad_down == false) {
//            robot.extendSlide.setPower(0.0);
//            robot.retractSlide.setPower(0.0);
//        }

        if (gamepad2.dpad_right) {
            robot.depositor.setPosition(0.61);
        }

//        if (gamepad2.dpad_up && !prevPress) {
//            stateServo = true;
//            time.reset();
//        }
//
//        prevPress = gamepad2.dpad_right;
//
//        if (stateServo) {
//            if (time.time() > 2.0) {
//                robot.depositor.setPosition(0);
//                stateServo = false;
//            } else {
//                robot.depositor.setPosition(0.61);
//
//
//            }
        }
    }



