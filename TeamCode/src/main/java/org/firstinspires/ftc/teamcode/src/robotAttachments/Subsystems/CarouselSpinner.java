package org.firstinspires.ftc.teamcode.src.robotAttachments.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class CarouselSpinner {
    private final CRServo spinnerServo;
    private static final double servoPower = 1;
    private static final long duckSleepTime = 3100;

    public CarouselSpinner(HardwareMap hardwareMap, String deviceName) {
        spinnerServo = hardwareMap.crservo.get(deviceName);
    }

    public void spinOffRedDuck() throws InterruptedException {
        spinnerServo.setPower(-servoPower);
        ElapsedTime t = new ElapsedTime();
        while (t.milliseconds() < duckSleepTime) {
        }
        spinnerServo.setPower(0);

    }
    public void spinOffBlueDuck() throws InterruptedException {
        spinnerServo.setPower(servoPower);
        //Thread.sleep(duckSleepTime);
        ElapsedTime t = new ElapsedTime();
        while (t.milliseconds() < duckSleepTime) {
        }
        spinnerServo.setPower(0);

    }

    public void spinForward(){
        spinnerServo.setPower(servoPower);
    }
    public void spinBackward(){
        spinnerServo.setPower(-servoPower);
    }

    public void stop(){
        spinnerServo.setPower(0);
    }
}
