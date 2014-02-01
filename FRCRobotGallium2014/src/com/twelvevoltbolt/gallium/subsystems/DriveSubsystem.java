package com.twelvevoltbolt.gallium.subsystems;

import com.twelvevoltbolt.gallium.RobotMap;
import com.twelvevoltbolt.gallium.commands.CommandBase;
import com.twelvevoltbolt.gallium.commands.TankDriveCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A drive system consisting of 4 motors, 2 on each side with the same output, left and right
 */
public class DriveSubsystem extends Subsystem {

    int speedShiftUp = 1000;
    int speedShiftDown = 500;
    Solenoid superShifter;
    public CANJaguar leftMotor1;
    public CANJaguar leftMotor2;
    public CANJaguar rightMotor1;
    public CANJaguar rightMotor2;
    RobotDrive drive;
    
    public DriveSubsystem() throws CANTimeoutException {
        superShifter = new Solenoid(RobotMap. SuperShifter);
        leftMotor1 = new CANJaguar(RobotMap.leftMotor1);
        leftMotor2 = new CANJaguar(RobotMap.leftMotor2);
        rightMotor1 = new CANJaguar(RobotMap.rightMotor1);
        rightMotor2 = new CANJaguar(RobotMap.rightMotor2);
        drive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
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
        return reversed;
    }
    
    public void toggleReversed() {
        setReversed(!getReversed());
    }
    
    // Gear Shifting System, state fo the gear
    private boolean gear;
    
    public void setGear(boolean gear) {
        this.gear = gear;
    }
    
    public boolean getGear() {
        return gear;
    }
    
    public void toggleGear() {
        setGear(!getGear());
    }
    
    public void shiftGear(boolean gear) {
        superShifter.set(gear);
    }

    public void updateGears() {
        try {
            double avg = (leftMotor1.getSpeed()); // + rightMotor1.getSpeed()) / 2;
            if ((avg < speedShiftUp) && !getGear()) {
                setGear(true);
                shiftGear(gear);
                System.out.println("Shifting up " + avg);
            } else if ((avg > speedShiftDown) && getGear()) {
                setGear(false);
                shiftGear(gear);
                System.out.println("Shifting down " + avg);
            }
            
            if (CommandBase.oi.isDebug()) {
                System.out.println("MotorSpeed: " + avg);
            }
        } catch (CANTimeoutException e) {
            e.printStackTrace();
        }
    }
}
