/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands;

/**
 *
 * @author code
 */
public class DirectionalFireCommand extends CommandBase {

    private double direction;
    
    public DirectionalFireCommand(float direction) {
        requires(firing);    
        
        this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        firing.shoot(direction, 1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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