/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import com.twelvevoltbolt.gallium.commands.CompressorRegulateCommand;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class CompressorSubsystem extends Subsystem {

    private Compressor compressor;
    
    public CompressorSubsystem() {
        compressor = new Compressor(RobotMap.compressorShutoffDigitalInput, RobotMap.compressorRelay);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new CompressorRegulateCommand());
    }

    public void setCompressing(boolean compress) {
        if (compress) {
            compressor.start();
        } else {
            compressor.stop();
        }
    }
    
    public Compressor getCompressor() {
        return compressor;
    }
}
