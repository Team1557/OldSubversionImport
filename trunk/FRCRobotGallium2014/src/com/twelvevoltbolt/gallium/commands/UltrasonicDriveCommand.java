package com.twelvevoltbolt.gallium.commands;

public class UltrasonicDriveCommand extends CommandBase {

    boolean atX = false;
    int inches;
    
    public UltrasonicDriveCommand(int inches) {
        requires(ultrasonic);
        requires(drive);
        this.inches = inches;
    }
    
    
    protected void initialize() {
    }

    protected void execute() {
        /*if (ultrasonic.getUltrasonic().getRangeInches() > inches) {
            drive.arcadeDrive(0.5, 0);
        } else {
            drive.arcadeDrive(0, 0);
            atX = true;
        }*/
    }

    protected boolean isFinished() {
        return atX;
    }

    protected void end() {
        drive.drive(0, 0);
    }

    protected void interrupted() {
        end();
    }   
}
