package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import com.twelvevoltbolt.gallium.commands.TankDriveCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A drive system consisting of 4 motors, 2 on each side with the same output, left and right
 */
public class DriveSubsystem extends Subsystem {

    CANJaguar leftMotor1;
    CANJaguar leftMotor2;
    CANJaguar rightMotor1;
    CANJaguar rightMotor2;
    RobotDrive drive;
    
    public DriveSubsystem() {
        try {
            leftMotor1 = new CANJaguar(RobotMap.leftMotor1);
            leftMotor2 = new CANJaguar(RobotMap.leftMotor2);
            rightMotor1 = new CANJaguar(RobotMap.rightMotor1);
            rightMotor2 = new CANJaguar(RobotMap.rightMotor2);
            drive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void initDefaultCommand() {
        setDefaultCommand(new TankDriveCommand());
    }
    
    public void stop() {
        drive(0, 0);
    }
    
    public void drive(double left, double right) {
        drive.tankDrive(-(reversed ? right : left), -(reversed ? left : right));
    }
    
    public void arcadeDrive(double magnitude, double angle) {
        drive.arcadeDrive(magnitude * (reversed ? -1 : 1), angle);
    }
    
    private boolean reversed;
    
    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }
    
    public boolean getReversed() {
        return this.reversed;
    }
    
    public void toggleReversed() {
        setReversed(!getReversed());
    }
}
