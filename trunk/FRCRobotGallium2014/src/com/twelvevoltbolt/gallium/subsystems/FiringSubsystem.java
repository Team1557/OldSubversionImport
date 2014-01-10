package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FiringSubsystem extends Subsystem {

    Solenoid leftFireArm;
    Solenoid rightFireArm;

    public FiringSubsystem() {
        leftFireArm = new Solenoid(RobotMap.leftLauncher);
        rightFireArm = new Solenoid(RobotMap.rightLauncher);
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
     * The maximum delay is determined by the multiplier, where an input of 1 with a multiplier of 1/3 gives a delay of 1/3rd seconds
     */
    public static double FIRING_DELAY_MULTIPLIER = 1 / 3;
    
    /**
     * Shoots a shot, with the given angle, and power.
     * @param angle
     * The angle, from -1 to 1 to shoot at.
     * @param power 
     * How much power to give to the shot.  Currently unused.
     */
    public void shoot(double angle, double power) {
        Solenoid firstToFire;
        Solenoid secondToFire;
        
        // Joystick input to the left means the right arm fires first, and vice versa
        if (angle < 0) {
            firstToFire = rightFireArm;
            secondToFire = leftFireArm;
        } else {
            firstToFire = leftFireArm;
            secondToFire = rightFireArm;
        }
        
        // Deadzone
        if (Math.abs(angle) < 0.1) {
            angle = 0;
        }
        
        double wait = Math.abs(angle) * FIRING_DELAY_MULTIPLIER;
        
        // Fires the arms
        firstToFire.set(true);
        Timer.delay(wait);
        secondToFire.set(true);
        
        // Wait for full shooty action
        Timer.delay(1);
        
        // Retract both
        firstToFire.set(false);
        secondToFire.set(false);
    }
}
