package org.firstinspires.ftc.teamcode.BLACKBOX.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Auto")
public class Auto extends LinearOpMode {
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
    //private Telemetry telemetry = null;
    static final double TICKS_PER_REV = 537.6;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 3.796;     // For figuring circumference
    static final double COUNTS_PER_INCH = (TICKS_PER_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;
    public ElapsedTime runtime;

    @Override
    public void runOpMode() throws InterruptedException
    {
        frontRight = hardwareMap.dcMotor.get("frontLeft");
        frontLeft = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        surgicalTubing = hardwareMap.dcMotor.get("surgicalTubing");
        turnCollector = hardwareMap.dcMotor.get("turnCollector");
        slide1 = hardwareMap.dcMotor.get("slide1");
        slide2 = hardwareMap.dcMotor.get("slide2");





        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();
        strafeDrive(.6,-20,20,30);
        sleep(500);
        encoderDrive(.5,-32,-32,30);
        sleep(2000);
        encoderDrive(.3,25,25,30); //Moving backwards with it
        // sleep(2000);
        splineDrive(.1,.4,-20,50,20);//Splining into the build zone
        sleep(500);
        encoderDrive(.5,-20,-20,30); //aligning to wall
        sleep(1000);
        sleep(2500);
        strafeDrive(.5,-10,10,30);//aligning to wall
        encoderDrive(.6,40,40,30);//park



        //encoderDrive(.3,6,6,30);
        //  splineDrive(.1,.3,-23,38,30);

        //positioning foundation

    }



    //Encoder Drive and Strafe Drive Methods:







    //Encoder Drive method
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS)
    {

        int newLeftFrontTarget = 0;
        int newRightFrontTarget = 0;
        int newLeftBackTarget = 0;
        int newRightBackTarget= 0;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newLeftFrontTarget = frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightFrontTarget = frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = backLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);


            frontRight.setTargetPosition(newRightFrontTarget);
            frontLeft.setTargetPosition(newLeftFrontTarget);
            backRight.setTargetPosition(newRightBackTarget);
            backLeft.setTargetPosition(newLeftBackTarget);


            // Turn On RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

          /* runtime.reset();
           BL.setPower(Math.abs(speedleft));
           BR.setPower(Math.abs(speedright));
           FL.setPower(Math.abs(speedleft));
           FR.setPower(Math.abs(speedright));*/

            // reset the timeout time and start motion.
            runtime.reset();
            frontRight.setPower(Math.abs(speed));
            frontLeft.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));
            backLeft.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < 31.0) &&
                    (frontRight.isBusy() && frontLeft.isBusy() && (backLeft.isBusy() && (backRight.isBusy())))) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d", newLeftBackTarget, newRightBackTarget);


                // FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                // FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                telemetry.update();





            }

            // Stop all motion;
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);



            // Turn off RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //  sleep(250);   // optional pause after each move
        }
    }

    //Encoder Drive method
    public void splineDrive(double lspeed, double rspeed,
                            double leftInches, double rightInches,
                            double timeoutS)
    {

        int newLeftFrontTarget = 0;
        int newRightFrontTarget = 0;
        int newLeftBackTarget = 0;
        int newRightBackTarget= 0;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newLeftFrontTarget = frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightFrontTarget = frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = backLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);


            frontRight.setTargetPosition(newRightFrontTarget);
            frontLeft.setTargetPosition(newLeftFrontTarget);
            backRight.setTargetPosition(newRightBackTarget);
            backLeft.setTargetPosition(newLeftBackTarget);


            // Turn On RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

          /* runtime.reset();
           BL.setPower(Math.abs(speedleft));
           BR.setPower(Math.abs(speedright));
           FL.setPower(Math.abs(speedleft));
           FR.setPower(Math.abs(speedright));*/

            // reset the timeout time and start motion.
            runtime.reset();
            frontLeft.setPower(Math.abs(rspeed));
            frontRight.setPower(Math.abs(lspeed));
            backLeft.setPower(Math.abs(rspeed));
            backRight.setPower(Math.abs(lspeed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < 31.0) &&
                    (frontRight.isBusy() && frontLeft.isBusy() && (backRight.isBusy() && (backLeft.isBusy())))) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d", newLeftBackTarget, newRightBackTarget);


                // FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                // FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                telemetry.update();





            }

            // Stop all motion;
            frontLeft.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);



            // Turn off RUN_TO_POSITION
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //  sleep(250);   // optional pause after each move
        }
    }

    //Strafe Drive Method:

    public void strafeDrive(double speed,
                            double leftInches, double rightInches,
                            double timeoutS)
    {

        int newLeftFrontTarget = 0;
        int newRightFrontTarget = 0;
        int newLeftBackTarget = 0;
        int newRightBackTarget = 0;



        // Ensure that the opmode is still active
        //if (opModeIsActive()) {

        // Determine new target position, and pass to motor controller

        newLeftFrontTarget = frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
        newRightFrontTarget = frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
        newLeftBackTarget = backLeft.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
        newRightBackTarget = backRight.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);


        frontRight.setTargetPosition(newRightFrontTarget);
        frontLeft.setTargetPosition(-newLeftFrontTarget);
        backRight.setTargetPosition(newRightBackTarget);
        backLeft.setTargetPosition(-newLeftBackTarget);


        // Turn On RUN_TO_POSITION
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

          /* runtime.reset();
           BL.setPower(Math.abs(speedleft));
           BR.setPower(Math.abs(speedright));
           FL.setPower(Math.abs(speedleft));
           FR.setPower(Math.abs(speedright));*/

        // reset the timeout time and start motion.
        runtime.reset();
        frontLeft.setPower(Math.abs(speed));
        backLeft.setPower(Math.abs(speed));
        backRight.setPower(Math.abs(speed));
        frontRight.setPower(Math.abs(speed));


        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while (opModeIsActive() &&
                (runtime.seconds() < 31.0) &&
                (frontRight.isBusy() && frontLeft.isBusy() && (backRight.isBusy() && (backLeft.isBusy())))) {

            // Display it for the driver.
            //telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget);
            //telemetry.addData("Path2",  "Running at %7d :%7d", newLeftBackTarget, newRightBackTarget);


            // FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            // FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            telemetry.update();





        }

        // Stop all motion;
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);



        // Turn off RUN_TO_POSITION
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //  sleep(250);   // optional pause after each move
        //}
    }

    //--------------------------------------------------------------------------------------------------------//

}
