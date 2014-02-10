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

    private double targetX;
    private double targetY;
    private int trackMargin = 40;
    
    public TrackingCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(drive);
        
        targetX = RoboRealm.IMAGE_WIDTH / 2;
        targetY = RoboRealm.IMAGE_HEIGHT / 2;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double angle = 0;
        double impulse = 0;
        
        if (RoboRealm.server != null) {
            double x = RoboRealm.server.getNumber("COG_X");
            if (x < targetX - trackMargin) {
                angle = -0.3;
            } else if (x > targetX + trackMargin) {
                angle = 0.3;
            }
            
            double y = RoboRealm.server.getNumber("COG_Y");
            if (y < targetY - trackMargin) {
                impulse = 0;
            } else if (y > targetY + trackMargin) {
                impulse = 0;
            }
        }
        
        double motorLeft = angle + impulse;
        double motorRight = -angle + impulse;
        
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

    public double getTargetX() {
        return targetX;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public int getTrackMargin() {
        return trackMargin;
    }

    public void setTrackMargin(int trackMargin) {
        this.trackMargin = trackMargin;
    }
}