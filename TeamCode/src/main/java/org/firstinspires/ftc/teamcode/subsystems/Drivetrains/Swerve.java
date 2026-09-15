package org.firstinspires.ftc.teamcode.subsystems.Drivetrains;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Swerve {
    public class Pod {
        public DcMotorEx A;
        public DcMotorEx B;
        public double forward;
        public double rotation;
    }

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
    }

    public void drive(double forward, double angular, double turn)
    {
        Lpod.forward = (1 / (1 + turn)) * (forward + turn);
        Lpod.rotation = angular;
        Rpod.forward = (1 / (1 + turn)) * (forward - turn);
        Rpod.rotation = angular;

        for (Pod pod : pods)
        {
            pod.A.setPower(0.5 * (pod.forward + pod.rotation));
            pod.B.setPower(0.5 * (pod.forward - pod.rotation));
        }
    }

}
