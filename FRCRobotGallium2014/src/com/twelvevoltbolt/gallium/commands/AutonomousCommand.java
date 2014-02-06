
package com.twelvevoltbolt.gallium.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
        addSequential(new DriveCommand(0.5, 0.5, 1));
        addSequential(new WaitCommand(1));
        addSequential(new FireActionCommand());
        addSequential(new WaitCommand(1));
        addSequential(new DriveCommand(0.5, 0.5, 1));
    }
}
