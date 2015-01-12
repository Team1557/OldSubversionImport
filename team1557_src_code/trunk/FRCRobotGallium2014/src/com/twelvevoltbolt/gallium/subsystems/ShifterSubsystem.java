/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class ShifterSubsystem extends Subsystem {

    Solenoid superShifter1;
    Solenoid superShifter2;
    
//    private Encoder encoder;
    
    public ShifterSubsystem() {
        superShifter1 = new Solenoid(RobotMap.superShifter1);
        superShifter2 = new Solenoid(RobotMap.superShifter2);
        
//        encoder = new Encoder(RobotMap.encoderA, RobotMap.encoderB, false, CounterBase.EncodingType.k4X);
//        encoder.setDistancePerPulse(1);
//        encoder.setMaxPeriod(10);
//        encoder.setMinRate(1);
//        encoder.start();
        
        LiveWindow.addActuator(getName(), "SuperShifter on", superShifter1);
        LiveWindow.addActuator(getName(), "SuperShifter off", superShifter2);
    }

    protected void initDefaultCommand() {
    }

    public void shiftGear(boolean gear) {
        superShifter1.set(gear);
        superShifter2.set(!gear);
    }
}
