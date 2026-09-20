package org.firstinspires.ftc.teamcode.subsystems.Drivetrains;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.UtilityOctoQuadConfigMenu;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Swerve {
    public class Pod {
        public DcMotorEx A;
        public DcMotorEx B;
        public int Astart;
        public int Bstart;

        public double x;
        public double y;
        public double goalAngle;
        public double goalAngleTicks;
        public double currentAngleTicks;

        public double length;
        public double rotation;

        public int direction;
    }

    double TICKS_PER_ROTATION = 3000;

    Pod Lpod = new Pod();
    Pod Rpod = new Pod();

    Pod[] pods = {Lpod, Rpod};

    public void init(HardwareMap hardwareMap)
    {
        Lpod.A = hardwareMap.get(DcMotorEx.class, "L_A");
        Lpod.B = hardwareMap.get(DcMotorEx.class, "L_B");
        Rpod.A = hardwareMap.get(DcMotorEx.class, "R_A");
        Rpod.B = hardwareMap.get(DcMotorEx.class, "R_B");

        Lpod.A.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lpod.B.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Rpod.A.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Rpod.B.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Lpod.Astart = Lpod.A.getCurrentPosition();
        Lpod.Bstart = Lpod.B.getCurrentPosition();
        Rpod.Astart = Rpod.A.getCurrentPosition();
        Rpod.Bstart = Rpod.B.getCurrentPosition();
    }

    public void drive(double y, double x, double rotation, Telemetry telemetry)
    {
        Lpod.x = x;
        Lpod.y = y - rotation;

        Rpod.x = x;
        Rpod.y = y + rotation;

        for (Pod pod : pods)
        {

            pod.direction = 1;
            pod.goalAngle = Math.atan2(pod.y, pod.x);
            pod.length = Math.sqrt(Math.pow(pod.x, 2) + Math.pow(pod.y, 2));

            pod.goalAngleTicks = pod.goalAngle / (2 * Math.PI) * TICKS_PER_ROTATION;
            pod.currentAngleTicks = pod.A.getCurrentPosition() - pod.Astart - (pod.B.getCurrentPosition() - pod.Bstart);
            if (pod == Lpod) pod.currentAngleTicks = -pod.currentAngleTicks;

            double error = shortestAngleTicks(pod.goalAngleTicks - pod.currentAngleTicks);
            pod.rotation = error / TICKS_PER_ROTATION;
            if ((Math.abs(error) < 60) || (Math.abs(pod.length) < 0.1))
            {
                pod.rotation = 0;
            }
            telemetry.addData("currentangleticks", pod.currentAngleTicks);
            telemetry.addData("goalangleticks", pod.goalAngleTicks);
            telemetry.addData("rotation", pod.rotation);

            /*
            if (Math.abs(pod.rotation) > 0.25)
            {
                pod.direction = -1;
                double flippedError = shortestAngleTicks(pod.goalAngleTicks + 0.5 * TICKS_PER_ROTATION - pod.currentAngleTicks);
                pod.rotation = flippedError / TICKS_PER_ROTATION;
            }
             */
        }
        Rpod.A.setPower(0.5 * (Rpod.length * Rpod.direction + Rpod.rotation));
        Rpod.B.setPower(0.5 * (Rpod.length * Rpod.direction - Rpod.rotation));
        Lpod.A.setPower(0.5 * (-Lpod.length * Lpod.direction - Lpod.rotation));
        Lpod.B.setPower(0.5 * (-Lpod.length * Lpod.direction + Lpod.rotation));
    }

    private double shortestAngleTicks(double in)
    {
        while (in > 0.5 * TICKS_PER_ROTATION) in -= TICKS_PER_ROTATION;
        while (in < -0.5 * TICKS_PER_ROTATION) in += TICKS_PER_ROTATION;
        return in;
    }
}