/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.gallium.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;


public class VacuumSubsystem extends Subsystem {

    private DigitalOutput vacuum;
    
    public VacuumSubsystem() {
        vacuum = new DigitalOutput(1);
    }
    
    protected void initDefaultCommand() {
    }
    
    public void setSuck(boolean sucks) {
        vacuum.set(sucks);
    }
}
