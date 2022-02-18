package org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.BLACKBOX.HardwareRobot;

public class Carousel {

    HardwareRobot robot;

    public Carousel(HardwareRobot r) {

        robot = r;
    }

        public void Update(Gamepad gamepad1, Gamepad gamepad2) {

            // B - Carousel spins
            if (gamepad2.b) {
                robot.carousel.setPower(-1);
            }

            else {
                robot.carousel.setPower(0);
            }

        }
        
    }

