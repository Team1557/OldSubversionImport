/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import com.twelvevoltbolt.gallium.commands.CompressorRegulateCommand;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;


public class CompressorSubsystem extends Subsystem {

    private Relay compressor;
    private DigitalInput shutoff;

    public CompressorSubsystem() {
        compressor = new Relay(RobotMap.compressorRelay);
        shutoff = new DigitalInput(RobotMap.compressorShutoffDigitalInput);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new CompressorRegulateCommand());
    }

    public void setCompressing(boolean compress) {
        compressor.set(compress ? Relay.Value.kForward : Relay.Value.kOff);
    }
    
    public Relay getCompressor() {
        return compressor;
    }

    public void setCompressor(Relay compressor) {
        this.compressor = compressor;
    }

    public DigitalInput getShutoff() {
        return shutoff;
    }

    public void setShutoff(DigitalInput shutoff) {
        this.shutoff = shutoff;
    }
}
