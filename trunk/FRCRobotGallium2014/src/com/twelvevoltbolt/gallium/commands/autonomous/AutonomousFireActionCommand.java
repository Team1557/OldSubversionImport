/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.gallium.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


/**
 *
 * @author code
 */
public class AutonomousFireActionCommand extends CommandGroup {

    public AutonomousFireActionCommand(String enableKey) {
        addSequential(new AutonomousFireCommand(enableKey, true, true));
        addSequential(new WaitCommand(2));
        addSequential(new AutonomousFireCommand(enableKey, true, false));
    }
    
}