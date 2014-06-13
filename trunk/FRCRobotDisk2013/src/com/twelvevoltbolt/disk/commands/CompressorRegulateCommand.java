/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.disk.commands;

/**
 * Regulates the compressor pressure by turning off the compressor when the shutoff value indicates optimal pressure.
 * @author code
 */
public class CompressorRegulateCommand extends CommandBase {
    
    public CompressorRegulateCommand() {
        requires(compressor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        compressor.setCompressing(true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        compressor.setCompressing(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}