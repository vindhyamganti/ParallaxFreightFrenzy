package org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.BLACKBOX.HardwareRobot;

@TeleOp
public class TeleOpFirstComp extends LinearOpMode {

    @Override

    public void runOpMode() throws InterruptedException {

        HardwareRobot robot = new HardwareRobot();

        robot.init(hardwareMap);

        Depositor depositor = new Depositor(robot);
        DriveTrain driveTrain = new DriveTrain(robot);
        Carousel carousel = new Carousel(robot);
        Collector collector = new Collector(robot);

        waitForStart();


        if (isStopRequested()) return;


        while(opModeIsActive()) {
            depositor.Update(gamepad1, gamepad2, this);
            driveTrain.Update(gamepad1, gamepad2);
            carousel.Update(gamepad1, gamepad2);
            collector.Update(gamepad1, gamepad2);


        }


    }
}


