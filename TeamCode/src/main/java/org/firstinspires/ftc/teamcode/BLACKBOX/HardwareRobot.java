package org.firstinspires.ftc.teamcode.BLACKBOX;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */

public class HardwareRobot {

    // Public opMode Members
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public DcMotor surgicalTubing;
    public DcMotor turnCollector;
    public DcMotor slide1;
    public DcMotor slide2;
    public Servo odo1;
    public Servo odo2;
    public Servo odo3;
    public Servo depositor;
    public Servo carousel;

    // Local opMode Members
    HardwareMap hwMap;
    private ElapsedTime period = new ElapsedTime();

    // Constructor
    public HardwareRobot() {

    }

    // Initialize Standard Hardware Interfaces
    public void init(HardwareMap ahwMap) {
        // Save Reference to HardwareMap
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        surgicalTubing = hwMap.get(DcMotor.class, "surgicalTubing");
        turnCollector = hwMap.get(DcMotor.class, "turnCollector");
        slide1 = hwMap.get(DcMotor.class, "slide1");
        slide2 = hwMap.get(DcMotor.class, "slide2");

        // Reverse Directions
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set All Motors To 0 Power
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        surgicalTubing.setPower(0);
        turnCollector.setPower(0);
        slide1.setPower(0);
        slide2.setPower(0);

        // Run Using Encoders
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and Initialize Servos
        odo1 = hwMap.get(Servo.class, "odo1");
        odo2 = hwMap.get(Servo.class, "odo2");
        odo3 = hwMap.get(Servo.class, "odo3");
        depositor = hwMap.get(Servo.class, "depositor");
        carousel = hwMap.get(Servo.class, "carousel");



    }
}
