package org.firstinspires.ftc.teamcode.BLACKBOX;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Collector extends LinearOpMode {
    @Override
    //Motor Configuration Names
    public void runOpMode() throws InterruptedException {

        HardwareRobot robot = new HardwareRobot();

        robot.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;


        while (opModeIsActive()) {
            if (gamepad1.right_bumper) {
                robot.surgicalTubing.setPower(0.05);
            }

            if (gamepad1.left_bumper) {
                robot.surgicalTubing.setPower(-0.05);
            }

            if (gamepad1.b) {
                robot.turnCollector.setPower(0.05);
            }
        }
    }
}
