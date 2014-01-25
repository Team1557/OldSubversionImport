package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FiringSubsystem extends Subsystem {

    private Solenoid arm;
    private Solenoid arm2;

    public FiringSubsystem() {
        arm = new Solenoid(RobotMap.armLauncher);
        arm2 = new Solenoid(RobotMap.armLauncher2);
    }

    protected void initDefaultCommand() {
    }

    public Solenoid getArm() {
        return arm;
    }

    public Solenoid getArm2() {
        return arm2;
    }

    public void fire() {
        fire(2000);
    }

    public void fire(int millis) {
        getArm().set(true);
        getArm2().set(true);
        Timer.delay(millis);
        getArm().set(false);
        getArm2().set(false);
    }
}
