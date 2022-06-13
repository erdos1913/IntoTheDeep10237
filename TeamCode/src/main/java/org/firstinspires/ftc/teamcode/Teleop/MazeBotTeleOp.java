package org.firstinspires.ftc.teamcode.Teleop;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp(name = "MazeBotTeleOp")
@Disabled

public class MazeBotTeleOp extends LinearOpMode {
    LinearOpMode op = this;
    DcMotorEx motorLeftFront;
    DcMotorEx motorRightFront;
    DcMotorEx motorLeftBack;
    DcMotorEx motorRightBack;

    public void runOpMode() {

        telemetry.addData("Status", "Before new Robot");
        telemetry.update();
        motorLeftFront = (DcMotorEx) op.hardwareMap.dcMotor.get("motorLeftFront");
        motorRightFront = (DcMotorEx) op.hardwareMap.dcMotor.get("motorRightFront");
        motorLeftBack = (DcMotorEx) op.hardwareMap.dcMotor.get("motorLeftBack");
        motorRightBack = (DcMotorEx) op.hardwareMap.dcMotor.get("motorRightBack");

        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }
        resetStartTime();
        while (!isStopRequested()&&getRuntime()<90) {
            float leftStickx = op.gamepad1.left_stick_x;
            float leftSticky = op.gamepad1.left_stick_y;
            float rightStick = op.gamepad1.right_stick_x*0.5f;
            double leftSticktheta = atan2(leftSticky, leftStickx);
            double leftStickr = sqrt(pow(leftSticky, 2) + pow(leftStickx, 2))*0.5;
            double powera = 0;
            double powerb = 0;
            double angle = atan2(leftSticky,leftStickx);

            powera = -sin(angle + PI/4);
            powerb = -sin(angle - PI/4);

//            if ((leftStickx + leftSticky)/(leftSticky - leftStickx) >= 1) {
//                powera = 1;
//                powerb = (leftSticky - leftStickx)/(leftStickx + leftSticky);
//            }
//            else if ((leftStickx + leftSticky)/(leftSticky - leftStickx) <= -1) {
//                powera = -1;
//                powerb = -(leftSticky - leftStickx)/(leftStickx + leftSticky);
//            }
//            else if ((leftStickx + leftSticky)/(leftSticky - leftStickx) > -1 && (leftStickx + leftSticky)/(leftSticky - leftStickx) < 0 || leftSticky-leftStickx < 0 && (leftStickx + leftSticky) == 0) {
//                powerb = -1;
//                powera = -(leftStickx + leftSticky)/(leftSticky - leftStickx);
//            }
//            else if ((leftStickx + leftSticky)/(leftSticky - leftStickx) < 1 && (leftStickx + leftSticky)/(leftSticky - leftStickx) > 0 || leftSticky-leftStickx > 0 && (leftStickx + leftSticky) == 0) {
//                powerb = 1;
//                powera = (leftStickx + leftSticky)/(leftSticky - leftStickx);
//            }


//        if (isSlow) {
//            if(abs(rightStick)<0.999&abs(rightStick)>0.2){
//                rightStick=0.999f*abs(rightStick)/rightStick;
//            }
//            motorLeftBack.setPower(rightSticky * 1.25/Math.sqrt(2));
//            motorRightFront.setPower((power - rightStick) * 0.3);
//            motorRightBack.setPower((power - rightStick) * 0.3);
//            motorLeftFront.setPower((power + rightStick) * 0.3);
//        }
//        else {
            motorLeftFront.setPower(powerb * leftStickr - rightStick);
            motorRightBack.setPower(powerb * leftStickr + rightStick);

            motorRightFront.setPower(powera * leftStickr + rightStick);
            motorLeftBack.setPower(powera * leftStickr - rightStick);

//        }
        }

        idle();
    }
}