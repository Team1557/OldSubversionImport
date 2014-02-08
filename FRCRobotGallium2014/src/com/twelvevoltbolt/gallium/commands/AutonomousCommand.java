
package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.gallium.commands.autonomous.AutonomousDriveCommand;
import com.twelvevoltbolt.gallium.commands.autonomous.AutonomousFireActionCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
        addSequential(new AutonomousDriveCommand("DRIVE", "DRIVE_TIME_1", "DRIVE_SPEED_1", true, 1, 0.75));
        addSequential(new WaitCommand(1));
        addSequential(new AutonomousFireActionCommand("FIRE"));
        addSequential(new WaitCommand(1));
        addSequential(new AutonomousDriveCommand("DRIVE", "DRIVE_TIME_2", "DRIVE_SPEED_2", true, 1, 0.75));
    }
}
