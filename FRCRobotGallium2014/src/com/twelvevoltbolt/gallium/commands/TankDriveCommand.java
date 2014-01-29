package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.gallium.MathUtils;
import com.twelvevoltbolt.gallium.RobotMap;

/**
 * A tank drive command that takes input from two joysticks and outputs to the
 * drive.
 *
 * @author code
 */
public class TankDriveCommand extends CommandBase {
    
    double motorLeft = 0;
    double motorRight = 0;
    
    public TankDriveCommand() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    public double normalize(double joystick, double motor) {
        if (Math.abs(joystick) < Math.abs(motor) || Math.abs(joystick)< RobotMap.motorRampStart) {
            return joystick;
        } else {
            return motor + MathUtils.sign(joystick) * Math.min(RobotMap.motorRampStep, Math.abs(joystick) - Math.abs(motor));
        }
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        motorLeft = normalize(oi.getLeftInput(), motorLeft);
        motorRight = normalize(oi.getRightInput(), motorRight);
        
        drive.updateGears();
        
        drive.drive(motorLeft, motorRight);
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