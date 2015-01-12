/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.disk.commands;

/**
 *
 * @author code
 */
public class FireCommand extends CommandBase { // CommandGroup {
    
    private boolean state;
    
    public FireCommand(boolean state) {
        requires(firing);
        
        this.state = state;
    }

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

    protected void execute() {
        firing.getLeftArmOn().set(state);
        firing.getRightArmOn().set(state);
        firing.getLeftArmOff().set(!state);
        firing.getRightArmOff().set(!state);
    }

    protected void initialize() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}