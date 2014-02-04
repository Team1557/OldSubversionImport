/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ShifterSubsystem extends Subsystem {

    Solenoid superShifter;
    
    public ShifterSubsystem() {
        superShifter = new Solenoid(RobotMap.SuperShifter);
    }

    protected void initDefaultCommand() {
    }

    public void shiftGear(boolean gear) {
        superShifter.set(gear);
    }
}
