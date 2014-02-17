package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author david
 */ 
public class UltraSubsystem extends Subsystem {

    Ultrasonic ultra;
    
    public UltraSubsystem() {
        ultra = new Ultrasonic(RobotMap.pingChannel, RobotMap.echoChannel);
//        ultra.getRangeInches();
    }

    protected void initDefaultCommand() {
        
    }
    
}
