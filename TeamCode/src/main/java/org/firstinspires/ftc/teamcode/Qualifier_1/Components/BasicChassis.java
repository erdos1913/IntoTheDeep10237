package org.firstinspires.ftc.teamcode.Qualifier_1.Components;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public abstract class BasicChassis {
    //initialize motor
    DcMotorEx motorLeftFront;
    DcMotorEx motorRightFront;
    DcMotorEx motorLeftBack;
    DcMotorEx motorRightBack;

    // Initialize Encoder Variables
    final double robot_diameter = Math.sqrt(619.84);
    final double wheel_diameter = 3.93701;

    // these encoder variables vary depending on chassis type
    final double counts_per_motor_goBilda = 383.6;
    final double counts_per_inch = 2 * (counts_per_motor_goBilda / (wheel_diameter * Math.PI));
    final double counts_per_degree = counts_per_inch * robot_diameter * Math.PI / 360;

    /* local OpMode members. */
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    public BasicChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;
        //lastAngles  = new Orientation();
        // Chassis motors
        motorLeftFront = (DcMotorEx) hardwareMap.dcMotor.get("motorLeftFront");
        motorRightFront = (DcMotorEx) hardwareMap.dcMotor.get("motorRightFront");
        motorLeftBack = (DcMotorEx) hardwareMap.dcMotor.get("motorLeftBack");
        motorRightBack = (DcMotorEx) hardwareMap.dcMotor.get("motorRightBack");

        // Chassis Motors
        motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        motorRightFront.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE);
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);

        // reset encoder count kept by left motor.
        motorLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    abstract public void stopAllMotors();
    abstract public void turnInPlace(double target, double power);
    abstract public void moveForward(double distance, double power) ;
    abstract public void moveBackward(double distance, double power);
    abstract public void moveRight(double distance, double power);
    abstract public void moveLeft(double distance, double power);
     public void moveMultidirectional(double power, double angle, float rightStick){
         double angleInRadian;
         angleInRadian = Math.toRadians(angle);

         motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
         motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

         motorLeftBack.setPower(Math.sin(angleInRadian - Math.PI / 4) * power - rightStick);
         motorRightBack.setPower(Math.sin(angleInRadian + Math.PI / 4) * power + rightStick);
         motorLeftFront.setPower(Math.sin(angleInRadian + Math.PI / 4) * power - rightStick);
         motorRightFront.setPower(Math.sin(angleInRadian - Math.PI / 4) * power + rightStick);
     }
}