package org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class CarouselMotors extends LinearOpMode {
    DcMotor CarouselMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        CarouselMotor = hardwareMap.dcMotor.get("CarouselMotor");
        CarouselMotor.setPower(0.1);

    }
}

