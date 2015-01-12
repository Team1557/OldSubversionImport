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
public class TurnAndAimCommand extends CommandGroup {
    
    double speed = 0.25;
    double timeMultiplier = 0.2;
    
    double angle;
    
    public double getTurnAngle() {
        return angle;
    }
    
    public TurnAndAimCommand(double angle) {
        this.angle = angle;
        
        if (getTurnAngle() > 0) {
            addSequential(new DriveCommand(speed, 0, getTurnAngle() * timeMultiplier));
        } else if (getTurnAngle() < 0) {
            addSequential(new DriveCommand(0, speed, getTurnAngle() * timeMultiplier));
        }
        
        addSequential(new WaitCommand(1));
        addSequential(new FireActionCommand());        
    }
}