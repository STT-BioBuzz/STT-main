package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "MecanumDrive", group = "TeleOp")
public class MecanumDrive extends LinearOpMode {

    MecanumDrivetrain MecanumDrivetrain = new MecanumDrivetrain();

    @Override
    public void runOpMode() {

        MecanumDrivetrain.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double r = gamepad1.right_stick_x;



            MecanumDrivetrain.drive(y,x,r,1);
            telemetry.update();

        }
    }
}