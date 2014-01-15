package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FiringSubsystem extends Subsystem {

//  private Solenoid leftFireShoulder;
//  private Solenoid rightFireShoulder;

    private Solenoid arm;
    
    //private Solenoid leftFireElbow;
    //private Solenoid rightFireElbow;

    public FiringSubsystem() {
//        leftFireShoulder = new Solenoid(RobotMap.leftLauncherShoulder);
//        rightFireShoulder = new Solenoid(RobotMap.rightLauncherShoulder);
        
        //leftFireElbow = new Solenoid(RobotMap.leftLauncherElbow);
        //rightFireElbow = new Solenoid(RobotMap.rightLauncherElbow);
        
        arm = new Solenoid(RobotMap.armLauncher);
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
}
