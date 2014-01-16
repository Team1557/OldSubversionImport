/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.Timer;

class DriveForwardCommand extends CommandBase {

    private double speed;
    private double time;

    public DriveForwardCommand(double speed, double time) {
        this.speed = speed;
        this.time = time;
        
        requires(drive);
        requires(firing);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        drive.drive(speed, speed);
        Timer.delay(time);
        drive.drive(0.0, 0.0);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
