
package com.twelvevoltbolt.gallium.commands;

import com.twelvevoltbolt.gallium.commands.autonomous.AutonomousConfiguredCommand;

public class AutonomousCommand extends CommandBase {

    AutonomousConfiguredCommand cmd;
    
    public AutonomousCommand() {
//        requires(firing);
//        requires(drive);
    }

    protected void initialize() {
        cmd = new AutonomousConfiguredCommand();
        cmd.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return !cmd.isRunning();
    }

    protected void end() {
        System.out.println("Ending AutonomousCommand");
        if (cmd != null) {
            cmd.cancel();
        }
    }

    protected void interrupted() {
        end();
    }

    public synchronized void cancel() {
        super.cancel();
        end();
    }

}
