/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.autonomous.AutonomousTables;
import com.twelvevoltbolt.autonomous.NotConnectedException;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

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

    private boolean hasBall = false;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        vacuum.setSuck(true);
        try {
            if (timeSinceInitialized() > 2) {
//                System.out.println("VacCurr: " + vacuum.vacuum1.getOutputCurrent());
                double current = vacuum.vacuum1.getOutputCurrent();
                if (current < 19 && !hasBall) {
                    hasBall = true;
                    try {
                        AutonomousTables.setAutoBoolean("HAS_BALL", true);
                    } catch (NotConnectedException ex) {
                    }
                } else if (current > 20 && hasBall) {
                    hasBall = false;
                    try {
                        AutonomousTables.setAutoBoolean("HAS_BALL", false);
                    } catch (NotConnectedException ex) {
                    }
                }
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        try {
            AutonomousTables.setAutoBoolean("HAS_BALL", false);
        } catch (NotConnectedException ex) {
        }
        vacuum.setSuck(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}