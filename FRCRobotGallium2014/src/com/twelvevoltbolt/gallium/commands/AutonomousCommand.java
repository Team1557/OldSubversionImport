
package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author bradmiller
 */
public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
        addSequential(new DriveForwardCommand(0.5, 2));
        addSequential(new WaitCommand(1));
        addSequential(new DirectionalFireCommand(0));
    }
    
}
