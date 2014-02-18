package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author david
 */ 
public class UltraSubsystem extends Subsystem {

//    Ultrasonic ultra;
    DigitalModule ultra;
    
    public UltraSubsystem() {
//        ultra = new Ultrasonic(RobotMap.pingChannel, RobotMap.echoChannel);
        ultra = DigitalModule.getInstance(RobotMap.ultra);
        
//        ultra.getRangeInches();
//        LiveWindow.addSensor(getName(), "Ultrasonic Sensor", ultra);
    }

    protected void initDefaultCommand() {
        
    }
    
    public DigitalModule getUltrasonic() {
        return ultra;
    }
}
