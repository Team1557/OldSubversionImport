/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twelvevoltbolt.disk.commands.autonomous;

import com.twelvevoltbolt.autonomous.AutonomousTables;
import com.twelvevoltbolt.disk.commands.FireCommand;


/**
 *
 * @author code
 */
public class AutonomousFireCommand extends FireCommand {

    String fireKey;
    
    boolean enabled = true;
    
    public AutonomousFireCommand(String fireKey, boolean defaultEnabled, boolean state) {
        super(state);
        this.enabled = defaultEnabled;
        this.fireKey = fireKey;
    }

    protected void initialize() {
        super.initialize();
        
        enabled = AutonomousTables.getAutoBoolean(fireKey, enabled);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (enabled) {
            super.execute();
        }
    }
}