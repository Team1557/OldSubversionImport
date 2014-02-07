
package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.autonomous.AutonomousTables;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
        boolean drive = AutonomousTables.getAutoBoolean("DRIVE", true);
        
        if (drive) {
            double driveTime = AutonomousTables.getAutoNumber("DRIVE_TIME_1", 1);
            
            if (driveTime > 0) {
                double driveSpeed = AutonomousTables.getAutoNumber("DRIVE_SPEED_1", 0.5);
                addSequential(new DriveCommand(driveSpeed, driveSpeed, driveTime));
            }
        }
        
        boolean fire = AutonomousTables.getAutoBoolean("FIRE", true);
        if (fire) {
            addSequential(new WaitCommand(1));
            addSequential(new FireActionCommand());
            addSequential(new WaitCommand(1));
        }

        if (drive) {
            double driveTime = AutonomousTables.getAutoNumber("DRIVE_TIME_2", 1);
            
            if (driveTime > 0) {
                double driveSpeed = AutonomousTables.getAutoNumber("DRIVE_SPEED_2", 0.5);
                addSequential(new DriveCommand(driveSpeed, driveSpeed, driveTime));
            }
        }
    }
}
