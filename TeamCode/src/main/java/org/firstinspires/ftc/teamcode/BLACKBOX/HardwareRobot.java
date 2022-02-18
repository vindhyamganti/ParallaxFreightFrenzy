package org.firstinspires.ftc.teamcode.BLACKBOX;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp.Carousel;
import org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp.Collector;
import org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp.Depositor;
import org.firstinspires.ftc.teamcode.BLACKBOX.TeleOp.DriveTrain;

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
    public DcMotor extendSlide;
    public DcMotor retractSlide;
    public Servo x1encoder;
    public Servo x2encoder;
    public Servo y1encoder;
    public Servo depositor;
    public CRServo carousel;

    Collector collector;
    DriveTrain DriveTrain;
    Depositor Depositor;
    Carousel Carousel;

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
        extendSlide = hwMap.get(DcMotor.class, "extendSlide");
        retractSlide = hwMap.get(DcMotor.class, "retractSlide");

        // Reverse Directions
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set All Motors To 0 Power
        surgicalTubing.setPower(0);
        turnCollector.setPower(0);
        extendSlide.setPower(0);
        retractSlide.setPower(0);

        // Run Using Encoders
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        surgicalTubing.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turnCollector.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        retractSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Define and Initialize Servos
<<<<<<< Updated upstream:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/BLACKBOX/TeleOp/HardwareRobot.java

        odo1 = hwMap.get(Servo.class, "odo1");
        odo2 = hwMap.get(Servo.class, "odo2");
        odo3 = hwMap.get(Servo.class, "odo3");
        depositor = hwMap.get(Servo.class, "depositor");
        carousel = hwMap.get(Servo.class, "carousel");

        odo1.setPosition(0.1);
        odo2.setPosition(0.1);
        odo3.setPosition(0.1);
=======
        x1encoder = hwMap.get(Servo.class, "x1encoder");
        x2encoder = hwMap.get(Servo.class, "x2encoder");
        y1encoder = hwMap.get(Servo.class, "y1encoder");
        depositor = hwMap.get(Servo.class, "depositor");
        carousel = hwMap.get(CRServo.class, "carousel");
>>>>>>> Stashed changes:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/BLACKBOX/HardwareRobot.java



    }
}
