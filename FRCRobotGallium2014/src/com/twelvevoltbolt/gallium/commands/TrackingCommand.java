/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.gallium.RoboRealm;


/**
 *
 * @author code
 */
public class TrackingCommand extends CommandBase {

    public TrackingCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    public static int trackMargin = 40;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double angle = 0;
        
        if (RoboRealm.server != null) {
            double x = RoboRealm.server.getNumber("COG_X");
            if (x < (RoboRealm.IMAGE_WIDTH / 2) - trackMargin) {
                angle = -0.3;
            } else if (x > (RoboRealm.IMAGE_WIDTH / 2) + trackMargin) {
                angle = 0.3;
            }
        }
        
        drive.drive(angle, -angle);
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