/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.disk.commands;

/**
 *
 * @author code
 */
public class ReverseCommand extends CommandBase {
    
    public ReverseCommand() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.toggleReversed();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}