/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands;

/**
 *
 * @author code
 */
public class VacuumSuckCommand extends CommandBase {
    
    public VacuumSuckCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(vacuum);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        vacuum.setSuck(true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        vacuum.setSuck(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}