
package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.autonomous.AutonomousTables;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
        boolean drive = AutonomousTables.getAutoBoolean("DRIVE", true);
        
        if (drive) {
            double driveTime1 = AutonomousTables.getAutoNumber("DRIVE_TIME_1", 1);
            addSequential(new DriveCommand(0.5, 0.5, driveTime1));
        }
        
        boolean fire = AutonomousTables.getAutoBoolean("FIRE", true);
        if (fire) {
            addSequential(new WaitCommand(1));
            addSequential(new FireActionCommand());
            addSequential(new WaitCommand(1));
        }

        if (drive) {
            double driveTime2 = AutonomousTables.getAutoNumber("DRIVE_TIME_2", 1);
            addSequential(new DriveCommand(0.5, 0.5, driveTime2));
        }
    }
}
