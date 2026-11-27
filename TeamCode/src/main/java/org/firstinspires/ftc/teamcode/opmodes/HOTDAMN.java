package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotparts.UpperParts;
import org.firstinspires.ftc.teamcode.robotparts.MecanumDrivetrain;

@TeleOp(name = "HOTDAMN", group = "TeleOp")
public class HOTDAMN extends LinearOpMode {

    MecanumDrivetrain MecanumDrivetrain = new MecanumDrivetrain();

    @Override
    public void runOpMode() {

        MecanumDrivetrain.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double r = gamepad1.right_stick_x;

            if



            MecanumDrivetrain.drive(y,x,r,1);
            telemetry.update();

        }
    }
}