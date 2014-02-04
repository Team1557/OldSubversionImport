package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import com.twelvevoltbolt.gallium.commands.CommandBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.AllocationException;

public class FiringSubsystem extends Subsystem {

    private Solenoid arm;
    private Solenoid arm2;

    public FiringSubsystem() {
        try {
            arm = new Solenoid(RobotMap.armLauncher);
            arm2 = new Solenoid(RobotMap.armLauncher2);
        } catch (AllocationException ex) {
            System.out.println("Solenoids failed to initialize.");
        }
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
        fire(2);
    }

    public void fire(int seconds) {
        if (CommandBase.oi.isDebug()) {
            System.out.println("Firing with timeout of : " + seconds);
        }
        getArm().set(true);
        getArm2().set(true);
        Timer.delay(seconds);
        getArm().set(false);
        getArm2().set(false);
    }
}
