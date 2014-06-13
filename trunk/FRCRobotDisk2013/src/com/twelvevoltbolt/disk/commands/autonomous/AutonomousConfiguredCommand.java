/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twelvevoltbolt.disk.commands.autonomous;

import com.twelvevoltbolt.autonomous.AutonomousTables;
import com.twelvevoltbolt.disk.commands.DriveCommand;
import com.twelvevoltbolt.disk.commands.ShiftCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


public class AutonomousConfiguredCommand extends CommandGroup {

    
    public AutonomousConfiguredCommand() {
        
        System.out.println("Starting AutonomousConfiguredCommand");
        
        addSequential(new ShiftCommand(false));
        // Returns Left, Right, Middle, or Goalie Zone
        String direction = AutonomousTables.getAutoString("LOCATION", "Left");
        if (direction.equals("Left") || direction.equals("Right")) {
            
            addSequential(new AutonomousDriveCommand("DRIVE", "DRIVE_TIME_1", "DRIVE_SPEED_1", true, 4.5, 0.75));
            addSequential(new WaitCommand(1));
            addSequential(new AutonomousFireActionCommand("FIRE"));
            addSequential(new WaitCommand(1));
            addSequential(new AutonomousDriveCommand("DRIVE", "DRIVE_TIME_2", "DRIVE_SPEED_2", true, 0, 0.75));
            
        } else if (direction.equals("Middle")) {
            
            addSequential(new AutonomousDriveCommand("DRIVE", "DRIVE_TIME_1", "DRIVE_SPEED_1", true, 4.5, 0.75));
            addSequential(new WaitCommand(1));

            double turnTime = AutonomousTables.getAutoNumber("DRIVE_TURN_TIME", 0.75);
            double turnSpeed = AutonomousTables.getAutoNumber("DRIVE_TURN_SPEED", 0.70);
            boolean turnLeft = AutonomousTables.getAutoBoolean("DRIVE_TURN_LEFT", true);
            
            if (turnLeft) {
                addSequential(new DriveCommand(-turnSpeed, turnSpeed, turnTime));
            } else {
                addSequential(new DriveCommand(turnSpeed, -turnSpeed, turnTime));
            }
            
            addSequential(new AutonomousFireActionCommand("FIRE"));
            
        } else if (direction.equals("Goalie Zone")) {
            //Do nothing
        }
        
    }
}
