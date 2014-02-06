/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


/**
 *
 * @author code
 */
public class FireActionCommand extends CommandGroup {

    public FireActionCommand() {
        addSequential(new FireCommand(true));
        addSequential(new WaitCommand(2));
        addSequential(new FireCommand(false));
    }
    
}