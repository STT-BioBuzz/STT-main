package org.firstinspires.ftc.teamcode.robotparts;

import  com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class UpperParts {

    private DcMotorEx intake;
    private DcMotorEx transfer;

    private DcMotorEx shooterLeft;
    private DcMotorEx shooterRight;

    public void init(HardwareMap map) {

        intake = map.get(DcMotorEx.class, "Intake");
        transfer = map.get(DcMotorEx.class, "Transfer");
        shooterLeft = map.get(DcMotorEx.class, "ShooterLeft");
        shooterRight = map.get(DcMotorEx.class, "ShooterRight");

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transfer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void Intake(boolean IntakeOn){

         if (IntakeOn) {
             intake.setPower(100);
         }
    }

    public void Transfer(boolean TransferOn){

        if (TransferOn) {
            transfer.setPower(100);
        }
    }
    public void Shooter(double ShooterPower){

        shooterLeft.setPower(ShooterPower);
        shooterRight.setPower(-ShooterPower);

    }















}
