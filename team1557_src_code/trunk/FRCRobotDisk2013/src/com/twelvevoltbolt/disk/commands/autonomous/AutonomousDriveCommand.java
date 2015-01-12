/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.disk.commands.autonomous;

import com.twelvevoltbolt.autonomous.AutonomousTables;
import com.twelvevoltbolt.disk.commands.DriveCommand;


/**
 *
 * @author code
 */
public class AutonomousDriveCommand extends DriveCommand {

    String enableKey;
    String timeKey;
    String speedKey;
    boolean enabled = true;
    
    public AutonomousDriveCommand(String enableKey, String timeKey, String speedKey, boolean defaultEnabled, double defaultTime, double defaultSpeed) {
        super(defaultSpeed, defaultSpeed, defaultTime);
        this.enableKey = enableKey;
        this.timeKey = timeKey;
        this.speedKey = speedKey;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        enabled = AutonomousTables.getAutoBoolean(enableKey, true);
        
        double speed = AutonomousTables.getAutoNumber(speedKey, getLeft());
        setLeft(speed);
        setRight(speed);
        
        setTime(AutonomousTables.getAutoNumber(timeKey, getTime()));
        
        System.out.println("Drive Enabled: " + enabled);
        System.out.println("Drive Speed: " + speed);
        System.out.println("Drive Time: " + getTime());
        
        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (enabled) {
            super.execute();
        }
    }
}
