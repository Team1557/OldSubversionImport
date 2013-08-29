package twelvevoltbolt.robotics.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Represents a simple command that by default only requires an initialize method.
 */
public abstract class SimpleCommand extends Command {
    protected void execute() {
        System.out.println("Execute");
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        System.out.println("End");
    }

    protected void interrupted() {
    }
    
    public abstract void initialize();
}
