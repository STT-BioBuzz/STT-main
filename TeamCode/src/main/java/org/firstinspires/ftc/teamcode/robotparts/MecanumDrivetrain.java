package org.firstinspires.ftc.teamcode.robotparts;

import  com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDrivetrain {

    private DcMotorEx fl;
    private DcMotorEx fr;
    private DcMotorEx bl;
    private DcMotorEx br;

    public void init(HardwareMap map) {

        fl = map.get(DcMotorEx.class, "front_left");
        fr = map.get(DcMotorEx.class, "front_right");
        bl = map.get(DcMotorEx.class, "back_left");
        br = map.get(DcMotorEx.class, "back_right");

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void drive(double y, double x, double r, double power){

        double flp = (y - x - r) * power;
        double frp = (-y - x - r) * power;
        double blp = (-y - x + r) * power;
        double brp = (-y + x - r) * power;

        fl.setPower(flp);
        fr.setPower(frp);
        bl.setPower(blp);
        br.setPower(brp);


    }













}
