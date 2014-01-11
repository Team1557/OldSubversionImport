/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands;

/**
 *
 * @author code
 */
public class TriggerFireCommand extends DirectionalFireCommand {

    public TriggerFireCommand() {
        super(0);
    }

    // @Override
    public double getDirection() {
        return CommandBase.oi.getFiringAngle();
    }
}