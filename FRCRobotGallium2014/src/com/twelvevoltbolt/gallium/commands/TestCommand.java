/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands;

/**
 * // TODO: Test button bound to test command
 * @author code
 */
public class TestCommand extends CommandBase {
    
    public TestCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        System.out.println("such construct");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("much initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("so execute");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("wow, many ends");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("why so interrupt");
    }
}