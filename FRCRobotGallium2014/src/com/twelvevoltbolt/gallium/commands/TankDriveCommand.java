package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.gallium.MathUtils;
import com.twelvevoltbolt.gallium.RobotMap;

/**
 * A tank drive command that takes input from two joysticks and outputs to the
 * drive.
 *
 * The Driver (Left) Joysticks always override the alternate joysticks.
 * The Alternate (Right) Joysticks control the turning motion with the horizontal axis, when the Driver joysticks are not active.
 * When the Trigger on the Alternate (Right) Joystick is held, the vertical axis on the Alternate (Right) Joystick controls the ball pickup arm.
 * 
 * 
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
        drive.updateGears();
        
        motorLeft = normalize(oi.getLeftInput(), motorLeft);
        motorRight = normalize(oi.getRightInput(), motorRight);
        
        if (oi.getLeftInput() != 0 || oi.getRightInput() != 0) {
            drive.drive(motorLeft, motorRight);
        } else {
            double angle = oi.getFiringAngle();
            
            if (Math.abs(angle) > 0.3) {
                drive.drive(angle / 2, -angle / 2);
            } else {
                drive.drive(0, 0);
            }
        }
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