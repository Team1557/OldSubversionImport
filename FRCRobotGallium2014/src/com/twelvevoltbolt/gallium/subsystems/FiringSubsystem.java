package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FiringSubsystem extends Subsystem {

//  private Solenoid leftFireShoulder;
//  private Solenoid rightFireShoulder;

    private Solenoid arm;
    private Solenoid arm2;
    
    //private Solenoid leftFireElbow;
    //private Solenoid rightFireElbow;

    public FiringSubsystem() {
//        leftFireShoulder = new Solenoid(RobotMap.leftLauncherShoulder);
//        rightFireShoulder = new Solenoid(RobotMap.rightLauncherShoulder);
        
        //leftFireElbow = new Solenoid(RobotMap.leftLauncherElbow);
        //rightFireElbow = new Solenoid(RobotMap.rightLauncherElbow);
        
        arm = new Solenoid(RobotMap.armLauncher);
        arm2 = new Solenoid(RobotMap.armLauncher2);
    }

    protected void initDefaultCommand() {
    }

//    public Solenoid getLeftFireShoulder() {
//        return leftFireShoulder;
//    }
//
//    public Solenoid getRightFireShoulder() {
//        return rightFireShoulder;
//    }

    /*public Solenoid getLeftFireElbow() {
        return leftFireElbow;
    }

    public Solenoid getRightFireElbow() {
        return rightFireElbow;
    }*/

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
