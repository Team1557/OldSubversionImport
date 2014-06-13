package com.twelvevoltbolt.disk.subsystems;

import com.twelvevoltbolt.disk.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.util.AllocationException;

public class FiringSubsystem extends Subsystem {

    private Solenoid lArmOn;
    private Solenoid lArmOff;
    
    private Solenoid rArmOn;
    private Solenoid rArmOff;

    public FiringSubsystem() {
        try {
            lArmOn = new Solenoid(RobotMap.leftArmLauncherOn); //Finish by implementing by adding support for all 4 solenoids
            lArmOff = new Solenoid(RobotMap.leftArmLauncherOff);
            rArmOn = new Solenoid(RobotMap.rightArmLauncherOn);
            rArmOff = new Solenoid(RobotMap.rightArmLauncherOff);
        } catch (AllocationException ex) {
            System.out.println("Solenoids failed to initialize.");
        }
        
        LiveWindow.addActuator(getName(), "Left Arm On", lArmOn);
        LiveWindow.addActuator(getName(), "Left Arm Off", lArmOff);
        LiveWindow.addActuator(getName(), "Right Arm On", rArmOn);
        LiveWindow.addActuator(getName(), "Right Arm Off", rArmOff);
    }

    protected void initDefaultCommand() {
    }
    
    public Solenoid getLeftArmOn() {
        return lArmOn;
    }

    public Solenoid getLeftArmOff() {
        return lArmOff;
    }

    public Solenoid getRightArmOn() {
        return rArmOn;
    }

    public Solenoid getRightArmOff() {
        return rArmOff;
    }
}
