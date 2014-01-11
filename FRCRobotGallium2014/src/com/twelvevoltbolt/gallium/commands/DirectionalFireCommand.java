/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author code
 */
public class DirectionalFireCommand extends CommandGroup {

    /**
     * The maximum delay is determined by the multiplier, where an input of 1
     * with a multiplier of 1/3 gives a delay of 1/3rd seconds
     */
    public static double FIRING_AIM_DELAY_MULTIPLIER = 0.333;
    /**
     * The maximum delay is determined by the multiplier, where an input of 1
     * with a multiplier of 1/3 gives a delay of 1/3rd seconds
     */
    public static double FIRING_JOINT_DELAY = 0.5;

    public static class MoveFireArmCommand extends CommandBase {

        Solenoid shoulder;
        Solenoid elbow;

        public MoveFireArmCommand(boolean isLeftSide) {
            requires(firing);
            
            
            
            this.shoulder = isLeftSide ? firing.getLeftFireShoulder() : firing.getRightFireShoulder();
            this.elbow = isLeftSide ? firing.getLeftFireElbow() : firing.getRightFireElbow();
        }
        
        public double getDelay() {
            double direction = ((DirectionalFireCommand) getGroup()).getDirection();
            
            // Deadzone
            if (Math.abs(direction) < 0.1) {
                direction = 0;
            }

            return Math.abs(direction) * DirectionalFireCommand.FIRING_AIM_DELAY_MULTIPLIER;
        }

        public void fire() {
            shoulder.set(true);
            setTimeout(getDelay());
            elbow.set(true);
        }

        public void retract() {
            elbow.set(false);
            setTimeout(getDelay());
            shoulder.set(false);
        }

        protected void initialize() {
            // First set fires the specific side
            fire();

            // Wait for everything to CALM DOWN
            setTimeout(1);

            // Retract in reverse
            retract();
        }

        protected void execute() {
        }

        protected boolean isFinished() {
            return isTimedOut();
        }

        protected void end() {
        }

        protected void interrupted() {
            retract();
        }
    }
    private double direction;

    public DirectionalFireCommand(float direction) {
        this.direction = direction;
        
        addParallel(new MoveFireArmCommand(true));
        addParallel(new MoveFireArmCommand(false));
    }

    public double getDirection() {
        return direction;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        
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