package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author david
 */ 
public class UltraSubsystem extends Subsystem {

    DigitalModule ultra;
    
    public UltraSubsystem() {
        ultra.allocateDIO(RobotMap.UltrasonicSensor, true);
    }

    protected void initDefaultCommand() {
        
    }
    
    public int getRange() {
        return ultra.getPWM(RobotMap.UltrasonicSensor);
    }
    
}
