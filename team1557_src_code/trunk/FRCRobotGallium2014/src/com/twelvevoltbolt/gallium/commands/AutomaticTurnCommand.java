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
public class AutomaticTurnCommand extends CommandBase {

    public AutomaticTurnCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (RoboRealm.server != null) {
            int x = (int) RoboRealm.server.getNumber("COG_X");
            int y = (int) RoboRealm.server.getNumber("COG_Y");

            double turn = x - (RoboRealm.IMAGE_WIDTH / 2.0);
            // double deltaY = y - (IMAGE_HEIGHT / 2.0);

            if (Math.abs(turn) > 30) {
                turn /= RoboRealm.IMAGE_WIDTH;
                drive.drive(turn, -turn);
                setTimeout(0.5);
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}