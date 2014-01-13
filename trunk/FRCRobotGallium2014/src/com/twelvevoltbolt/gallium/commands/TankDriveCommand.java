package com.twelvevoltbolt.gallium.commands;

/**
 * A tank drive command that takes input from two joysticks and outputs to the
 * drive.
 *
 * @author code
 */
public class TankDriveCommand extends CommandBase {

    public TankDriveCommand() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.drive(oi.getLeftInput(), oi.getRightInput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}