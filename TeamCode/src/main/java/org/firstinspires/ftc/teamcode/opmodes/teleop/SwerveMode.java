package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrains.Swerve;

//the name is how this Opmode will show up on the driver-hub
@TeleOp(name = "swervey", group = "TeleOp")
public class SwerveMode extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();
    Swerve swerve = new Swerve();

    @Override
    public void runOpMode() throws InterruptedException
    {
        swerve.init(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive())
        {
            swerve.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        }
    }
}