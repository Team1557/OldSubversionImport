/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author code
 */
public class FireCommand extends CommandBase { // CommandGroup {

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

//    public static class MoveFireArmCommand extends CommandBase {
//
//        Solenoid shoulder;
//        //Solenoid elbow;
//
//        public MoveFireArmCommand(boolean isLeftSide) {
//            requires(firing);
//
//            this.shoulder = isLeftSide ? firing.getLeftFireShoulder() : firing.getRightFireShoulder();
//            // this.elbow = isLeftSide ? firing.getLeftFireElbow() : firing.getRightFireElbow();
//        }
//
//        /**
//         * Gets the delay between firing each side of the arm pair
//         * @return 
//         */
//        public double getDelay() {
//            double direction = ((FireCommand) getGroup()).getDirection();
//
//            // Deadzone
//            if (Math.abs(direction) < 0.1) {
//                direction = 0;
//            }
//
//            return Math.abs(direction) * FireCommand.FIRING_AIM_DELAY_MULTIPLIER;
//        }
//
//        public void fire() {
//            shoulder.set(true);
//            setTimeout(getDelay());
//            //elbow.set(true);
//        }
//
//        public void retract() {
//            //elbow.set(false);
//            //setTimeout(getDelay());
//            shoulder.set(false);
//        }
//
//        protected void initialize() {
//            // First set fires the specific side
//            fire();
//
//            // Wait for everything to CALM DOWN
//            setTimeout(1);
//
//            // Retract in reverse
//            retract();
//        }
//
//        protected void execute() {
//        }
//
//        protected boolean isFinished() {
//            return isTimedOut();
//        }
//
//        protected void end() {
//        }
//
//        protected void interrupted() {
//            retract();
//        }
//    }

    protected void execute() {
        CommandBase.firing.fire(2);
    }

    protected void initialize() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}