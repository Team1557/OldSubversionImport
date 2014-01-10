package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import com.twelvevoltbolt.gallium.commands.DirectionalFireCommand;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FiringSubsystem extends Subsystem {

    private Solenoid leftFireShoulder;
    private Solenoid rightFireShoulder;

    private Solenoid leftFireElbow;
    private Solenoid rightFireElbow;

    public FiringSubsystem() {
        leftFireShoulder = new Solenoid(RobotMap.leftLauncherShoulder);
        rightFireShoulder = new Solenoid(RobotMap.rightLauncherShoulder);
        
        leftFireElbow = new Solenoid(RobotMap.leftLauncherElbow);
        rightFireElbow = new Solenoid(RobotMap.rightLauncherElbow);
    }

    protected void initDefaultCommand() {
    }
    
    /**
     * Shoots a centered, full power shot.
     */
    public void shoot() {
        shoot(0, 1);
    }
    
    
    /**
     * Shoots a shot, with the given angle, and power.
     * @param angle
     * The angle, from -1 to 1 to shoot at.
     * @param power 
     * How much power to give to the shot.  Currently unused.
     */
    public void shoot(double angle, double power) {
        Solenoid firstToFireShoulder,
            secondToFireShoulder,
            firstToFireElbow,
            secondToFireElbow;
        
        // Joystick input to the left means the right arm fires first, and vice versa
        if (angle < 0) {
            firstToFireShoulder = rightFireShoulder;
            secondToFireShoulder = leftFireShoulder;
            firstToFireElbow = rightFireElbow;
            secondToFireElbow = leftFireElbow;
        } else {
            firstToFireShoulder = leftFireShoulder;
            secondToFireShoulder = rightFireShoulder;
            firstToFireElbow = leftFireElbow;
            secondToFireElbow = rightFireElbow;
        }
        
        // Deadzone
        if (Math.abs(angle) < 0.1) {
            angle = 0;
        }
        
        double wait = Math.abs(angle) * DirectionalFireCommand.FIRING_AIM_DELAY_MULTIPLIER;
        
        // Fires the arms
        firstToFireShoulder.set(true);
        
        Timer.delay(wait);
        
        secondToFireShoulder.set(true);
        
        // Wait for full shooty action
        Timer.delay(1);
        
        // Retract both
        firstToFireShoulder.set(false);
        firstToFireElbow.set(false);
        secondToFireShoulder.set(false);
        secondToFireElbow.set(false);
    }

    public Solenoid getLeftFireShoulder() {
        return leftFireShoulder;
    }

    public Solenoid getRightFireShoulder() {
        return rightFireShoulder;
    }

    public Solenoid getLeftFireElbow() {
        return leftFireElbow;
    }

    public Solenoid getRightFireElbow() {
        return rightFireElbow;
    }
}
